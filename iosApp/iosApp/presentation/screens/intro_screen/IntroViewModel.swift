//
//  IntroViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/29/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class IntroViewModel: ObservableObject {
    private let authUseCase = KoinHelper().authUseCase
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = IntroState()
    private let effectSubject = PassthroughSubject<IntroEffect, Never>()

    init(cancellables: Set<AnyCancellable> = Set<AnyCancellable>(), state: IntroState = IntroState()) {
        self.cancellables = cancellables
        self.state = state

        getUserCondition()
    }

    func effect() -> AnyPublisher<IntroEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: IntroEvent) {
        switch event {
        case .UserOnBoarded:
            handleUserOnBoard()
        case .CancelAsyncTasks:
            cancelAsyncTasks()
        }
    }
    
    private func getUserCondition() {
        let future = createFuture(for: authUseCase.getAdmission.execute())
        
        future.delay(for: 2, scheduler: RunLoop.main).sink(
                receiveCompletion: { _ in
                    self.updateState(isLoading: false)
                },
                receiveValue: { user in
                    self.updateState(userCondition: user)
                    self.effectSubject.send(.Navigate(userCondition: user))
                    print(user)
                }
            )
            .store(in: &self.cancellables)
    }

    private func handleUserOnBoard() {
        createFuture(for: authUseCase.setOnBoarded.execute(isOnBoolean: true))
            .sink(receiveCompletion: { _ in
                self.effectSubject.send(.UserOnBoarded)
            }, receiveValue: { _ in
                
            })
            .store(in: &cancellables)
    }

    private func updateState(
        userCondition: UserCondition? = nil,
        isLoading: Bool? = nil
    ) {
        guard let currentState = state.copy() as? IntroState else { return }
        DispatchQueue.main.async { [self] in
            state = state.doCopy(
                userCondition: userCondition ?? currentState.userCondition,
                isLoading: isLoading ?? currentState.isLoading
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

enum IntroEvent {
    case UserOnBoarded
    case CancelAsyncTasks
}

enum IntroEffect {
    case UserOnBoarded
    case Navigate(userCondition: UserCondition)
}
