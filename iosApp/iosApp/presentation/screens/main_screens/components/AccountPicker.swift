//
//  AccountPicker.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AccountPicker: View {
    @Binding var accounts: [WalletUiItem]
    @Binding var blurBalance: Bool
    var isScrolling: Bool = false
    @State private var page = 0
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            ZStack {
                ForEach(
                    Array(zip(accounts.indices.reversed(), accounts)),
                    id: \.1
                ) { index, acc in
                    AccountSummaryCard(
                        wallet: acc,
                        blurBalance: blurBalance,
                        isDragEnable: acc == accounts.last
                    ) {
                        accounts.insert(accounts.popLast()!, at: 0)
                        page += 1
                        if page >= accounts.count {
                            page = 0
                        }
                    }
                    .frame(height: 184)
                    .padding(.horizontal, Padding.medium * CGFloat(index))
                    .stacked(at: index, in: accounts.count)
                    .brightness(Double(index) * (1.0 / Double(accounts.count + 1)))
                }
            }
            if accounts.count > 1 {
                DotIndicator(totalDots: accounts.count, index: $page)
                    .offset(x: 0, y: Double((accounts.count + 1) * 10))
            }
        }
    }
}

extension View {
    func stacked(at position: Int, in total: Int) -> some View {
        let offset = Double(total - position)
        return self.offset(x: 0, y: offset * 10)
    }
}

struct AccountPicker_Previews: PreviewProvider {
    static var previews: some View {
        AccountPicker(accounts: .constant([
            WalletUiItem(id: UUID().uuidString, title: "Wallet", balance: 5465.45, description: nil, lines: [
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Needs",
                    percentage: 50,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Wants",
                    percentage: 30,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Saves",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                )
            ], transactions: []),
            WalletUiItem(id: UUID().uuidString, title: "Wallet", balance: 5465.45, description: nil, lines: [
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Needs",
                    percentage: 50,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Wants",
                    percentage: 30,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Saves",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                )
            ], transactions: []),
            WalletUiItem(id: UUID().uuidString, title: "Wallet", balance: 5465.45, description: nil, lines: [
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Needs",
                    percentage: 50,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Wants",
                    percentage: 30,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Saves",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                )
            ], transactions: []),
            WalletUiItem(id: UUID().uuidString, title: "Wallet", balance: 5465.45, description: nil, lines: [
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Needs",
                    percentage: 50,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Wants",
                    percentage: 30,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: UUID().uuidString,
                    title: "Saves",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                )
            ], transactions: [])
        ]), blurBalance: .constant(false)
        )
    }
}
