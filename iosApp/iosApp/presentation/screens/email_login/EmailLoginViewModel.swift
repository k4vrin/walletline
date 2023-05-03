//
//  EmailLoginViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/23/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class EmailLoginViewModel: ObservableObject {
    private let useCase = KoinHelper()
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = EmailLoginState()
    private let eventSubject = PassthroughSubject<EmailEffect, Never>()

    func onEvent(_ event: EmailEvent) {
        switch event {
        case .emailChange(let email):
            updateState(email: email)
        case .continueClicked:
            handleAction {
                registerEmail()
            }
        case .enterBySocials:
            handleAction {
                eventSubject.send(.enterBySocials)
            }
        case .cancelAsyncTasks:
            cancelAsyncTasks()
        }
    }

    private func registerEmail() {
        let emailValidation = useCase.validateEmail(email: state.email)
        updateState(emailError: emailValidation.message)
        if !emailValidation.isSuccess { return }
        updateState(isLoading: true)
        let registerResponse = createFuture(for: useCase.register(email: state.email))
        registerResponse.sink(
            receiveCompletion: { _ in },
            receiveValue: { res in
                switch res {
                case is ResourceError<RegisteredError>:
                    self.handleRegisterError(res as! ResourceError<RegisteredError>)
                case is ResourceSuccess<RegisteredSuccess>:
                    self.eventSubject.send(.registerSuccessful)
                default: self.doNothing()
                }
            }
        )
        .store(in: &cancellables)
    }

    private func handleRegisterError(_ res: ResourceError<RegisteredError>) {
        guard let error = res.data?.emailError?.first else {
            eventSubject.send(.error(message: res.message ?? "Unknown error"))
            return
        }
        updateState(emailError: EmailValidationMessageDynamic(message: error))
    }

    func effect() -> AnyPublisher<EmailEffect, Never> {
        return eventSubject.eraseToAnyPublisher()
    }

    private func updateState(
        email: String? = nil,
        emailError: EmailValidationMessage? = nil,
        isLoading: Bool? = nil,
        isActionEnabled: Bool? = nil
    ) {
        guard let currentState = state.copy() as? EmailLoginState else {return}
        state = state.doCopy(
            email: email ?? currentState.email,
            emailError: emailError ?? currentState.emailError,
            isLoading: isLoading ?? currentState.isLoading,
            isActionsEnabled: isActionEnabled ?? currentState.isActionsEnabled
        )
    }
    
    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func doNothing() {}

    @inline(__always)
    private func handleAction(block: () -> Void) {
        updateState(isActionEnabled: false)
        block()
        updateState(isActionEnabled: true)
    }
}

enum EmailEffect {
    case registerSuccessful
    case error(message: String)
    case enterBySocials
}

enum EmailEvent {
    case emailChange(email: String)
    case continueClicked
    case enterBySocials
    case cancelAsyncTasks
}