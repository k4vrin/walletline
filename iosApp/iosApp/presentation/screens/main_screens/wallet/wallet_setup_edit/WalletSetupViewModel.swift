//
//  WalletSetupViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class WalletSetupViewModel: ObservableObject {
    let walletId: String?
    private let useCase = KoinHelper().walletUseCase
    private let validateUseCase = KoinHelper().validateUseCase
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = WalletSetupState()
    private let effectSubject = PassthroughSubject<WalletSetupEffect, Never>()
    
    init(walletId: String?) {
        self.walletId = walletId
        if let walletId = walletId {
            createFuture(for: useCase.getWallet.execute(walletId: walletId))
                .sink(receiveCompletion: {_ in}, receiveValue: {resource in
                    switch resource {
                    case is ResourceError<KotlinBoolean>:
                        self.effectSubject.send(.ErrorInWalletCreation(err: .errorhappened))
                    case is ResourceSuccess<Wallet>:
                        guard let res = (resource as? ResourceSuccess<Wallet>)?.data else {return}
                        self.updateState(title: res.name, balance: res.balance, desc: res.description_, isApplyEnable: true)
                    default:
                        self.doNothing()
                    }
                })
                .store(in: &cancellables)
        }
    }

    func effect() -> AnyPublisher<WalletSetupEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: WalletSetupEvent) {
        switch event {
        case .ChangeTitle(title: let title):
            updateState(title: title, isApplyEnable: !title.isBlank() && !state.balance.isBlank())
        case .ChangeBalance(balance: let balance):
            updateState(balance: balance, isApplyEnable: !state.title.isBlank() && !balance.isBlank())
        case .ChangeDescription(desc: let desc):
            updateState(desc: desc)
        case .ApplyClicked:
            createWallet()
        case .SaveClicked:
            editWallet()
        case .CancelTasks:
            cancelAsyncTasks()
        case .DeleteWallet:
            deleteWallet()
        }
    }
    
    private func createWallet() {
        createFuture(for: useCase.createWallet.execute(name: state.title, balance: state.balance, description: state.desc, lines: []))
            .sink(receiveCompletion: {_ in}, receiveValue: { resource in
                switch resource {
                case is ResourceError<CreateWalletError>:
                    guard let res = (resource as? ResourceError<CreateWalletError>)?.data else {return}
                    self.effectSubject.send(.ErrorInWalletCreation(err: res))
                case is ResourceSuccess<KotlinBoolean>:
                    self.effectSubject.send(.NavigateToWalletScreen)
                default:
                    self.doNothing()
                }
            })
            .store(in: &cancellables)
    }
    
    private func editWallet() {
        guard let walletId = self.walletId else {return}
        createFuture(for: useCase.editWallet.execute(wallet: Wallet(id: walletId, name: state.title, balance: state.balance, description: state.desc, lines: [])))
            .sink(receiveCompletion: {_ in}, receiveValue: { resource in
                switch resource {
                case is ResourceError<KotlinBoolean>:
                    self.effectSubject.send(.ErrorInWalletCreation(err: .errorhappened))
                case is ResourceSuccess<KotlinBoolean>:
                    self.effectSubject.send(.NavigateToWalletScreen)
                default:
                    self.doNothing()
                }
            })
            .store(in: &cancellables)
    }
    
    private func deleteWallet() {
        guard let walletId = self.walletId else {return}
        createFuture(for: useCase.deleteWallet.execute(walletId: walletId))
            .sink(receiveCompletion: {_ in}, receiveValue: { resource in
                switch resource {
                case is ResourceError<KotlinBoolean>:
                    self.effectSubject.send(.ErrorInWalletCreation(err: .errorhappened))
                case is ResourceSuccess<KotlinBoolean>:
                    self.effectSubject.send(.NavigateToWalletScreen)
                default:
                    self.doNothing()
                }
            })
            .store(in: &cancellables)
    }

    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func doNothing() {}

    private func updateState(
        title: String? = nil,
        balance: String? = nil,
        desc: String? = nil,
        isApplyEnable: Bool? = nil
    ) {
        DispatchQueue.main.async { [self] in
            state = WalletSetupState(title: title ?? state.title, balance: balance ?? state.balance, desc: desc ?? state.desc, isApplyEnable: isApplyEnable ?? state.isApplyEnable)
        }
    }
}

enum WalletSetupEvent {
    case ChangeTitle(title: String)
    case ChangeBalance(balance: String)
    case ChangeDescription(desc: String)
    case ApplyClicked
    case SaveClicked
    case CancelTasks
    case DeleteWallet
}

enum WalletSetupEffect {
    case NavigateToWalletScreen
    case ErrorInWalletCreation(err: CreateWalletError)
}

struct WalletSetupState {
    var title: String = ""
    var balance: String = ""
    var desc: String? = nil
    var isApplyEnable: Bool = false
    var walletId: String? = nil
}
