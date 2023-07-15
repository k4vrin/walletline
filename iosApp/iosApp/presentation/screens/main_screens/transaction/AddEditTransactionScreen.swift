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
    let walletId: String
    let transactionId: String?
    
    @State private var isScrolling: Bool = false
    @State private var dest: AnyView? = nil
    @State private var isNavActive = false
    @State private var cancellables = Set<AnyCancellable>()
    @State private var showPeriodicalConfirmation: Bool = false
    
    @FocusState private var focusField: Bool
    
    @Environment(\.presentationMode) var presentation
    
    @ObservedObject var viewModel: TransactionViewModel
    
    init(walletId: String, transactionId: String? = nil) {
        self.walletId = walletId
        self.transactionId = transactionId
        self.viewModel = TransactionViewModel(transactionId: transactionId)
    }

    private let locale = Locale.current as NSLocale
    
    var body: some View {
        ZStack {
            WalletLineScaffold(isScrolling: $isScrolling, backgroundColor: .neutralColor) { _ in
                
                WalletLineTabRow(
                    isFirstTabSelected: Binding(
                        get: { viewModel.state.isDepositSelected },
                        set: { viewModel.onEvent(.ChangeIsDeposit(isDeposit: $0)) }
                    ),
                    firstTabName: NSLocalizedString("Deposit", comment: ""),
                    secondTabName: NSLocalizedString("Withdraw", comment: "")
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
                        text: Binding(
                            get: { viewModel.state.amount },
                            set: { viewModel.onEvent(.ChangeAmount(amount: $0)) }
                        ),
                        placeHolder: "0.00",
                        leadingIcon: { _ in
                            Text(
                                locale.displayName(
                                    forKey: .currencySymbol,
                                    value: viewModel.state.currencyCode
                                ) ?? "$"
                            )
                            .headlineLargeStyle()
                            .foregroundColor(.neutralColorDark)
                            .frame(width: 24, height: 24)
                        }
                    )
                    .keyboardType(.numbersAndPunctuation)
                    .focused($focusField)
                    .onReceive(
                        Just(viewModel.state.amount)
                    ) { newValue in
                        let filtered = newValue.filter {
                            "0123456789.".contains($0) &&
                                newValue.first != "."
                        }
                        if filtered != newValue {
                            viewModel.onEvent(.ChangeAmount(amount: filtered))
                        }
                    }
                }
                .padding(.horizontal, Padding.medium)
                    
                // some
                ExpandableVStack(
                    expanded: Binding(
                        get: { viewModel.state.showSomeDetail },
                        set: { viewModel.onEvent(.ShowSomeDetial(show: $0)) }
                    ),
                    title: NSLocalizedString("Some Details", comment: "")
                ) {
                    SomeDetailsSection(
                        title: Binding(
                            get: { viewModel.state.title },
                            set: { viewModel.onEvent(.ChangeTitle(title: $0)) }
                        ),
                        line: viewModel.state.selectedLine,
                        categories: viewModel.state.selectedCategories,
                        showLineSheet: Binding(
                            get: { viewModel.state.showLineSheet },
                            set: { viewModel.onEvent(.ShowLineSheet(show: $0)) }
                        ),
                        showCategorySheet: Binding(
                            get: { viewModel.state.showCategorySheet },
                            set: { viewModel.onEvent(.ShowCategorySheet(show: $0)) }
                        ),
                        focus: $focusField
                    )
                }
                .padding(.horizontal, Padding.medium)
                .padding(.top, Padding.extraMedium)
                    
                // More
                ExpandableVStack(
                    expanded: Binding(
                        get: { viewModel.state.showMoreDetail },
                        set: { viewModel.onEvent(.ShowMoreDetial(show: $0)) }
                    ),
                    title: NSLocalizedString("More Details", comment: "")
                ) {
                    MoreDetailsSection(
                        date: viewModel.state.selectedInitialDate,
                        desc: Binding(
                            get: { viewModel.state.desc },
                            set: { viewModel.onEvent(.ChangeDescription(desc: $0)) }
                        ),
                        isTaxIncluded: Binding(
                            get: { viewModel.state.isTaxIncluded },
                            set: { viewModel.onEvent(.ChangeIsTax(isTax: $0)) }
                        ),
                        isPeriodical: Binding(
                            get: { viewModel.state.isPeriodical },
                            set: { viewModel.onEvent(.ChangeIsPeriodical(isPeriodical: $0)) }
                        ),
                        focus: $focusField,
                        onDateClick: {
                            viewModel.onEvent(.ShowDateSheet(show: true))
                        },
                        onPeriodicalClick: { wasPeriodical in
                            
                            if wasPeriodical {
                                showPeriodicalConfirmation = true
                            } else {
                                viewModel.onEvent(.ShowPeriodicalSheet(show: true))
                            }
                        }
                    )
                }
                .padding(.horizontal, Padding.medium)
                    
            } topBar: {
                DefaultTopBar(
                    title: NSLocalizedString("New Transaction", comment: ""),
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
                title: NSLocalizedString("Select Line to Borrow from", comment: ""),
                buttonTitle: NSLocalizedString("Done", comment: ""),
                showSheet: Binding(
                    get: { viewModel.state.showLineSheet },
                    set: { viewModel.onEvent(.ShowLineSheet(show: $0)) }
                )
            ) {
                SelectLineSection(
                    selectedLine: Binding(
                        get: { viewModel.state.selectedLine },
                        set: { viewModel.onEvent(.ChangeLine(line: $0)) }
                    ),
                    lines: viewModel.state.walletLines,
                    onAddClick: {}
                )
                .padding(.horizontal, Padding.medium)
                .padding(.vertical)
            } onDoneClick: {
                viewModel.onEvent(.ShowLineSheet(show: false))
            } onCancelClick: {
                viewModel.onEvent(.CancelLineSelection)
            }
            
            // Category
            BottomSheet(
                title: NSLocalizedString("Select Category", comment: ""),
                buttonTitle: NSLocalizedString("Done", comment: ""),
                showSheet: Binding(
                    get: { viewModel.state.showCategorySheet },
                    set: { viewModel.onEvent(.ShowCategorySheet(show: $0)) }
                )
            ) {
                SelectCategorySection(
                    selectedCategories: Binding(
                        get: { viewModel.state.selectedCategories },
                        set: { viewModel.onEvent(.ChangeCategories(categories: $0)) }
                    ),
                    catagories: viewModel.state.categories,
                    onAddClick: {}
                )
                .padding(.horizontal, Padding.medium)
                .padding(.vertical)
            } onDoneClick: {
                viewModel.onEvent(.ShowCategorySheet(show: false))
            } onCancelClick: {
                viewModel.onEvent(.CancelCategories)
            }
            
            // Periodical
            BottomSheet(
                title: NSLocalizedString("Periodical Withdraw", comment: ""),
                buttonTitle: NSLocalizedString("Done", comment: ""),
                showSheet: Binding(
                    get: { viewModel.state.showPeriodicalSheet },
                    set: { viewModel.onEvent(.ShowPeriodicalSheet(show: $0)) }
                )
            ) {
                PeriodicalWithdrawSection(
                    selectedFrequency: Binding(
                        get: { viewModel.state.selectedFrequency },
                        set: { viewModel.onEvent(.ChangeFrequency(frequency: $0)) }
                    ),
                    selectedCustomFrequency: Binding(
                        get: { viewModel.state.selectedCustomFrequency },
                        set: { viewModel.onEvent(.ChangeDayFreq(frequencies: $0)) }
                    ),
                    selectedStartDate: Binding(
                        get: { viewModel.state.selectedStartDate },
                        set: { viewModel.onEvent(.ChangePeriodicalStartDate(date: $0)) }
                    ),
                    selectedEndDate: Binding(
                        get: { viewModel.state.selectedEndDate },
                        set: { viewModel.onEvent(.ChangePeriodicalEndDate(date: $0)) }
                    ),
                    selectedDuration: Binding(
                        get: { viewModel.state.selectedDuration },
                        set: { viewModel.onEvent(.ChangeDuration(duration: $0)) }
                    ),
                    selectedDurationTimes: Binding(
                        get: { viewModel.state.selectedDurationTimes },
                        set: { viewModel.onEvent(.ChangeDurationTimes(times: $0)) }
                    )
                )
                .padding(.vertical)
            } onDoneClick: {
                viewModel.onEvent(.ShowPeriodicalSheet(show: false))
            } onCancelClick: {
                viewModel.onEvent(.CancelPeriodical)
            }
            
            // Date
            BottomSheet(
                title: NSLocalizedString("Select a date", comment: ""),
                buttonTitle: NSLocalizedString("Done", comment: ""),
                showSheet: Binding(
                    get: { viewModel.state.showDateSheet },
                    set: { viewModel.onEvent(.ShowDateSheet(show: $0)) }
                )
            ) {
                WLDatePicker(
                    selectedDate: Binding(
                        get: { viewModel.state.selectedInitialDate },
                        set: { viewModel.onEvent(.ChangeInitDate(date: $0)) }
                    )
                )
                    .padding(.vertical)
            } onDoneClick: {
                viewModel.onEvent(.ShowDateSheet(show: false))
            } onCancelClick: {
                viewModel.onEvent(.CancelDate)
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
        .onChange(of: viewModel.state.showLineSheet, perform: { _ in
            focusField = false
        })
        .onChange(of: viewModel.state.showCategorySheet, perform: { _ in
            focusField = false
        })
        .onChange(of: viewModel.state.showPeriodicalSheet, perform: { _ in
            focusField = false
            
        })
        .confirmationDialog(NSLocalizedString("Delete Periodical Withdraw?", comment: ""), isPresented: $showPeriodicalConfirmation, actions: {
            Button(NSLocalizedString("Yes", comment: ""), role: .destructive) {
                viewModel.onEvent(.CancelPeriodical)
            }
            Button(NSLocalizedString("Cancel", comment: ""), role: .cancel) {
                viewModel.onEvent(.ChangeIsPeriodical(isPeriodical: true))
            }
        }, message: {
            Text(NSLocalizedString("periodical settings", comment: ""))
                .bodyMediumStyle()
        })
        .navigationBarBackButtonHidden(true)
    }
    

}

struct AddEditTransactionScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddEditTransactionScreen(walletId: "123")
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
            
            if expanded {
                content()
                    .frame(height: expanded ? nil : 0, alignment: .top)
                    .clipped()
            }
            
            Divider()
                .padding(.top, expanded ? nil : 0)
        }
        .background(Color.neutralColor)
        .animation(.spring(), value: expanded)
    }
}
