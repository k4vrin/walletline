//
//  RegistrationFinishedScreen.swift
//  iosApp
//
//  Created by Paria on 12/17/1401 AP.
//  Copyright © 1401 AP orgName. All rights reserved.
//

import SwiftUI

struct RegistrationFinishedScreen: View {
    var body: some View {
        ScrollView {
            ZStack(alignment: .top) {
                Image("register_bg")
                    .frame(
                        width: Dimen.registerBgWidth,
                        height: Dimen.registerBgHeight,
                        alignment: .topTrailing
                    )
                    .offset(CGSize(width: Dimen.registerBgOffsetWidth, height: Dimen.registerBgOffsetHeight))
                
                VStack(spacing: Padding.defaultPadding) {
                    Image("dolphin_image")
                        .padding(.top, Padding.extraLarge)
                    
                    HStack(spacing: Padding.defaultPadding) {
                        Text(
                            NSLocalizedString("Account", comment: "")
                        )
                        .titleLargeStyle()
                        .padding(
                            EdgeInsets(
                                top: Padding.defaultPadding,
                                leading: Padding.defaultPadding,
                                bottom: Padding.defaultPadding,
                                trailing: Padding.extraSmall
                            )
                        )
                        .foregroundColor(Color.onBackgroundColor)
                        
                        Text(
                            NSLocalizedString("Created", comment: "")
                        )
                        .titleLargeStyle()
                        .foregroundColor(Color.primaryColor)
                        
                        Text(
                            NSLocalizedString("!", comment: "")
                        )
                        .titleLargeStyle()
                        .foregroundColor(Color.secondaryColor)
                    }
                    .frame(alignment: .top)
                    .padding(
                        EdgeInsets(
                            top: Padding.extraLarge,
                            leading: Padding.defaultPadding,
                            bottom: Padding.defaultPadding,
                            trailing: Padding.defaultPadding
                        )
                    )
                    
                    Text(
                        NSLocalizedString("Dear user your account has been created successfully. Enjoy using app", comment: "")
                    )
                    .foregroundColor(Color.onBackgroundColor)
                    .bodyMediumStyle()
                    .multilineTextAlignment(.center
                    )
                    
                    .padding(
                        EdgeInsets(
                            top: Padding.large,
                            leading: Padding.smallLarge,
                            bottom: 0,
                            trailing: Padding.extraMedium
                        )
                    )
                    
                    Button(
                        action: {},
                        label: {
                            Text(
                                NSLocalizedString("Start Fun", comment: "")
                            )
                            .startButtonStyle()
                        }
                    )
                    .padding(.horizontal, Padding.smallLarge)
                    .padding(.top, Dimen.RegisterFinishedMiddleMargin)
                    
                    VStack(spacing: Padding.extraSmall) {
                        AuthBodyText(
                            text: NSLocalizedString("Do you want to improve account recovery?", comment: "")
                        )
                        .bodySmallStyle()
                        
                        Button(action: {}, label: {
                            UnderlineText(
                                str: NSLocalizedString("Link your profile to your socials", comment: ""),
                                textColor: Color.primaryColor
                            )
                            .bodySmallStyle()
                        })
                    }.padding(
                        EdgeInsets(
                            top: Padding.extraMedium,
                            leading: Padding.defaultPadding,
                            bottom: Padding.extraLarge,
                            trailing: Padding.defaultPadding
                        )
                    )
                }
            }
        }
        .background(Color.backgroundColor)
        .navigationBarBackButtonHidden(true)
    }
}

struct RegistrationFinished_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationFinishedScreen()
    }
}
        
