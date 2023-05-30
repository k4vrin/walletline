//
//  EmailVerifyViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class VerifyEmailViewModel: ObservableObject {
    private let useCase = KoinHelper()
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = VerifyEmailState()
    private let eventSubject = PassthroughSubject<VerifyEmailEffect, Never>()

    func effect() -> AnyPublisher<VerifyEmailEffect, Never> {
        return eventSubject.eraseToAnyPublisher()
    }

    init(cancellables: Set<AnyCancellable> = Set<AnyCancellable>(), state: VerifyEmailState = VerifyEmailState()) {
        self.cancellables = cancellables
        self.state = state
        startTimer()
    }

    func onEvent(_ event: VerifyEmailEvent) {
        switch event {
        case .OTPChange(let text):
            updateState(otp: text)
        case .OnChangeEmailClicked:
            eventSubject.send(.NavigateToEmail)
        case .OnContinueClicked:
            verifyOTP()
        case .OnResendClicked:
            resendOTP()
        case .OnPrivacyClicked:
            doNothing()
        case .OnTermsClicked:
            doNothing()
        case .cancelAsyncTask:
            cancelAsyncTasks()
        case .UpdateEmail(let email):
            updateState(email: email)
        }
    }

    private func verifyOTP() {
        let verifyFuture = createFuture(for: useCase.verifyOtp(otp: state.otp))
        updateState(isLoading: true)
        verifyFuture.sink(
            receiveCompletion: { _ in },
            receiveValue: { [self] res in
                updateState(isLoading: false)
                switch res {
                case is ResourceError<KotlinNothing>:
                    handleVerifyError((res as? ResourceError<KotlinNothing>)?.message)
                case is ResourceSuccess<KotlinBoolean>:
                    eventSubject.send(.VerifySuccessful)
                default:
                    doNothing()
                }
            }
        )
        .store(in: &cancellables)
    }

    private func resendOTP() {
        let resendOTPFuture = createFuture(for: useCase.resendOtp())
        updateState(isLoading: true)
        resendOTPFuture.sink(
            receiveCompletion: { _ in },
            receiveValue: { [self] res in
                updateState(isLoading: false)
                switch res {
                case is ResourceError<KotlinNothing>:
                    handleVerifyError((res as? ResourceError<KotlinNothing>)?.message)
                case is ResourceSuccess<OtpData>:
                    startTimer()
                    guard let res = res as? ResourceSuccess<OtpData> else { return }
                    guard let otp = res.data?.otp else { return }
                    eventSubject.send(.ResendOTP(otp: otp))
                default:
                    doNothing()
                }
            }
        )
        .store(in: &cancellables)
    }

    private func handleVerifyError(_ errorMessage: String?) {
        if errorMessage?.contains("otp") == true {
            updateState(otpError: OTPValidationMessageNotValid())
        } else {
            eventSubject.send(.Error(message: errorMessage ?? "Try again"))
        }
    }

    private func startTimer() {
        useCase.startTimer()
        guard let timer = useCase.timer() else { eventSubject.send(.Error(message: "timer null"));return }
        let publisher = createPublisher(for: timer)
        
        publisher.sink(receiveCompletion: { _ in
            self.updateState(isTimerActive: false)
        }, receiveValue: { value in
            self.updateState(timer: Int32(truncating: value))
        })
        .store(in: &cancellables)
    }

    private func updateState(
        otp: String? = nil,
        otpError: OTPValidationMessage? = nil,
        email: String? = nil,
        timer: Int32? = nil,
        isTimerActive: Bool? = nil,
        isLoading: Bool? = nil,
        isActionsEnabled: Bool? = nil
    ) {
        guard let currentState = state.copy() as? VerifyEmailState else { return }
        DispatchQueue.main.async { [self] in
            state = state.doCopy(
                otp: otp ?? currentState.otp,
                otpError: otpError ?? currentState.otpError,
                email: email ?? currentState.email,
                timer: timer ?? currentState.timer,
                isTimerActive: isTimerActive ?? currentState.isTimerActive,
                isLoading: isLoading ?? currentState.isLoading,
                isActionsEnabled: isActionsEnabled ?? currentState.isActionsEnabled
            )
        }
    }

    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func doNothing() {}

    @inline(__always)
    private func handleAction(block: () -> Void) {
        updateState(isActionsEnabled: false)
        block()
        updateState(isActionsEnabled: true)
    }
}

enum VerifyEmailEvent {
    case OTPChange(text: String)
    case UpdateEmail(email: String)
    case OnContinueClicked
    case OnChangeEmailClicked
    case OnResendClicked
    case OnTermsClicked
    case OnPrivacyClicked
    case cancelAsyncTask
}

enum VerifyEmailEffect {
    case Error(message: String)
    case VerifySuccessful
    case NavigateToEmail
    case NavigateToPattern
    case ShowPolicy
    case ShowTerms
    case ResendOTP(otp: String)
}
