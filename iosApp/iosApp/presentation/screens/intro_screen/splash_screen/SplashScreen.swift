//
//  SplashScreen.swift
//  iosApp
//
//  Created by Paria on 12/13/1401 AP.
//  Copyright © 1401 AP orgName. All rights reserved.
//

import Combine
import shared
import SwiftUI

struct SplashScreen: View {
    @StateObject private var viewModel = IntroViewModel()
    @State private var isNavActive = false
    @State private var dest: AnyView? = nil
    @State private var cancellables = Set<AnyCancellable>()

    var body: some View {
        WalletLineBackground { geo in
            NavigationLink(isActive: $isNavActive) {
                dest
            } label: {
                EmptyView()
            }.hidden()

            WalletLineLogoHeader()
                .padding(.top, Dimen.WalletlineLogoTopMargin)
                .padding(.bottom, Dimen.WalletlineLogoBottomMargin)

            Spacer(minLength: geo.size.height / 2)

            if viewModel.state.isLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
                    .foregroundColor(.neutralColor)
                    .padding()
            }

            Text(
                NSLocalizedString("Version 1.0", comment: "")
            )
                .titleSmallStyle()
                .foregroundColor(.neutralColor)
        }
        .onAppear {

            let effect = viewModel.effect()
            effect.sink(
                receiveCompletion: { err in
                    print(err)
                },
                receiveValue: { effect in
                    switch effect {
                    case .UserOnBoarded:
                        doNothing()
                    case .Navigate(userCondition: let userCondition):
                        switch userCondition {
                        case .firsttime:
                            dest = AnyView(OnBoardingScreen(viewModel: viewModel))
                        case .onboarded:
                            dest = AnyView(SocialLoginScreen())
                        case .signedinwithpattern:
                            dest = AnyView(EnterPatternScreen())
                        case .signedinwithfinger:
                            dest = AnyView(EnterPatternScreen())
                        case .signedin:
                            // TODO: Navigate home
                            doNothing()
                        default:
                            doNothing()
                        }

                        isNavActive = true
                    }
                }
            ).store(in: &cancellables)
        }
        .onDisappear {
            cancellables.forEach {
                $0.cancel()
            }
            viewModel.onEvent(.CancelAsyncTasks)
        }
        .animation(.default, value: viewModel.state.isLoading)
    }

    private func doNothing() {}
}

struct SplashScreen_Previews: PreviewProvider {
    static var previews: some View {
        SplashScreen()
    }
}
