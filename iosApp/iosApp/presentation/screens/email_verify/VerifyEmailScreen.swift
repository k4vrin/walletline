//
//  MobileVerifyScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/22/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import shared
import SwiftUI

struct VerifyEmailScreen: View {
    let email: String
    @StateObject var viewModel = VerifyEmailViewModel()
    @State private var goToPatternScreen = false
    @State private var cancellables = Set<AnyCancellable>()
    @State private var showAlert = false
    @State private var alertContent = ""
    
    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        WalletLineBackground {
            NavigationLink(
                destination: PatternScreen(),
                isActive: $goToPatternScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            VStack(spacing: Padding.defaultPadding) {
                WalletLineLogoHeader()
                    .padding(.top, Dimen.WalletlineLogoTopMargin)
                    .padding(.bottom, Dimen.WalletlineLogoBottomMargin)
                
                AuthCard {
                    AuthCardTitle(
                        NSLocalizedString("Verify Email Address", comment: "")
                    )
                    .padding(.top, Padding.smallLarge)
                    .padding(.bottom, Padding.extraMedium)
                    
                    Text(
                        NSLocalizedString("Enter the 4-digit code we sent to", comment: "")
                    )
                    .bodyMediumStyle()
                    .foregroundColor(.neutralColorShade3)
                    
                    HStack {
                        Text(email)
                            .headlineSmallStyle()
                            .foregroundColor(.neutralColorShade4)
                        Image("ic_edit")
                            .renderingMode(.template)
                            .foregroundColor(.neutralColorShade4)
                    }
                    .onTapGesture {
                        viewModel.onEvent(.OnChangeEmailClicked)
                    }
                    .padding(.top, Padding.medium)
                    
                    OtpTextField(
                        otpText: Binding(
                            get: { viewModel.state.otp },
                            set: { otp, _ in
                                viewModel.onEvent(VerifyEmailEvent.OTPChange(text: otp))
                            }
                        )
                    )
                    .padding(.top, Padding.smallLarge)
                    
                    if viewModel.state.isTimerActive {
                        VerifyEmailTimer(
                            time: Binding(
                                get: { viewModel.state.timer },
                                set: { _, _ in }
                            )
                        )
                        .padding(.top, Padding.smallLarge)
                    } else {
                        VStack {
                            Text(
                                NSLocalizedString("Didn't received the code?", comment: "")
                            )
                            .foregroundColor(.neutralColorShade3)
                            .bodySmallStyle()
                            
                            Button {
                                viewModel.onEvent(.OnResendClicked)
                            } label: {
                                UnderlineText(
                                    str: NSLocalizedString("Resend Email", comment: ""),
                                    textColor: .mainColorShade4
                                )
                                .bodySmallStyle()
                            }
                            .padding(.top, Padding.small)
                        }
                        .padding(.top, Padding.extraMedium)
                    }
                    
                    AuthButton(
                        title: NSLocalizedString("Continue", comment: ""),
                        isLoading: viewModel.state.isLoading,
                        action: {
                            viewModel.onEvent(.OnContinueClicked)
                        }
                    )
                    .padding(.vertical, Padding.smallLarge)
                    .padding(.horizontal, Padding.extraMedium)
                }
                .padding(.horizontal, Padding.medium)
                
                TermsAndConditionSection()
                    .padding(.horizontal, Padding.smallLarge)
                    .padding(.top, Padding.extraLarge)
                    .padding(.bottom, Padding.small)
            }
        }
        .onAppear {
            viewModel.onEvent(.UpdateEmail(email: email))
            viewModel.effect().sink(
                receiveValue: { effect in
                    switch effect {
                    case .NavigateToEmail:
                        self.presentation.wrappedValue.dismiss()
                    case .NavigateToPattern:
                        break
                    case .ResendOTP(otp: let otp):
                        NotificationManager.instance.sendOtpNotification(otp: otp)
                    case .ShowPolicy:
                        break
                    case .ShowTerms:
                        break
                    case .VerifySuccessful:
                        break
                    case .Error(message: let message):
                        alertContent = message
                    }
                }
            )
            .store(in: &cancellables)
        }
        .alert(alertContent, isPresented: $showAlert, actions: {
            Button("Ok") {}
        })
        .onDisappear {
            cancellables.forEach { pub in
                pub.cancel()
            }
            viewModel.onEvent(.cancelAsyncTask)
        }
        .animation(.default, value: viewModel.state.isTimerActive)
        .navigationBarBackButtonHidden(true)
        .background(Color.backgroundColor)
    }
}

struct VerifyEmailScreen_Previews: PreviewProvider {
    static var previews: some View {
        VerifyEmailScreen(email: "test@test.com")
    }
}
