//
//  LoginPatternViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/29/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import shared
import KMPNativeCoroutinesCombine


@MainActor
class LoginPatternViewModel: ObservableObject {
    private let authUseCase = KoinHelper().authUseCase
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = LoginPatternState()
    private let effectSubject = PassthroughSubject<LoginPatternEffect, Never>()
    
    init(cancellables: Set<AnyCancellable> = Set<AnyCancellable>(), state: LoginPatternState = LoginPatternState()) {
        self.cancellables = cancellables
        self.state = state
        
        createFuture(for: authUseCase.getPattern.execute())
            .sink(
                receiveCompletion: {_ in},
                receiveValue: { pattern in
                    self.updateState(pattern: pattern)
                }
            ).store(in: &self.cancellables)
        
    }

    func effect() -> AnyPublisher<LoginPatternEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: LoginPatternEvent) {
        switch event {
        case .ClearAsyncTasks:
            cancelAsyncTasks()
        case .DrawPatternFinished(pattern: let pattern):
            confirmPattern(pattern)
        }
    }
    
    
    private func confirmPattern(_ pattern: String) {
        if pattern == state.pattern {
            effectSubject.send(.PatternIsVerified)
            return
        }
        
        updateState(remainingAttempts: state.remainingAttempts - 1)
        
        if state.remainingAttempts == 0 {
            effectSubject.send(.LockApp)
        }
    }

    private func updateState(
        pattern: String? = nil,
        remainingAttempts: Int32? = nil,
        patternError: PatternValidationMessage? = nil,
        isScrollEnabled: Bool? = nil,
        showRemainingAttempts: Bool? = nil,
        lockTimer: Int32? = nil
    ) {
        guard let currentState = state.copy() as? LoginPatternState else { return }
        DispatchQueue.main.async { [self] in
            state = state.doCopy(
                pattern: pattern ?? currentState.pattern,
                remainingAttempts: remainingAttempts ?? currentState.remainingAttempts,
                showRemainingAttempts: showRemainingAttempts ?? currentState.showRemainingAttempts,
                isScrollEnabled: isScrollEnabled ?? currentState.isScrollEnabled,
                patternError: patternError ?? currentState.patternError,
                lockTimer: lockTimer ?? currentState.lockTimer
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

enum LoginPatternEvent {
    case ClearAsyncTasks
    case DrawPatternFinished(pattern: String)
}

enum LoginPatternEffect {
    case PatternIsVerified
    case LockApp
    case LockIsExpired
}
