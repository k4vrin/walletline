//
//  LockAppScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/29/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LockAppScreen: View {
    @State private var navigateToHomeScreen = false
    var body: some View {
        WalletLineBackground { _ in
            NavigationLink(
                destination: EmailLoginScreen(),
                isActive: $navigateToHomeScreen
            ) {
                EmptyView()
            }
            .hidden()
            
            
            Image("ic_timer")
                .renderingMode(.template)
                .foregroundColor(.neutralColor)
                .padding(.top, 88)
            
            
            Text(
                NSLocalizedString("The app is locked temporarily", comment: "")
            )
            .foregroundColor(.neutralColorShade1)
            .multilineTextAlignment(.center)
            .padding(.top, Padding.smallLarge)
            
            LockTimer(size: 211)
                .frame(maxWidth: .infinity)
                .padding(.top, 112)
            
        }
    }
}

struct LockAppScreen_Previews: PreviewProvider {
    static var previews: some View {
        LockAppScreen()
    }
}
