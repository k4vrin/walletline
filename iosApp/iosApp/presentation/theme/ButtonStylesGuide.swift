//
//  ButtonStylesGuide.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/21/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ButtonStylesGuide: View {
    var body: some View {
        VStack(spacing: 30) {
            Text("Button Styles")
                .titleLargeStyle()

            Button {} label: {
                Text("primaryButtonStyle disable")
                    .primaryButtonStyle()
            }
            .disabled(true)
            
            Button {} label: {
                Text("primaryButtonStyle enable")
                    .primaryButtonStyle()
            }
            .disabled(false)

            Button {} label: {
                Text("startButtonStyle")
                    .startButtonStyle()
            }
            Button {} label: {
                ZStack {
                    HStack {
                        Image("apple_icon")
                        Spacer()
                    }
                    Text("socialMediaButtonStyle")
                }
                .socialMediaButtonStyle(bgColor: Color.backgroundColor, fgColor: Color.surfaceColor)
            }
        }
        .padding()
    }
}

struct PrimaryButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled
    var contentColor = Color.neutralColor
    var backgroundColor = Color.mainColorShade4
    var borderColor: Color? = nil

    var bColor: Color {
        isEnabled ? backgroundColor : backgroundColor.opacity(Dimen.DisabledAlpha)
    }

    var cColor: Color {
        isEnabled ? contentColor : contentColor.opacity(Dimen.DisabledAlpha)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .buttonStyle()
            .foregroundColor(cColor)
            .padding(Padding.medium)
            .background(
                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius, style: .continuous)
                    .fill(bColor)
            )
            .overlay(
                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius, style: .continuous)
                    .stroke(borderColor ?? bColor, lineWidth: 2)
            )
    }
}

struct StartButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled

    var backgroundColor: Color {
        isEnabled ? Color.tertiaryColor : Color.primaryColor.opacity(Dimen.DisabledAlpha)
    }

    var contentColor: Color {
        isEnabled ? Color.onPrimaryColor : Color.onPrimaryColor.opacity(Dimen.DisabledAlpha)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .frame(height: 56)
            .bodyLargeStyle()
            .foregroundColor(contentColor)
            .background(
                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius, style: .continuous)
                    .fill(backgroundColor)
            )
    }
}

struct SocialMediaButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled
    var bgColor: Color
    var fgColor: Color
    var radius: CGFloat = Dimen.DefaultButtonCornerRadius

    var backgroundColor: Color {
        isEnabled ? bgColor : bgColor.opacity(Dimen.DisabledAlpha)
    }

    var contentColor: Color {
        isEnabled ? fgColor : fgColor.opacity(Dimen.DisabledAlpha)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .frame(height: Dimen.SocialMediaButtonHeight)
            .buttonStyle()
            .foregroundColor(contentColor)
            .background(
                RoundedRectangle(cornerRadius: radius, style: .continuous)
                    .fill(bgColor)
            )
            .overlay(
                RoundedRectangle(cornerRadius: radius, style: .continuous)
                    .stroke(Color.neutralColorShade2)
            )
    }
}

public extension View {
    func primaryButtonStyle(
        backgroundColor: Color = Color.mainColorShade4,
        contentColor: Color = Color.neutralColor,
        borderColor: Color? = nil
    ) -> some View {
        modifier(
            PrimaryButtonStyle(
                contentColor: contentColor,
                backgroundColor: backgroundColor,
                borderColor: borderColor
            )
        )
    }

    func startButtonStyle() -> some View {
        modifier(StartButtonStyle())
    }

    func socialMediaButtonStyle(radius: CGFloat = 12, bgColor: Color, fgColor: Color) -> some View {
        modifier(SocialMediaButtonStyle(bgColor: bgColor, fgColor: fgColor, radius: radius))
    }
}

struct ButtonStylesGuide_Previews: PreviewProvider {
    static var previews: some View {
        ButtonStylesGuide()
    }
}
