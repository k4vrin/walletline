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
    @State private var showMoreDetail: Bool = false
    @State private var transactionTitle: String = ""
    @State private var line: WalletLineUiItem? = nil
    @State private var walletLines: [WalletLineUiItem] = [
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
    ]
    @State private var showLineSheet: Bool = false
    @State private var showCategorySheet: Bool = false
    @State private var categories = ["Gym", "Gym2", "Gym4", "Gymsda", "Gym5", "Gym6", "Gym7"]
    @State private var selectedCategories: Set<String> = []
    
    @FocusState private var focusField: Bool
    
    @Environment(\.presentationMode) var presentation
    
    private let locale = Locale.current as NSLocale
    
    var body: some View {
        ZStack {
            WalletLineScaffold(isScrolling: $isScrolling, backgroundColor: .neutralColor) { _ in
                
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
                    SomeDetailsSection(
                        title: $transactionTitle,
                        line: $line,
                        showLineSheet: $showLineSheet,
                        showCategorySheet: $showCategorySheet,
                        focus: $focusField
                    )
                }
                .padding(.horizontal, Padding.medium)
                .padding(.top, Padding.extraMedium)
                    
                ExpandableVStack(expanded: $showMoreDetail, title: "More Details") {
                    ZStack {
                        Rectangle()
                            .fill(.yellow)
                    }
                }
                .padding(.horizontal, Padding.medium)
                    
            } topBar: {
                DefaultTopBar(
                    title: "New Transaction",
                    isMoreEnable: false,
                    isScrolling: $isScrolling,
                    menu: { EmptyView() },
                    onBackClick: {
                        self.presentation.wrappedValue.dismiss()
                    }
                )
            }
            .background(Color.neutralColor)
            
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
            
            // Line
            BottomSheet(
                buttonTitle: "Done",
                showSheet: $showLineSheet
            ) {
                SelectLineSection(
                    selectedLine: $line,
                    lines: walletLines,
                    onAddClick: {},
                    onCancelClick: {
                        line = nil
                        showLineSheet.toggle()
                    }
                )
                .padding(.horizontal, Padding.medium)
                .padding(.vertical)
            } onDoneClick: {
                showLineSheet.toggle()
            } onCancelClick: {
                line = nil
                showLineSheet.toggle()
            }
            
            // Category
            BottomSheet(
                buttonTitle: "Done",
                showSheet: $showCategorySheet
            ) {
                SelectCategorySection(
                    selectedCategories: $selectedCategories,
                    catagories: categories,
                    onAddClick: {},
                    onCancelClick: {
                        showCategorySheet.toggle()
                        selectedCategories = []
                    }
                )
                .padding(.horizontal, Padding.medium)
                .padding(.vertical)
            } onDoneClick: {
                showCategorySheet.toggle()
            } onCancelClick: {
                showCategorySheet.toggle()
                selectedCategories = []
            }
        }
        .toolbar {
            ToolbarItem(placement: .keyboard) {
                Button(
                    NSLocalizedString("Done", comment: "")
                ) {
                    focusField.toggle()
                }
                .frame(maxWidth: .infinity, alignment: .trailing)
            }
        }
        .animation(.default, value: showSomeDetail)
        .animation(.default, value: showMoreDetail)
        .onChange(of: showLineSheet, perform: { _ in
            focusField = false
        })
        .onChange(of: showCategorySheet, perform: { _ in
            focusField = false
        })
        .navigationBarBackButtonHidden(true)
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
                .frame(height: expanded ? nil : 0, alignment: .top)
                .clipped()
            
            Divider()
                .padding(.top, expanded ? nil : 0)
        }
        .background(Color.neutralColor)
    }
}
