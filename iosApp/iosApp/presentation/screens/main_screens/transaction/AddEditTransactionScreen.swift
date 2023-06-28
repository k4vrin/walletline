//
//  AddEditTransactionScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import SwiftUI

struct AddEditTransactionScreen: View {
    @State private var isScrolling: Bool = false
    @State private var dest: AnyView? = nil
    @State private var isNavActive = false
    @State private var cancellables = Set<AnyCancellable>()
    @State private var isDepositSelected: Bool = false
    @State private var amount: String = ""
    @State private var currencyCode: String = "USD"
    @State private var showSomeDetail: Bool = false
    
    @FocusState private var focusField: Bool
    
    private let locale = Locale.current as NSLocale
    
    var body: some View {
        ZStack {
            WalletLineScaffold(isScrolling: $isScrolling) { _ in
                
                VStack(spacing: Padding.defaultPadding) {
                    WalletLineTabRow(
                        isFirstTabSelected: $isDepositSelected,
                        firstTabName: "Deposit",
                        secondTabName: "Withdraw"
                    )
                    .padding(.vertical)
                    .padding(.horizontal, Padding.medium)
                    
                    VStack(alignment: .leading, spacing: Padding.small) {
                        Text(
                            NSLocalizedString("Amount", comment: "")
                        )
                        .titleLargeStyle()
                        .foregroundColor(.neutralColorDark)
                        
                        WalletLineNormalTextField(
                            text: $amount,
                            placeHolder: "0.00",
                            leadingIcon: { _ in
                                Text(locale.displayName(forKey: .currencySymbol, value: currencyCode) ?? "$")
                                    .headlineLargeStyle()
                                    .foregroundColor(.neutralColorDark)
                                    .frame(width: 24, height: 24)
                            }
                        )
                        .keyboardType(.numbersAndPunctuation)
                        .focused($focusField)
                        .onReceive(
                            Just(amount)
                        ) { newValue in
                            let filtered = newValue.filter {
                                "0123456789.".contains($0) &&
                                    newValue.first != "."
                            }
                            if filtered != newValue {
                                amount = filtered
                            }
                        }
                    }
                    .padding(.horizontal, Padding.medium)
                    
                    ExpandableVStack(expanded: $showSomeDetail, title: "Some Details") {
                        ZStack {
                            Rectangle()
                                .fill(.yellow)
                        }
                    }
                    .padding(.horizontal, Padding.medium)
                    .padding(.top, Padding.extraMedium)
                    
                    ExpandableVStack(expanded: $showSomeDetail, title: "More Details") {
                        ZStack {
                            Rectangle()
                                .fill(.yellow)
                        }
                    }
                    .padding(.horizontal, Padding.medium)
                }
                
            } topBar: {
                DefaultTopBar(
                    title: "New Transaction",
                    isScrolling: $isScrolling,
                    menu: {
                        EmptyView()
                    },
                    onBackClick: {}
                )
            }
            
            VStack {
                Spacer()
                WalletLineButton(
                    title: NSLocalizedString("Add", comment: "")
                ) {}
                    .shadow(
                        color: .mainColor.opacity(0.5),
                        radius: 8,
                        y: 8
                    )
                    .padding(.all, Padding.medium)
            }
        }
        .animation(.default, value: showSomeDetail)
    }
}

struct AddEditTransactionScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddEditTransactionScreen()
    }
}

struct ExpandableVStack<Content: View>: View {
    @Binding var expanded: Bool
    var title: String
    
    @ViewBuilder var content: () -> Content
    
    let tabHeight: CGFloat = 56
    
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.defaultPadding) {
                Text(title)
                    .titleLargeStyle()
                    .foregroundColor(.neutralColorDark)
                
                Spacer()
                
                Image("ic_arrow")
                    .rotationEffect(.degrees(expanded ? 90 : -90))
            }
            .frame(height: tabHeight)
            .onTapGesture {
                expanded.toggle()
            }
            
            content()
            Divider()
        }
        .frame(height: expanded ? nil : tabHeight)
            
        
    }
}
