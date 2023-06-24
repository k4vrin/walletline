//
//  PatternScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct CreatePatternScreen: View {
    
    @StateObject private var viewModel = MakePatternViewModel()
    
    @State private var cancellables = Set<AnyCancellable>()
    
    @State private var goToConfirmScreen = false
    
    var body: some View {
        WalletLineAuthBackground { _ in
            NavigationLink(
                destination: ConfirmPatternScreen(viewModel: viewModel),
                isActive: $goToConfirmScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            VStack(alignment: .center, spacing: Padding.defaultPadding) {
                RememberPatternText()
                    .padding(.horizontal, Padding.smallLarge)
                    .padding(.top)
                
                PatternTitle(
                    NSLocalizedString(
                        "Create your pattern",
                        comment: ""
                    )
                )
                .padding(.top, Padding.large)
                
                LockScreenPattern(
                    frame: CGRect(x: 0, y: 0, width: 500, height: 500),
                    action: { uniqeId, cellsInOrder in
                        viewModel.onEvent(.PatternChanged(pattern: String(Int(uniqeId)), isScrollEnabled: true))
                    }
                )
                .frame(width: Dimen.lockPatternWidth, height: Dimen.lockPatternHeight)
                
                PatternButton(
                    title: NSLocalizedString("Continue", comment: "")
                ) {
                    viewModel.onEvent(.ContinueClicked)
                }
                    .padding(.horizontal, Padding.medium)
                
                OrDivider(
                    text: NSLocalizedString("or continue with", comment: "")
                )
                .padding(.horizontal, Padding.medium)
                .padding(.top, Padding.smallLarge)
                
                FingerPrintButton{
                    viewModel.onEvent(.FingerPrintClicked)
                }
                    .padding(.top, Padding.smallLarge)
                    .padding(.horizontal, Padding.medium)
                
                HStack {
                    Text(
                        NSLocalizedString("Prefer less secure?", comment: "")
                    )
                    .bodyMediumStyle()
                    .foregroundColor(.neutralColorShade2)
                    
                    
                    Button {
                        viewModel.onEvent(.SkipPatternClicked)
                    } label: {
                        Text(
                            NSLocalizedString("Skip", comment: "")
                        )
                        .bodyMediumStyle()
                        .foregroundColor(.neutralColor)
                    }
                    
                }
                .padding(.top, Padding.extraMedium)
            }
        }
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.effect().sink(receiveValue: { effect in
                switch effect {
                    
                case .NavigateToConfirmPattern:
                    goToConfirmScreen = true
                case .ConfirmPatternSuccessful:
                    doNothing()
                case .BiometricSuccess:
                    //TODO: navigate to home
                    doNothing()
                case .ConfirmPatternUnSuccessful(message: _):
                    doNothing()
                case .RetryPattern:
                    doNothing()
                case .ShowBiometricPrompt:
                    print("Call")
                    BiometricClient().showBiometricPrompt {
                        
                    } onError: { errMessage in
                        print(errMessage)
                    }
                case .SkipMakePattern:
                    //TODO: navigate to home
                    doNothing()
                }
            }).store(in: &cancellables)
        }
        .onDisappear {
            cancellables.forEach {
                $0.cancel()
            }
        }
    }
    
    private func doNothing() {}
}

struct PatternScreen_Previews: PreviewProvider {
    static var previews: some View {
        CreatePatternScreen()
    }
}
