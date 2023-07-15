//
//  WalletScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import SwiftUI

struct WalletScreen: View {
    @StateObject private var viewModel = WalletViewModel()
    @State private var isScrolling: Bool = false
    @State private var dest: AnyView? = nil
    @State private var isNavActive = false
    @State private var cancellables = Set<AnyCancellable>()
    @State private var blurBalance = false
    @State private var filterOrder = TransactionFilterOrder.NewestToOldest {
        didSet {
            viewModel.onEvent(.ChangeFilter(order: filterOrder))
        }
    }

    @ObservedObject var navController: NavigationController
    
    @State private var accounts: [WalletUiItem] = []
    
    var lines: [WalletLineUiItem]? {
        return accounts.last?.lines
    }
    
    var transactions: [TransactionUiItem] {
        return accounts.last?.transactions ?? []
    }
    
    var body: some View {
        WalletLineScaffold(isScrolling: $isScrolling) { geo in
            NavigationLink(isActive: $isNavActive) {
                dest
            } label: {
                EmptyView()
            }.hidden()
            VStack(spacing: Padding.defaultPadding) {
                if !accounts.isEmpty {
                    AccountPicker(
                        accounts: $accounts,
                        blurBalance: $blurBalance,
                        isScrolling: isScrolling
                    )
                    .simultaneousGesture(DragGesture(minimumDistance: 0), including: .all)
                    .padding(.horizontal, Padding.medium)
                    .padding(.bottom, Padding.extraLarge)
                    
                    WalletLineTabRow(
                        isFirstTabSelected: Binding(
                            get: { viewModel.state.isLinesSelected },
                            set: { value in viewModel.onEvent(.ChangeTab(isLineSelected: value)) }
                        ),
                        firstTabName: NSLocalizedString("Lines", comment: ""),
                        secondTabName: NSLocalizedString("Transactions", comment: "")
                    )
                    .padding(.horizontal, Padding.medium)
                    
                    WalletTabSection(
                        isLinesSelected: viewModel.state.isLinesSelected,
                        lines: lines,
                        isAccsEmpty: accounts.isEmpty,
                        transactions: transactions,
                        filterOrder: $filterOrder,
                        onCreateWalletClicked: { viewModel.onEvent(.CreateWalletClicked) },
                        onCreateLineClicked: { viewModel.onEvent(.CreateLineClicked) }
                    )
                } else {
                    WalletEmptyView(
                        title: NSLocalizedString("There is no wallet yet!", comment: ""),
                        desc: NSLocalizedString("Plan ahead and manage", comment: "")
                    ).frame(height: geo.size.height / 2, alignment: .bottom)
                    
                    DashedBorderButton(title: NSLocalizedString("Create your 1st wallet", comment: "")) {
                        if accounts.isEmpty {
                            viewModel.onEvent(.CreateWalletClicked)
                        } else {
                            viewModel.onEvent(.CreateLineClicked)
                        }
                    }
                    .padding([.horizontal, .top], Padding.medium)
                }
                
                if viewModel.state.isLinesSelected {}
            }
            
        } topBar: {
            DefaultTopBar(
                title: NSLocalizedString("Wallets", comment: ""),
                color: .neutralColorShade1,
                isMoreEnable: !accounts.isEmpty,
                isScrolling: $isScrolling,
                menu: {
                    if !accounts.isEmpty {
                        Button {
                            guard let id = accounts.last?.id else { return }
                            viewModel.onEvent(.EditWalletClicked(walletId: id))
                        } label: {
                            HStack {
                                Image("ic_edit")
                                    .renderingMode(.template)
                                    .foregroundColor(.neutralColorDark)
                                Text(
                                    NSLocalizedString("Edit Wallet", comment: "")
                                )
                                .titleLargeStyle()
                            }
                        }
                    }
                }
            )
        }
        .onAppear {
            navController.detailIsShown = false
            viewModel.onEvent(.GetWallets)
            let effect = viewModel.effect()
            effect.sink(receiveCompletion: { _ in }, receiveValue: { effect in
                switch effect {
                case .NavigateCreateLine(walletId: let walletId):
                    doNothing()
                case .NavigateAddTransaction(walletId: let walletId):
                    doNothing()
                case .NavigateEditWallet(walletId: let walletId):
                    dest = AnyView(WalletSetupScreen(walletId: walletId))
                    isNavActive = true
                    navController.detailIsShown = true
                case .NavigateToLine(lineId: let lineId):
                    doNothing()
                case .NavigateCreateWallet:
                    dest = AnyView(WalletSetupScreen(walletId: nil))
                    isNavActive = true
                    navController.detailIsShown = true
                }
            })
            .store(in: &cancellables)
        }
        .onDisappear {
            viewModel.onEvent(.CancelTasks)
            cancellables.forEach {
                $0.cancel()
            }
        }
        .onChange(of: viewModel.state.wallets) { wallets in
            self.accounts = wallets
        }
        .onChange(of: accounts, perform: { accs in
            navController.currentWalletId = accs.last?.id
        })
    }
    
    private func doNothing() {}
}

struct WalletScreen_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            WalletScreen(navController: NavigationController())
        }
    }
}
