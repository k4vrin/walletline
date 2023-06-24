//
//  WalletSetupScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import SwiftUI

struct WalletSetupScreen: View {
    let walletId: String?
    var currencyCode: String = "USD"
    private let locale = Locale.current as NSLocale
    private var isEditWallet: Bool {
        walletId != nil
    }
    
    @State private var cancellables = Set<AnyCancellable>()
    @FocusState private var focusField: Bool
    @State var isScrolling: Bool = false
    @ObservedObject private var viewModel: WalletSetupViewModel
    @Environment(\.presentationMode) var presentation
    
    @State private var showAlert: Bool = false
    @State private var alertTitle: String = ""
    @State private var alertMessage: String = ""
    
    @State private var showDeleteAlert: Bool = false
    
    init(walletId: String?) {
        self.walletId = walletId
        self.viewModel = WalletSetupViewModel(walletId: walletId)
    }
    
    var body: some View {
        ZStack {
            WalletLineScaffold(isScrolling: $isScrolling, backgroundColor: .neutralColor) { _ in
                
                VStack(alignment: .leading, spacing: Padding.defaultPadding) {
                    Text(
                        NSLocalizedString("Title", comment: "")
                    )
                    .titleLargeStyle()
                    .foregroundColor(.neutralColorDark)
                        
                    WalletLineNormalTextField(
                        text: Binding(
                            get: {
                                viewModel.state.title
                            },
                            set: { value in
                                viewModel.onEvent(.ChangeTitle(title: value))
                            }
                        ),
                        placeHolder: NSLocalizedString("Wallet title", comment: "")
                    ) { _ in
                        Image("ic_title")
                            .renderingMode(.template)
                            .foregroundColor(.neutralColorDark)
                    }
                    .focused($focusField)
                    .padding(.top, Padding.small)
                }
                .padding(.top, Padding.small)
                .padding(.horizontal, Padding.medium)
                    
                VStack(alignment: .leading, spacing: Padding.defaultPadding) {
                    Text(
                        NSLocalizedString("Balance", comment: "")
                    )
                    .titleLargeStyle()
                    .foregroundColor(.neutralColorDark)
                        
                    WalletLineNormalTextField(
                        text: Binding(
                            get: {
                                viewModel.state.balance
                            },
                            set: { value in
                                viewModel.onEvent(.ChangeBalance(balance: value))
                            }
                        ),
                        placeHolder: "0.0"
                    ) { _ in
                        Text(locale.displayName(forKey: .currencySymbol, value: currencyCode) ?? "$")
                            .headlineLargeStyle()
                            .foregroundColor(.neutralColorDark)
                            .frame(width: 24, height: 24)
                    }
                    .keyboardType(.numbersAndPunctuation)
                    .focused($focusField)
                    .padding(.top, Padding.small)
                    .onReceive(
                        Just(viewModel.state.balance)
                    ) { newValue in
                        let filtered = newValue.filter {
                            "0123456789.".contains($0) &&
                                newValue.first != "."
                        }
                        if filtered != newValue {
                            viewModel.onEvent(.ChangeBalance(balance: filtered))
                        }
                    }
                }
                .padding(.top, Padding.extraMedium)
                .padding(.horizontal, Padding.medium)
                    
//                DashedBorderButton(title: "Set Lines") {}
//                    .padding(.horizontal, Padding.medium)
//                    .padding(.top, Padding.extraMedium)
                    
                VStack(alignment: .leading, spacing: Padding.defaultPadding) {
                    HStack(alignment: .firstTextBaseline, spacing: Padding.defaultPadding) {
                        Text(
                            NSLocalizedString("Description", comment: "")
                        )
                        .titleLargeStyle()
                        .foregroundColor(.neutralColorDark)
                        .padding(.trailing, Padding.extraSmall)
                        Text(
                            NSLocalizedString("(Optional)", comment: "")
                        )
                        .bodySmallStyle()
                        .foregroundColor(.neutralColorShade3)
                    }
                        
                    WalletLineTextEditor(
                        text: Binding(
                            get: {
                                viewModel.state.desc ?? ""
                            },
                            set: { value in
                                viewModel.onEvent(.ChangeDescription(desc: value))
                            }
                        ),
                        placeHolder: NSLocalizedString("Describe your category", comment: "")
                    )
                    .focused($focusField)
                    .padding(.top, Padding.small)
                }
                .padding(.top, Padding.extraMedium)
                .padding(.bottom, Padding.large)
                .padding(.horizontal, Padding.medium)
                
                if isEditWallet {
                    VStack(alignment: .center, spacing: Padding.defaultPadding) {
//                        Button {} label: {
//                            ZStack {
//                                Group {
//                                    HStack {
//                                        Image("ic_share")
//                                            .renderingMode(.template)
//
//                                        Text("Share with contacts")
//                                            .headlineSmallStyle()
//                                    }
//                                    .foregroundColor(.mainColorShade4)
//                                    .padding(.vertical, Padding.small)
//                                    .padding(.horizontal, Padding.smallMedium)
//
//                                }.background(
//                                    RoundedRectangle(cornerRadius: 4)
//                                        .fill(Color.mainColorShade1)
//                                )
//                                .frame(height: 36)
//                            }
//                        }
                        
                        Button {
                            showDeleteAlert = true
                        } label: {
                            Text(
                                NSLocalizedString("Delete Wallet", comment: "")
                            )
                            .bodySmallStyle()
                            .foregroundColor(.mainColorShade4)
                            .padding(.top, Padding.extraMedium)
                        }
                    }
                }
                    
            } topBar: {
                DefaultTopBar(
                    title: isEditWallet ? NSLocalizedString("Edit Wallet", comment: "") : NSLocalizedString("Wallet Setup", comment: ""),
                    isMoreEnable: false,
                    isScrolling: $isScrolling,
                    menu: { EmptyView() },
                    onBackClick: {
                        self.presentation.wrappedValue.dismiss()
                    }
                )
            }
            
            VStack {
                Spacer()
                WalletLineButton(title: isEditWallet ? NSLocalizedString("Save", comment: "") : NSLocalizedString("Apply", comment: "")) {
                    if isEditWallet {
                        viewModel.onEvent(.SaveClicked)
                    } else {
                        viewModel.onEvent(.ApplyClicked)
                    }
                }
                .shadow(
                    color: .mainColor.opacity(0.5),
                    radius: 8,
                    y: 8
                )
                .padding(.all, Padding.medium)
                .disabled(!viewModel.state.isApplyEnable)
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
        .alert(alertTitle, isPresented: $showAlert) {
            Button(NSLocalizedString("Ok", comment: ""), action: {})
        } message: {
            Text(alertMessage)
        }
        .alert(NSLocalizedString("Delete Wallet", comment: ""), isPresented: $showDeleteAlert) {
            Button(NSLocalizedString("Yes", comment: ""), role: .destructive, action: { viewModel.onEvent(.DeleteWallet) })
        } message: {
            Text(NSLocalizedString("Are you sure?", comment: ""))
        }
        .onAppear {
            let effect = viewModel.effect()
            effect.sink(receiveCompletion: { _ in }, receiveValue: { effect in
                switch effect {
                case .NavigateToWalletScreen:
                    DispatchQueue.main.async {
                        self.presentation.wrappedValue.dismiss()
                    }
                case .ErrorInWalletCreation(err: let err):
                    switch err {
                    case .errorhappened:
                        alertTitle = NSLocalizedString("Error!", comment: "")
                        alertMessage = NSLocalizedString("Some unknown error", comment: "")
                        showAlert = true
                    case .namealreadyexist:
                        alertTitle = NSLocalizedString("Error!", comment: "")
                        alertMessage = NSLocalizedString("A wallet with this name", comment: "")
                        showAlert = true
                    default:
                        doNothing()
                    }
                }
            })
            .store(in: &cancellables)
        }
        .onDisappear {
            viewModel.onEvent(.CancelTasks)
            cancellables.forEach {
                $0.cancel()
            }
        }
        .navigationBarBackButtonHidden(true)
    }
    
    private func doNothing() {}
}

struct WalletSetupScreen_Previews: PreviewProvider {
    static var previews: some View {
        WalletSetupScreen(walletId: nil)
    }
}
