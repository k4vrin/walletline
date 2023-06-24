//
//  MakePatternViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class MakePatternViewModel: ObservableObject {
    private let authUseCase = KoinHelper().authUseCase
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = MakePatternState()
    private let effectSubject = PassthroughSubject<MakePatternEffect, Never>()

    func effect() -> AnyPublisher<MakePatternEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: MakePatternEvent) {
        switch event {
        case .PatternChanged(let pattern, let isScrollEnabled):
            updateState(pattern: pattern, isScrollEnabled: isScrollEnabled, isContinueButtonEnable: pattern.count >= 4)
        case .ConfirmPatternChange(pattern: let pattern, isScrollEnabled: let isScrollEnabled):
            updateState(confirmPattern: pattern, isScrollEnabled: isScrollEnabled, isContinueButtonEnable: pattern.count >= 4)
        case .SkipPatternClicked:
            handleSkipPattern()
        case .ContinueClicked:
            effectSubject.send(.NavigateToConfirmPattern)
        case .FingerPrintClicked:
            effectSubject.send(.ShowBiometricPrompt)
        case .ConfirmPatternClicked:
            confirmPattern()
        case .RetryPatternClicked:
            effectSubject.send(.RetryPattern)
        case .FingerFaceSuccessful(type: _):
            handleSuccessFingerFace()
        case .FingerFaceUnSuccessful(error: let error):
            handleUnsuccessfulFingerFace(error)
        }
    }

    private func handleUnsuccessfulFingerFace(_ error: BiometricError) {}

    private func handleSuccessFingerFace() {
        let setAdmission = createFuture(for: authUseCase.setAdmission.execute(pattern: "", isFingerprint: true))
        setAdmission.sink(receiveCompletion: { _ in }, receiveValue: { _ in
            self.effectSubject.send(.BiometricSuccess)
        })
        .store(in: &cancellables)
    }

    private func handleSkipPattern() {
        let setAdmission = createFuture(for: authUseCase.setAdmission.execute(pattern: "", isFingerprint: false))
        setAdmission.sink(receiveCompletion: { _ in

        }, receiveValue: { _ in
            self.effectSubject.send(.SkipMakePattern)
        }).store(in: &cancellables)
    }

    private func confirmPattern() {
        if state.pattern != state.confirmPattern {
            effectSubject.send(.ConfirmPatternUnSuccessful(message: PatternValidationMessageNotSame()))
            return
        }

        let setAdmission = createFuture(for: authUseCase.setAdmission.execute(pattern: state.pattern, isFingerprint: false))
        setAdmission.sink(receiveCompletion: { _ in

        }, receiveValue: { _ in
            self.effectSubject.send(.ConfirmPatternSuccessful)
        })
        .store(in: &cancellables)
    }

    private func updateState(
        pattern: String? = nil,
        confirmPattern: String? = nil,
        patternError: PatternValidationMessage? = nil,
        isScrollEnabled: Bool? = nil,
        isContinueButtonEnable: Bool? = nil,
        isConfirmButtonEnable: Bool? = nil
    ) {
        guard let currentState = state.copy() as? MakePatternState else { return }
        DispatchQueue.main.async { [self] in
            state = state.doCopy(
                pattern: pattern ?? currentState.pattern,
                confirmPattern: confirmPattern ?? currentState.confirmPattern,
                patternError: patternError ?? currentState.patternError,
                isScrollEnabled: isScrollEnabled ?? currentState.isScrollEnabled,
                isContinueButtonEnable: isContinueButtonEnable ?? currentState.isContinueButtonEnable,
                isConfirmButtonEnable: isConfirmButtonEnable ?? currentState.isConfirmButtonEnable
            )
        }
    }

    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func doNothing() {}
}

enum MakePatternEvent {
    case PatternChanged(pattern: String, isScrollEnabled: Bool)
    case ConfirmPatternChange(pattern: String, isScrollEnabled: Bool)
    case SkipPatternClicked
    case ContinueClicked
    case FingerPrintClicked
    case ConfirmPatternClicked
    case RetryPatternClicked
    case FingerFaceSuccessful(type: BiometricType)
    case FingerFaceUnSuccessful(error: BiometricError)
}

enum MakePatternEffect {
    case NavigateToConfirmPattern
    case ConfirmPatternSuccessful
    case BiometricSuccess
    case ConfirmPatternUnSuccessful(message: PatternValidationMessage)
    case RetryPattern
    case ShowBiometricPrompt
    case SkipMakePattern
}
