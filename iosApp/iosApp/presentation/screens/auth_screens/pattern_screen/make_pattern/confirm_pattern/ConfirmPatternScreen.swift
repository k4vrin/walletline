//
//  ConfirmPatternScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import shared
import SwiftUI

struct ConfirmPatternScreen: View {
    @ObservedObject var viewModel: MakePatternViewModel
    @State private var cancellables = Set<AnyCancellable>()
    @State private var showAlert = false
    @State private var alertText = ""
    @Environment(\.presentationMode) var presentation
    
    @State private var navigateToHomeScreen = false
    var body: some View {
        WalletLineAuthBackground { _ in
            NavigationLink(
                destination: EmailLoginScreen(),
                isActive: $navigateToHomeScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            HStack {
                Text(
                    NSLocalizedString("Set Pattern", comment: "")
                )
                .headlineMediumStyle()
                .foregroundColor(.neutralColor)
                
                Spacer()
                
                SkipButton(onClick: {
                    viewModel.onEvent(.SkipPatternClicked)
                })
            }
            .padding(Padding.medium)
            
            RememberPatternText()
                .padding(.horizontal, Padding.smallLarge)
                .padding(.top, Padding.large)
            
            PatternTitle(
                NSLocalizedString("Confirm your pattern", comment: "")
            )
            .padding(.top, 72)
            
            LockScreenPattern(
                frame: CGRect(x: 0, y: 0, width: 500, height: 500),
                action: { uniqeId, _ in
                    viewModel.onEvent(.ConfirmPatternChange(pattern: String(Int(uniqeId)), isScrollEnabled: true))
                }
            )
            .frame(width: Dimen.lockPatternWidth, height: Dimen.lockPatternHeight)
            
            HStack {
                PatternButton(
                    title: NSLocalizedString("Retry", comment: ""),
                    bgColor: .clear,
                    contentColor: .neutralColor,
                    borderColor: .neutralColor
                ) {
                    viewModel.onEvent(.RetryPatternClicked)
                }
                
                Spacer()
                
                PatternButton(
                    title: NSLocalizedString("Confirm", comment: ""),
                    bgColor: .neutralColor,
                    contentColor: .neutralColorDark,
                    borderColor: .neutralColor
                ) {
                    viewModel.onEvent(.ConfirmPatternClicked)
                }
            }
            .padding(.horizontal, Padding.medium)
        }
        .alert(isPresented: $showAlert, content: {
            Alert(
                title: Text("Error"),
                message: Text(alertText)
            )
        })
        .onAppear {
            viewModel.effect().sink(receiveValue: { effect in
                switch effect {
                case .NavigateToConfirmPattern:
                    doNothing()
                case .ConfirmPatternSuccessful:
                    navigateToHomeScreen = true
                case .BiometricSuccess:
                    doNothing()
                case .ConfirmPatternUnSuccessful(message: let message):
                    showAlert = true
                    if let msg = message as? PatternValidationMessageNotSame {
                        alertText = "Pattern are not the same"
                    } else {
                        alertText = "Something went wrong"
                    }
                    
                case .RetryPattern:
                    presentation.wrappedValue.dismiss()
                case .ShowBiometricPrompt:
                    doNothing()
                case .SkipMakePattern:
                    navigateToHomeScreen = true
                }
            }).store(in: &cancellables)
        }
    }
    
    private func doNothing() {}
}

struct ConfirmPatternScreen_Previews: PreviewProvider {
    static var previews: some View {
        ConfirmPatternScreen(viewModel: MakePatternViewModel())
    }
}
