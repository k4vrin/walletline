//
//  TransactionUiItem.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/25/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct TransactionUiItem: Hashable, Identifiable {
    let id: String
    let title: String
    let isDeposit: Bool
    let amount: Decimal
    let description: String?
    let categories: [String]
    let date: Date
    let isTaxInculded: Bool
}
