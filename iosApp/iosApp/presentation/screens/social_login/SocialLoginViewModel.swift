//
//  SocialLoginViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/15/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class SocialLoginViewModel: ObservableObject {
    private let useCase = KoinHelper()
    private var cancellables = Set<AnyCancellable>()
    
    @Published private(set) var state = SocialLoginState()
    private let eventSubject = PassthroughSubject<SocialEffect, Never>()
    
    func onEvent(_ event: SocialEvent) {
        switch event {
        case .SignInWithGoogle(tokens: let tokens):
            signInWithGoogle(tokens: tokens)
        case .SignInWithFacebook(tokens: let tokens):
            signInWithFacebook(tokens: tokens)
        case .SignInWithApple(tokens: let tokens):
            signInWithApple(tokens: tokens)
        }
    }
    
    func signInWithGoogle(tokens: SocialSignTypeGoogleAuth) {
        updateState(isLoading: true)
        let googleFuture = createFuture(for: useCase.authUseCase.signInWithSocial.execute(type: tokens))
        googleFuture.sink(receiveCompletion: {error in}, receiveValue: { [unowned self] resource in
            switch resource {
            case is ResourceError<SocialSignInError>:
                guard let res = (resource as? ResourceError<SocialSignInError>)?.data else {return}
                eventSubject.send(.ShowError(error: res))
            case is ResourceSuccess<KotlinBoolean>:
                eventSubject.send(.NavigateToPattern)
            default:
                doNothing()
            }
        })
        .store(in: &cancellables)
    }
    
    func signInWithFacebook(tokens: SocialSignTypeFacebookAuth) {
        
    }
    
    func signInWithApple(tokens: SocialSignTypeAppleAuth) {
        
    }
    
    func effect() -> AnyPublisher<SocialEffect, Never> {
        return eventSubject.eraseToAnyPublisher()
    }

    private func updateState(
        isLoading: Bool? = nil
    ) {
        guard let currentState = state.copy() as? SocialLoginState else { return }
        DispatchQueue.main.async { [self] in
            state = state.doCopy(
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

enum SocialEvent {
    case SignInWithGoogle(tokens: SocialSignTypeGoogleAuth)
    case SignInWithFacebook(tokens: SocialSignTypeFacebookAuth)
    case SignInWithApple(tokens: SocialSignTypeAppleAuth)
}

enum SocialEffect {
    case NavigateToPattern
    case NavigateToEmailLogin
    case ShowError(error: SocialSignInError)
}
