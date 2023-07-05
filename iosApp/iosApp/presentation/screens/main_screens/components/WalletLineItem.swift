//
//  WalletLineItem.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineItem: View {
    
    var title: String
    var percentage: Int64
    var balance: Decimal
    var buttonEnable: Bool = true
    var currencyCode: String = "EUR"
    
    var isSelected: Bool = false
    
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 18)
                .fill(Color.neutralColor)
                .overlay(
                    RoundedRectangle(cornerRadius: 18)
                        .stroke(
                            isSelected ? Color.mainColorShade4 : Color.neutralColorShade6,
                            lineWidth: isSelected ? 2 : 1
                        )
                )
                .frame(height: 88)
                .overlay(
                    RoundedRectangle(cornerRadius: 18)
                        .fill(isSelected ? Color.mainColorShade1 : Color.clear)
                )
            
            
            HStack(spacing: Padding.defaultPadding) {
                
                RoundedRectangle(cornerRadius: Padding.smallMedium)
                    .stroke(Color.neutralColorShade2)
                    .frame(width: 44, height: 44)
                    .padding(.trailing, Padding.smallMedium)
                
                VStack(alignment: .leading, spacing: Padding.small) {
                    Text(title)
                        .headlineSmallStyle()
                        .foregroundColor(.neutralColorDark)
                    
                    Text(percentage, format: .percent)
                        .bodySmallStyle()
                        .foregroundColor(.neutralColorShade3)
                }
                
                Spacer()
                
                CurrencyText(
                    amount: balance,
                    currencyCode: currencyCode,
                    symbolFont: .custom(PlusJakartaSans.semiBold, size: 16),
                    symbolColor: .neutralColorDark,
                    wholePartFont: .custom(PlusJakartaSans.semiBold, size: 24),
                    wholePartColor: .neutralColorDark,
                    fracPartFont: .custom(PlusJakartaSans.semiBold, size: 16),
                    fracPartColor: .neutralColorDark
                )
                .padding(.trailing, Padding.medium)
                
                if buttonEnable {
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
                
            }
            .padding(.horizontal, Padding.medium)
        }
        .animation(.default, value: isSelected)
        
    }
}


struct WalletLineItem_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineItem(
            title: "Savings",
            percentage: 20,
            balance: 333.33,
            isSelected: true
        )
    }
}
