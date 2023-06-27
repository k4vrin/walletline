//
//  WalletAccount.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct WalletUiItem: Hashable, Identifiable {
    let id: String
    let title: String
    let balance: Decimal
    let description: String?
    let lines: [WalletLineUiItem]
    let transactions: [TransactionUiItem]
}
