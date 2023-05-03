//
//  SocialMediaLoginScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/23/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SocialLoginScreen: View {
    @State private var isNavActive = false
    var body: some View {
        WalletLineBackground {
            NavigationLink(
                destination: EmailLoginScreen(),
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
                        NSLocalizedString("Enter by socials", comment: "Enter by socials")
                    )
                    .padding(.top, Padding.smallLarge)
                    
                    SocialSignInButton(
                        icon: {
                            Image("google_icon")
                        },
                        text: NSLocalizedString("Google Account", comment: "")
                    ) {
                        // onGoogleClick
                    }
                        .padding(.horizontal, Padding.extraMedium)
                        .padding(.top, Padding.smallMedium)
                    
                    SocialSignInButton(
                        icon: {
                            Image("facebook_icon")
                        },
                        text: NSLocalizedString("Facebook", comment: "")
                    ) {
                        // onFacebookClick
                    }
                        .padding(.horizontal, Padding.extraMedium)
                        .padding(.top, Padding.smallMedium)
                    
                    SocialSignInButton(
                        icon: {
                            Image("apple_icon")
                                .renderingMode(.template)
                                .foregroundColor(Color.neutralColor)
                        },
                        text: NSLocalizedString("Apple ID", comment: ""),
                        bgColor: Color.neutralColorShade6,
                        fgColor: Color.neutralColor
                    ) {
                        // onAppleClick
                    }
                        .padding(.horizontal, Padding.extraMedium)
                        .padding(.top, Padding.smallMedium)
                    
                    OrDivider()
                        .padding(.top, Padding.extraMedium)
                    
                    AuthCardArrowText(
                        NSLocalizedString("Enter by email address", comment: "")
                    ) {
                        isNavActive = true
                    }
                    .padding(.top, Padding.medium)
                    .padding(.bottom, Padding.smallLarge)
                }
                .padding(.horizontal, Padding.medium)
            }
            
            DatariversTeamText()
                .padding(.top, Padding.extraLarge)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct SocialMediaLoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        SocialLoginScreen()
    }
}
