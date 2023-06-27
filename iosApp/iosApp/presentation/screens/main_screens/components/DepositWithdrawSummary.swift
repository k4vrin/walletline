//
//  DepositWithdrawSummary.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/25/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DepositWithdrawSummary: View {
    
    let depositAmount: Decimal
    let withdrawAmount: Decimal
    var currency: String = "USD"
    var onDepositClick: () -> Void
    var onWithdrawClick: () -> Void
    
    var body: some View {
        HStack(spacing: Padding.defaultPadding) {
            SummaryCard(
                isDeposit: true,
                amount: depositAmount,
                currency: currency,
                onClick: onDepositClick
            )
            
            Spacer(minLength: Padding.smallMedium)
            
            SummaryCard(
                isDeposit: false,
                amount: withdrawAmount,
                currency: currency,
                onClick: onWithdrawClick
            )
        }
    }
}

struct SummaryCard: View {
    let isDeposit: Bool
    let amount: Decimal
    var currency: String = "USD"
    var onClick: () -> Void
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.neutralColor)
                
            VStack(spacing: Padding.defaultPadding) {
                HStack(spacing: Padding.defaultPadding) {
                    ZStack {
                        Circle()
                            .stroke(Color.neutralColorShade2)
                            .frame(width: 40, height: 40)
                        Image(isDeposit ? "ic_deposit" : "ic_withdraw")
                            .renderingMode(.template)
                            .foregroundColor(isDeposit ? .successColor : .errorColor)
                    }
                    Spacer()
                    CurrencyText(
                        amount: amount,
                        currencyCode: currency,
                        symbolFont: .custom(PlusJakartaSans.bold, size: 16),
                        symbolColor: isDeposit ? .successColor : .errorColor,
                        wholePartFont: .custom(PlusJakartaSans.bold, size: 16),
                        wholePartColor: isDeposit ? .successColor : .errorColor,
                        fracPartFont: .custom(PlusJakartaSans.bold, size: 12),
                        fracPartColor: isDeposit ? .successColor : .errorColor
                    )
                }
                .padding(.leading, Padding.medium)
                .padding(.trailing, Padding.smallLarge)
                HStack(spacing: Padding.defaultPadding) {
                    Text(isDeposit ? "Deposit" : "Withdraw")
                        .font(.custom(PlusJakartaSans.medium, size: 12))
                        .foregroundColor(.neutralColorShade4)
                    Spacer()
                    Image("ic_arrow_right")
                        .renderingMode(.template)
                        .foregroundColor(.neutralColorDark)
                }
                .padding(.trailing, Padding.medium)
                .padding(.leading, Padding.extraMedium)
                .padding(.top, Padding.small)
            }
            .padding(.vertical, Padding.medium)
        }
        .onTapGesture(perform: onClick)
        .frame(height: 94)
    }
}

struct DepositWithdrawSummary_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            ZStack {
                Color.neutralColorShade3
                DepositWithdrawSummary(
                    depositAmount: 365.90, withdrawAmount: 200.90
                ) {
                    
                } onWithdrawClick: {
                    
                }
            }
        }
    }
}
