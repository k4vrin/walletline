//
//  WalletLineUiItem.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct WalletLineUiItem: Hashable, Identifiable {
    let id: String
    let title: String
    let percentage: Int64
    let balance: Decimal
    let description: String?
    let categories: [String]
}
