//
//  TransactionsList.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/25/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum TransactionFilterOrder: CaseIterable, CustomStringConvertible {
    var description: String {
        switch self {
        case .NewestToOldest:
            return "New to Old"
        case .OldestToNewest:
            return "Old to New"
        case .LargerToSmaller:
            return "More to Less"
        case .SmallerToLarger:
            return "Less to More"
        case .OnlyDeposit:
            return "Only Deposit"
        case .OnlyWithdraw:
            return "Only Withdraw"
        }
    }
    
    case NewestToOldest, OldestToNewest, LargerToSmaller, SmallerToLarger, OnlyDeposit, OnlyWithdraw
}

struct TransactionsList: View {
    let transactions: [TransactionUiItem]
    var currencyCode: String = "USD"
    
    @Binding var filter: TransactionFilterOrder
    
    let onEditClick: (String) -> Void
    
    var body: some View {
        VStack(spacing: Padding.small) {
            HStack(spacing: Padding.defaultPadding) {
                Text("Transactions")
                    .titleLargeStyle()
                    .foregroundColor(.neutralColorDark)
                Spacer()
                Menu {
                    ForEach(
                        TransactionFilterOrder.allCases,
                        id: \.self
                    ) { filterCase in

                        Button(
                            action: {
                                filter = filterCase
                            }, label: {
                                HStack {
                                    Text(filterCase.description)
                                        .bodyMediumStyle()
                                    if filter == filterCase {
                                        Image(systemName: "checkmark")
                                    }
                                }
                            }
                        )
                    }
                } label: {
                    HStack {
                        Image("ic_filter")
                            .renderingMode(.template)
                            .foregroundColor(.mainColorShade4)
                        
                        Text("Filter")
                            .bodySmallStyle()
                            .foregroundColor(.mainColorShade4)
                    }
                }
            }
            
            ForEach(transactions) { transaction in
                TransactionItem(
                    transaction: transaction,
                    currencyCode: currencyCode,
                    onEditClick: onEditClick
                )
            }
        }
    }
}

struct TransactionsList_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            ZStack {
                Color.neutralColorShade3
                TransactionsList(
                    transactions: [
                        TransactionUiItem(
                            id: UUID().uuidString,
                            title: "Salary",
                            isDeposit: true,
                            amount: 120,
                            description: "Datarivers",
                            categories: [],
                            date: .now,
                            isTaxInculded: true
                        ),
                        TransactionUiItem(
                            id: UUID().uuidString,
                            title: "Transportation",
                            isDeposit: false,
                            amount: 86.80,
                            description: "Petrol",
                            categories: [],
                            date: .distantPast,
                            isTaxInculded: true
                        ),
                        TransactionUiItem(
                            id: UUID().uuidString,
                            title: "Gym",
                            isDeposit: false,
                            amount: 63.90,
                            description: "Shahin",
                            categories: [],
                            date: .distantPast,
                            isTaxInculded: true
                        )
                    ], filter: .constant(.NewestToOldest), onEditClick: { _ in}
                )
            }
        }
    }
}

struct TransactionItem: View {
    let transaction: TransactionUiItem
    var currencyCode: String = "USD"
    
    @State private var offset = CGSize.zero
    
    let onEditClick: (String) -> Void
    
    var body: some View {
        ZStack {
            HStack(spacing: Padding.defaultPadding) {
                Button {
                    onEditClick(transaction.id)
                } label: {
                    ZStack {
                        RoundedRectangle(cornerRadius: 16)
                            .fill(Color.mainColor)
                        Image("ic_edit")
                            .renderingMode(.template)
                            .foregroundColor(.neutralColor)
                    }
                }
                .frame(width: 43)
                Spacer()
            }
            
            ZStack {
                RoundedRectangle(cornerRadius: 16)
                    .fill(Color.neutralColor)
                
                HStack(spacing: Padding.defaultPadding) {
                    ZStack {
                        Circle()
                            .stroke(Color.neutralColorShade2)
                            .frame(width: 40, height: 40)
                        Image("ic_dolar")
                    }
                    .padding(.trailing, Padding.smallMedium)
                    
                    VStack(alignment: .leading, spacing: Padding.small) {
                        Text(transaction.title)
                            .headlineSmallStyle()
                            .foregroundColor(.neutralColorDark)
                        
                        Text(transaction.description ?? "")
                            .bodySmallStyle()
                            .foregroundColor(.neutralColorShade3)
                    }
                    
                    Spacer()
                    
                    VStack(alignment: .trailing, spacing: Padding.small) {
                        HStack(spacing: Padding.extraSmall) {
                            Text(
                                transaction.isDeposit ? "+" : "-"
                            )
                            .font(.custom(PlusJakartaSans.medium, size: 16))
                            .foregroundColor(transaction.isDeposit ? .successColor : .errorColor)
                            
                            CurrencyText(
                                amount: transaction.amount,
                                currencyCode: currencyCode,
                                symbolFont: .custom(PlusJakartaSans.medium, size: 16),
                                symbolColor: transaction.isDeposit ? .successColor : .errorColor,
                                wholePartFont: .custom(PlusJakartaSans.medium, size: 16),
                                wholePartColor: transaction.isDeposit ? .successColor : .errorColor,
                                fracPartFont: .custom(PlusJakartaSans.semiBold, size: 12),
                                fracPartColor: transaction.isDeposit ? .successColor : .errorColor
                            )
                        }
                        
                        Text(transaction.date.formatted(date: .omitted, time: .shortened))
                            .bodySmallStyle()
                            .foregroundColor(.neutralColorShade3)
                    }
                }
                .padding(.horizontal, Padding.medium)
            }
            .offset(CGSize(width: offset.width, height: 0))
            .simultaneousGesture(
                DragGesture(minimumDistance: 30)
                    .onChanged { gesture in
                        offset = gesture.translation
                    }
                    .onEnded { _ in
                        swipeItem(width: offset.width)
                    }
            )
        }
        
        .animation(.default, value: offset)
        .frame(height: 71)
    }
    
    func swipeItem(width: CGFloat) {
        switch width {
        case 0...50:
            offset = .zero
        case 50...500:
            offset = CGSize(width: 50, height: 0)
        default:
            offset = .zero
        }
    }
}
