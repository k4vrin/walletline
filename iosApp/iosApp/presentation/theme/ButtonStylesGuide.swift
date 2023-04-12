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
                Text("small primary")
                    .primaryButtonStyle()
            }
            .disabled(true)

            Button {} label: {
                Text("small primary")
                    .startButtonStyle()
            }
            Button {} label: {
                ZStack {
                    HStack {
                        Image("apple_icon")
                        Spacer()
                    }
                    Text("small primary")
                }
                .socialMediaButtonStyle(bgColor: Color.backgroundColor, fgColor: Color.surfaceColor)
            }
        }
    }
}

struct PrimaryButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled

    var backgroundColor: Color {
        isEnabled ? Color.primaryColor : Color.primaryColor.opacity(0.6)
    }

    var contentColor: Color {
        isEnabled ? Color.onPrimaryColor : Color.onPrimaryColor.opacity(0.6)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .bodyLargeStyle()
            .foregroundColor(contentColor)
            .padding(16)
            .background(
                RoundedRectangle(cornerRadius: 10, style: .continuous)
                    .fill(backgroundColor)
            )
    }
}

struct StartButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled

    var backgroundColor: Color {
        isEnabled ? Color.tertiaryColor : Color.primaryColor.opacity(0.6)
    }

    var contentColor: Color {
        isEnabled ? Color.onPrimaryColor : Color.onPrimaryColor.opacity(0.6)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .frame(height: 56)
            .bodyLargeStyle()
            .foregroundColor(contentColor)
            .background(
                RoundedRectangle(cornerRadius: 10, style: .continuous)
                    .fill(backgroundColor)
            )
    }
}

struct SocialMediaButtonStyle: ViewModifier {
    @Environment(\.isEnabled) var isEnabled
    var bgColor: Color
    var fgColor: Color
    var radius: CGFloat = 12

    var backgroundColor: Color {
        isEnabled ? bgColor : bgColor.opacity(0.6)
    }

    var contentColor: Color {
        isEnabled ? fgColor : fgColor.opacity(0.6)
    }

    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .frame(height: 48)
            .labelLargeStyle()
            .foregroundColor(contentColor)
            .background(
                RoundedRectangle(cornerRadius: radius, style: .continuous)
                    .fill(bgColor)
            )
            .overlay(
                RoundedRectangle(cornerRadius: radius, style: .continuous)
                    .stroke(Color.outlineColor.opacity(0.1))
            )
    }
}

public extension View {
    func primaryButtonStyle() -> some View {
        modifier(PrimaryButtonStyle())
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
