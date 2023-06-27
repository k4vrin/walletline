//
//  WalletViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class WalletViewModel: ObservableObject {
    private let useCase = KoinHelper().walletUseCase
    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var state = WalletState()
    private let effectSubject = PassthroughSubject<WalletEffect, Never>()

    func effect() -> AnyPublisher<WalletEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: WalletEvent) {
        switch event {
        case .ChangeTab(isLineSelected: let isLineSelected):
            updateState(isLinesSelected: isLineSelected)
        case .EditWalletClicked(walletId: let walletId):
            effectSubject.send(.NavigateEditWallet(walletId: walletId))
        case .LineClicked(lineId: let lineId):
            effectSubject.send(.NavigateToLine(lineId: lineId))
        case .CreateLineClicked:
            if let wallet = state.selectedWallet {
                effectSubject.send(.NavigateCreateLine(walletId: wallet.id))
            }
        case .AddTransactionClicked:
            doNothing()
        case .HideNumbersClicked:
            doNothing()
        case .OnBackButtonClicked:
            doNothing()
        case .CancelTasks:
            cancelAsyncTasks()
        case .CreateWalletClicked:
            effectSubject.send(.NavigateCreateWallet)
        case .GetWallets:
            getWallets()
        case .ChangeFilter(order: let order):
            updateState(filterOrder: order)
        }
    }

    private func getWallets() {
        createPublisher(for: useCase.getWallets.execute())
            .sink(receiveCompletion: { _ in }, receiveValue: { wallets in
                let uiWallets = wallets.map { wallet in
                    WalletUiItem(
                        id: wallet.id,
                        title: wallet.name,
                        balance: Decimal(string: wallet.balance) ?? Decimal(),
                        description: wallet.description_,
                        lines: wallet.lines.map { line in
                            WalletLineUiItem(
                                id: line.id,
                                title: line.name,
                                percentage: line.percentage,
                                balance: Decimal(string: line.balance) ?? Decimal(),
                                description: line.description_,
                                categories: line.categories
                            )
                        },
                        transactions: self.dummyTransactions()
                    )
                }

                self.updateState(wallets: uiWallets)
            })
            .store(in: &cancellables)
    }

    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func dummyTransactions() -> [TransactionUiItem] {
        return [
            TransactionUiItem(
                id: UUID().uuidString,
                title: "Salary",
                isDeposit: true,
                amount: 120,
                description: "Datarivers",
                categories: [],
                date: .now,
                isTaxInculded: true
            ),
            TransactionUiItem(
                id: UUID().uuidString,
                title: "Transportation",
                isDeposit: false,
                amount: 86.80,
                description: "Petrol",
                categories: [],
                date: .distantPast,
                isTaxInculded: true
            ),
            TransactionUiItem(
                id: UUID().uuidString,
                title: "Gym",
                isDeposit: false,
                amount: 63.90,
                description: "Shahin",
                categories: [],
                date: .distantPast,
                isTaxInculded: true
            ),
        ]
    }

    private func doNothing() {}

    private func updateState(
        wallets: [WalletUiItem]? = nil,
        isLinesSelected: Bool? = nil,
        filterOrder: TransactionFilterOrder? = nil
    ) {
        
        DispatchQueue.main.async { [self] in
            state = WalletState(
                wallets: wallets ?? state.wallets,
                isLinesSelected: isLinesSelected ?? state.isLinesSelected,
                selectedWallet: state.wallets.last,
                transOrder: filterOrder ?? state.transOrder
            )
        }
    }
}

enum WalletEvent {
    case ChangeTab(isLineSelected: Bool)
    case EditWalletClicked(walletId: String)
    case LineClicked(lineId: String)
    case CreateLineClicked
    case AddTransactionClicked
    case HideNumbersClicked
    case OnBackButtonClicked
    case CancelTasks
    case CreateWalletClicked
    case GetWallets
    case ChangeFilter(order: TransactionFilterOrder)
}

enum WalletEffect {
    case NavigateCreateLine(walletId: String)
    case NavigateAddTransaction(walletId: String)
    case NavigateEditWallet(walletId: String)
    case NavigateCreateWallet
    case NavigateToLine(lineId: String)
}

struct WalletState {
    var wallets: [WalletUiItem] = []
    var isLinesSelected: Bool = true
    var selectedWallet: WalletUiItem? = nil
    var transOrder: TransactionFilterOrder = .NewestToOldest
}
