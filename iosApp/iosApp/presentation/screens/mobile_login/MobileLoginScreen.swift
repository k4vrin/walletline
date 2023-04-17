//
//  MobileLoginScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MobileLoginScreen: View {
    @State var emailText: String = ""
    @State private var isNavActive = false
    var body: some View {
        WalletLineBackground {
            NavigationLink(
                destination: MobileVerifyScreen(),
                isActive: $isNavActive
            ) {
                EmptyView()
            }
            .hidden()
            VStack(alignment: .center, spacing: Padding.defaultPadd) {
                WalletLineLogoHeader()
                    .padding(.top, Dimen.walletlineLogoTopMargin)
                    .padding(.bottom, Dimen.walletlineLogoBottomMargin)
                
                AuthCard {
                    AuthCardTitle(
                        NSLocalizedString("Enter by email address", comment: "Enter by socials")
                    )
                    .padding(.top, Padding.smallLarge)
                    
                    EmailTextField(text: $emailText)
                        .padding(.top, Padding.extraMedium)
                        .padding(.horizontal, Padding.extraMedium)
                    
                    Button {
                        
                    } label: {
                        Text("Continue")
                            .primaryButtonStyle()
                    }
                    .padding(.top, Padding.extraMedium)
                    .padding(.horizontal, Padding.extraMedium)
                    
                    OrDivider()
                        .padding(.top, Padding.extraMedium)
                    
                    AuthCardArrowText("Enter by socials") {
                        
                    }
                    .padding(.vertical, Padding.extraMedium)
                    
                    
                }
                .padding(.horizontal, Padding.medium)
                
            }
            
            DatariversTeamText()
                .padding(.top, Padding.extraLarge)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct MobileLoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        MobileLoginScreen()
    }
}
