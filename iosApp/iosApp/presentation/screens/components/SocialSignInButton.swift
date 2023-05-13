//
//  SocialMediaButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SocialSignInButton<Content: View>: View {
    var radius: CGFloat
    @ViewBuilder let icon: () -> Content
    let text: String
    let bgColor: Color
    let fgColor: Color
    let action: () -> Void
    
    init(radius: CGFloat = 12, icon: @escaping () -> Content, text: String, bgColor: Color = Color.clear , fgColor: Color = Color.neutralColorShade6, action: @escaping () -> Void) {
        self.radius = radius
        self.icon = icon
        self.text = text
        self.bgColor = bgColor
        self.fgColor = fgColor
        self.action = action
    }
    
    var body: some View {
        Button(
            action: action,
            label: {
                HStack(spacing: Padding.smallMedium) {
                    icon()
                        
                    Text(text)
                        .foregroundColor(fgColor)
                }
                .socialMediaButtonStyle(
                    radius: radius,
                    bgColor: bgColor,
                    fgColor: fgColor
                )
            }
        )
    }
}

struct SocialMediaButton_Previews: PreviewProvider {
    static var previews: some View {
        SocialSignInButton(
            icon: {
                Image("facebook_icon")
            },
            text: "Sign in with Facebook"
        ) {}
    }
}
