//
//  LoginPattern.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct EnterPatternScreen: View {
    @StateObject var viewModel = LoginPatternViewModel()
    
    @State private var navigateToHomeScreen = false
    @State private var navigateToLockScreen = false
    
    @State private var cancellables = Set<AnyCancellable>()
    
    @State var reset = false
    
    var body: some View {
        WalletLineAuthBackground { _ in
            NavigationLink(
                destination: EmailLoginScreen(),
                isActive: $navigateToHomeScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            NavigationLink(
                destination: EmailLoginScreen(),
                isActive: $navigateToLockScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            Image("ic_lock")
                .renderingMode(.template)
                .foregroundColor(.neutralColor)
                .padding(.top, 96)
            
            PatternTitle(
                NSLocalizedString(
                    viewModel.state.showRemainingAttempts ? "Incorrect pattern drawn" : "Draw unlock pattern",
                    comment: ""
                )
            )
            .padding(.top, 110)
            
            LockScreenPattern(
                frame: CGRect(x: 0, y: 0, width: 500, height: 500),
                resetView: reset,
                action: { _, _ in
                }
            )
            .frame(width: Dimen.lockPatternWidth, height: Dimen.lockPatternHeight)
            
            if viewModel.state.showRemainingAttempts {
                Text(
                    String(
                        format: NSLocalizedString(
                            "if you enter the pattern wrongly",
                            comment: ""
                        ),
                        arguments: [1]
                    )
                )
                .multilineTextAlignment(.center)
                .bodyMediumStyle()
                .foregroundColor(.neutralColorShade2)
                .padding(.horizontal, Padding.large)
            }
        }
        .onAppear {
            viewModel.effect().sink(receiveValue: { effect in
                switch effect {
                case .PatternIsVerified:
                    navigateToHomeScreen = true
                case .LockApp:
                    navigateToLockScreen = true
                case .LockIsExpired:
                    doNothing()
                }
            }).store(in: &cancellables)
        }
        .onDisappear {
            viewModel.onEvent(.ClearAsyncTasks)
            cancellables.forEach {
                $0.cancel()
            }
        }
        .animation(.default, value: viewModel.state.showRemainingAttempts)
    }
    private func doNothing() {}
}

struct LoginPattern_Previews: PreviewProvider {
    static var previews: some View {
        EnterPatternScreen()
    }
}
