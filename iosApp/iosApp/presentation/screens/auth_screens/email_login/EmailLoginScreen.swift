//
//  MobileLoginScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import shared
import SwiftUI
import UserNotifications

struct EmailLoginScreen: View {
    @State private var isNavActive = false
    @State private var cancellables = Set<AnyCancellable>()
    @State private var permissionGranted = false
    @State private var showAlert = false
    @State private var alertContent = ""
    @State private var dest: AnyView? = nil
    
    @StateObject var viewModel = EmailLoginViewModel()
    
    var body: some View {
        WalletLineAuthBackground { geo in
            NavigationLink(
                destination: dest,
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
                    
                    WalletLineButton(
                        title: NSLocalizedString("Continue", comment: ""),
                        isLoading: viewModel.state.isLoading,
                        action: {
                            viewModel.onEvent(.continueClicked)
                        }
                    )
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
        .alert(alertContent, isPresented: $showAlert, actions: {
            Button("Ok") {}
        })
        .onAppear {
            // Check if we already have permissions to send notifications
            UNUserNotificationCenter.current().getNotificationSettings { settings in
                if settings.authorizationStatus == .authorized {
                    permissionGranted = true
                } else {
                    requestPermission()
                }
            }
            
            viewModel.effect().sink(
                receiveValue: { effect in
                    switch effect {
                    case .enterBySocials:
                        dest = AnyView(SocialLoginScreen())
                        isNavActive = true
                    case .registerSuccessful(let otp):
                        if permissionGranted {
                            NotificationManager.instance.sendOtpNotification(otp: otp)
                        }
                        dest = AnyView(VerifyEmailScreen(email: viewModel.state.email))
                        
                        isNavActive = true
                    case .error(message: let message):
                        alertContent = message
                    }
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
    
    private func requestPermission() {
        NotificationManager.instance.requestPermission(handler: { success, error in
            if success {
                permissionGranted = true
            } else if let error = error {
                print(error.localizedDescription)
            }
        })
    }
}

struct EmailLoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmailLoginScreen()
    }
}
