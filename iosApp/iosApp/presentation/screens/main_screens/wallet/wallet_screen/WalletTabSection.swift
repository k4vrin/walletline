//
//  WalletTabSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletTabSection: View {
    var isLinesSelected: Bool
    var lines: [WalletLineUiItem]?
    var isAccsEmpty: Bool
    var transactions: [TransactionUiItem]
        
    @Binding var filterOrder: TransactionFilterOrder
        
    let onCreateWalletClicked: () -> Void
    let onCreateLineClicked: () -> Void
        
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            if isLinesSelected, let lines = lines {
                LinesListView(lines: lines)
                    .padding(.horizontal, Padding.medium)
                    
                DashedBorderButton(title: isAccsEmpty ? NSLocalizedString("Create your 1st wallet", comment: "") : NSLocalizedString("Create line", comment: "")) {
                    if isAccsEmpty {
                        onCreateWalletClicked()
                    } else {
                        onCreateLineClicked()
                    }
                }
                .padding([.horizontal, .top], Padding.medium)
            } else {
                DepositWithdrawSummary(
                    depositAmount: 365.90,
                    withdrawAmount: 200.90,
                    onDepositClick: {},
                    onWithdrawClick: {}
                )
                .padding([.horizontal, .top], Padding.medium)
                    
                TransactionsList(
                    transactions: transactions,
                    filter: $filterOrder,
                    onEditClick: { id in
                        print("transId: \(id)")
                    }
                ).simultaneousGesture(DragGesture(minimumDistance: 0), including: .all)
                .padding([.horizontal, .top], Padding.medium)
            }
        }    }
}
