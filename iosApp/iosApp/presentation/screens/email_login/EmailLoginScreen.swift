//
//  MobileLoginScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct EmailLoginScreen: View {
    @State private var isNavActive = false
    @State private var cancellables = Set<AnyCancellable>()
    
    @ObservedObject var viewModel: EmailLoginViewModel
    
    init(isNavActive: Bool = false) {
        self.isNavActive = isNavActive
        self.viewModel = EmailLoginViewModel()
    }
    
    var body: some View {
        WalletLineBackground {
            NavigationLink(
                destination: MobileVerifyScreen(),
                isActive: $isNavActive
            ) {
                EmptyView()
            }
            .hidden()
            VStack(alignment: .center, spacing: Padding.defaultPadding) {
                WalletLineLogoHeader()
                    .padding(.top, Dimen.WalletlineLogoTopMargin)
                    .padding(.bottom, Dimen.WalletlineLogoBottomMargin)
                
                AuthCard {
                    AuthCardTitle(
                        NSLocalizedString("Enter by email address", comment: "Enter by socials")
                    )
                    .padding(.top, Padding.smallLarge)
                    
                    EmailTextField(
                        text: Binding(
                            get: { viewModel.state.email },
                            set: { email, _ in
                                viewModel.onEvent(.emailChange(email: email))
                            }
                        ),
                        error: getErrorMessage(from: viewModel.state.emailError)
                    )
                        .padding(.top, Padding.extraMedium)
                        .padding(.horizontal, Padding.extraMedium)
                    
                    Button {
                        viewModel.onEvent(.continueClicked)
                    } label: {
                        Text(
                            NSLocalizedString("Continue", comment: "")
                        )
                            .primaryButtonStyle()
                    }
                    .padding(.top, Padding.extraMedium)
                    .padding(.horizontal, Padding.extraMedium)
                    
                    OrDivider()
                        .padding(.top, Padding.extraMedium)
                    
                    AuthCardArrowText(
                        NSLocalizedString("Enter by socials", comment: "")
                    ) {
                        viewModel.onEvent(.enterBySocials)
                    }
                        .padding(.vertical, Padding.extraMedium)
                }
                .padding(.horizontal, Padding.medium)
            }
            
            DatariversTeamText()
                .padding(.top, Padding.extraLarge)
        }
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.effect().sink(
                receiveValue: { effect in
                    print(effect)
                }
            )
            .store(in: &cancellables)
        }
        .onDisappear {
            cancellables.forEach { pub in
                pub.cancel()
            }
            viewModel.onEvent(.cancelAsyncTasks)
        }
    }
    
    private func getErrorMessage(from: EmailValidationMessage?) -> String? {
        switch from {
        case is EmailValidationMessageDynamic:
            return (from as! EmailValidationMessageDynamic).message
        case is EmailValidationMessageNotEmpty:
            return "Can't be empty"
        case is EmailValidationMessageNotValid:
            return "Not a valid email"
        default: return nil
        }
    }
}

struct EmailLoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmailLoginScreen()
    }
}
