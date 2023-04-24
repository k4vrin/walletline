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
            
            VStack(alignment: .center, spacing: 0) {
                WalletLineLogoHeader()
                    .padding(.top, Dimen.walletlineLogoTopMargin)
                    .padding(.bottom, Dimen.walletlineLogoBottomMargin)
                
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
                    ) {}
                        .padding(.horizontal, Padding.extraMedium)
                        .padding(.top, Padding.smallMedium)
                    
                    SocialSignInButton(
                        icon: {
                            Image("facebook_icon")
                        },
                        text: NSLocalizedString("Facebook", comment: "")
                    ) {}
                        .padding(.horizontal, Padding.extraMedium)
                        .padding(.top, Padding.smallMedium)
                    
                    SocialSignInButton(
                        icon: {
                            Image("apple_icon")
                                .renderingMode(.template)
                                .foregroundColor(Color.surfaceColor)
                        },
                        text: NSLocalizedString("Apple ID", comment: ""),
                        bgColor: Color.onSurfaceColor,
                        fgColor: Color.surfaceColor
                    ) {}
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
