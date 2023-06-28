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
        VStack(spacing: Padding.defaultPadding) {
            ForEach(lines, id: \.title) { line in
                ZStack {
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color.neutralColor)
                        .frame(height: 88)
                        
                    
                    HStack(spacing: Padding.defaultPadding) {
                        
                        RoundedRectangle(cornerRadius: Padding.smallMedium)
                            .stroke(Color.neutralColorShade2)
                            .frame(width: 44, height: 44)
                            .padding(.trailing, Padding.smallMedium)
                        
                        VStack(alignment: .leading, spacing: Padding.small) {
                            Text(line.title)
                                .headlineSmallStyle()
                                .foregroundColor(.neutralColorDark)
                            
                            Text(line.percentage, format: .percent)
                                .bodySmallStyle()
                        }
                        
                        Spacer()
                        
                        CurrencyText(
                            amount: line.balance,
                            symbolFont: .custom(DMSans.bold, size: 16),
                            symbolColor: .neutralColorDark,
                            wholePartFont: .custom(DMSans.bold, size: 24),
                            wholePartColor: .neutralColorDark,
                            fracPartFont: .custom(DMSans.bold, size: 16),
                            fracPartColor: .neutralColorDark
                        )
                        .padding(.trailing, Padding.medium)
                        
                        Button {
                            
                        } label: {
                            ZStack {
                                Circle()
                                    .stroke(Color.neutralColorShade2)
                                    .frame(width: 48, height: 48)
                                Image(systemName: "arrow.forward")
                                    .renderingMode(.template)
                                    .foregroundColor(.neutralColorDark)
                            }
                        }
                        
                    }
                    .padding(.horizontal, Padding.medium)
                }
                .padding(.vertical, Padding.extraSmall)
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


