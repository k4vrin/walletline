//
//  SelectLineSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SelectLineSection: View {
    @Binding var selectedLine: WalletLineUiItem?
    
    var lines: [WalletLineUiItem]
    var currencyCode: String = "USD"
    
    var onAddClick: () -> Void
    
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            
            VStack(spacing: Padding.small) {
                ForEach(lines) { line in
                    WalletLineItem(
                        title: line.title,
                        percentage: line.percentage,
                        balance: line.balance,
                        buttonEnable: false,
                        currencyCode: currencyCode,
                        isSelected: line == selectedLine
                    )
                    .onTapGesture {
                        selectedLine = line
                    }
                }
                
                Button {
                    onAddClick()
                } label: {
                    Text(
                    NSLocalizedString("+ Add new line", comment: "")
                    )
                        .buttonStyle()
                        .foregroundColor(.infoColorShade4)
                }
                .padding()
            }
            .padding(.top, Padding.extraMedium)
        }
    }
}

struct SelectLineSection_Previews: PreviewProvider {
    static var previews: some View {
        SelectLineSection(
            selectedLine: .constant(nil),
            lines: [
                WalletLineUiItem(
                    id: "1",
                    title: "Savings",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: "2",
                    title: "Savings",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                ),
                WalletLineUiItem(
                    id: "3",
                    title: "Savings",
                    percentage: 20,
                    balance: 560.00,
                    description: nil,
                    categories: []
                )
            ],
            onAddClick: {}
        )
    }
}
