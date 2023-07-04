//
//  LinesListView.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LinesListView: View {
    var lines: [WalletLineUiItem]
    
    var body: some View {
        VStack(spacing: Padding.small) {
            ForEach(lines, id: \.title) { line in
                WalletLineItem(
                    title: line.title,
                    percentage: line.percentage,
                    balance: line.balance
                )
            }
        }
    }
}

struct LinesListView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            ZStack {
                Color.neutralColorShade3
                LinesListView(
                    lines: [
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
                    ]
                )
            }
            
        }
    }
}



