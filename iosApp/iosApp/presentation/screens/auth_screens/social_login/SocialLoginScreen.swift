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
    let googleSignInClient = GoogleSignInClient()
    let facebookSignInClient = FacebookSignInClient()
    
    @StateObject var viewModel = SocialLoginViewModel()
    
    var body: some View {
        WalletLineAuthBackground { geo in
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
                    .padding(.bottom, Padding.extraMedium)
                    
                    SocialSignInButton(
                        icon: {
                            Image("google_icon")
                        },
                        text: NSLocalizedString("Google Account", comment: "")
                    ) {
                        googleSignInClient.signIn { signInResult in
                            viewModel.onEvent(.SignInWithGoogle(tokens: signInResult))
                        }
                    }
                        .padding(.horizontal, Padding.extraMedium)
                        
                    
                    SocialSignInButton(
                        icon: {
                            Image("facebook_icon")
                        },
                        text: NSLocalizedString("Facebook", comment: "")
                    ) {
//                        googleSignInClient.signOut()
                        facebookSignInClient.signIn { tokens in
                            viewModel.onEvent(.SignInWithFacebook(tokens: tokens))
                        }
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
                        bgColor: Color.neutralColorDark,
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
