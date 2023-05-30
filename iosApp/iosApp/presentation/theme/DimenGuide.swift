//
//  DimenGuide.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 3/13/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DimenGuide: View {
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct Dimen {
    public static let SplashTopMargin: CGFloat = 217
    public static let DefaultButtonCornerRadius: CGFloat = 12
    public static let DefaultCardCornerRadius: CGFloat = 16
    public static let HeartIconSize: CGFloat = 24
    public static let WalletlineLogoSize: CGFloat = 90
    public static let WalletlineLogoTopMargin: CGFloat = 100
    public static let WalletlineLogoBottomMargin: CGFloat = 70
    public static let phoneIconSize: CGFloat = 130
    public static let mobileVerifyTopMargin: CGFloat = 109
    public static let socialMediaMiddleMargin: CGFloat = 120
    public static let orDividerHeight: CGFloat = 1
    public static let orDividerRadius: CGFloat = 50
    public static let orDividerOffset: CGFloat = 2
    public static let registerBgWidth: CGFloat = 408
    public static let registerBgHeight: CGFloat = 280
    public static let registerBgOffsetWidth: CGFloat = 60
    public static let registerBgOffsetHeight: CGFloat = -210
    public static let lockPatternHeight: CGFloat = 370
    public static let lockPatternWidth: CGFloat = 370
    public static let RegisterFinishedMiddleMargin: CGFloat = 170
    public static let DisabledAlpha: CGFloat = 0.38
    public static let SocialMediaButtonHeight: CGFloat = 48
    public static let OnBoardingImageHeight: CGFloat = 320
    public static let OnBoardingCardTextHeight: CGFloat = 200
    public static let TextFieldHeight: CGFloat = 56
    public static let TextFieldLeadingIconWidth: CGFloat = 51
}

struct DimenGuide_Previews: PreviewProvider {
    static var previews: some View {
        DimenGuide()
    }
}
