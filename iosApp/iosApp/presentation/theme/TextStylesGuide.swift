//
//  TextStylesGuide.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TextStylesGuide: View {
    var body: some View {
        VStack(spacing: 30) {
            Text("System")
            
            Text("Display italic")
                .displaySmallStyle()
            
            Text("Header Large")
                .headlineLargeStyle()
            
            Text("Header Medium")
                .headlineMediumStyle()
            
            Text("Header Small")
                .headlineSmallStyle()
            
            Text("Title Large")
                .titleLargeStyle()
            
            Text("Title Small")
                .titleSmallStyle()
            
            Text("Body Medium")
                .bodyMediumStyle()
            
            Text("Body Small")
                .bodySmallStyle()
            
            
            Text("Button")
                .buttonStyle()
        }
    }
}

struct DMSans {
    static let medium = "DMSans-Regular"
    static let bold = "DMSans-Bold"
}
struct PlusJakartaSans {
    static let regular = "PlusJakartaSans-Regular"
    static let medium = "PlusJakartaSans-Medium"
    static let mediumItalic = "PlusJakartaSans-MediumItalic"
    static let semiBold = "PlusJakartaSans-SemiBold"
    static let bold = "PlusJakartaSans-Bold"
}

struct DisplaySmallStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.mediumItalic,
                    size: 24,
                    relativeTo: .largeTitle
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct HeadlineLargeStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
                    size: 20,
                    relativeTo: .headline
                )
            )
            .lineSpacing(Padding.extraSmall)
    }
}

struct HeadlineMediumStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.medium,
                    size: 18,
                    relativeTo: .headline
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct HeadlineSmallStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.medium,
                    size: 16,
                    relativeTo: .headline
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct TitleLargeStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.medium,
                    size: 16,
                    relativeTo: .title
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct TitleMediumStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.regular,
                    size: 16,
                    relativeTo: .title2
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct TitleSmallStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.mediumItalic,
                    size: 12,
                    relativeTo: .title3
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct BodyLargeStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.medium,
                    size: 14,
                    relativeTo: .subheadline
                )
            )
            .lineSpacing(2)
    }
}

struct BodyMediumStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.regular,
                    size: 14,
                    relativeTo: .body
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct BodySmallStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.medium,
                    size: 12,
                    relativeTo: .body
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct ButtonStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
                    size: 14,
                    relativeTo: .footnote
                )
            )
            .lineSpacing(Padding.small)
    }
}

extension View {
    public func displaySmallStyle() -> some View {
        modifier(DisplaySmallStyle())
    }
    public func headlineLargeStyle() -> some View {
        modifier(HeadlineLargeStyle())
    }
    public func headlineMediumStyle() -> some View {
        modifier(HeadlineMediumStyle())
    }
    public func headlineSmallStyle() -> some View {
        modifier(HeadlineSmallStyle())
    }
    public func titleLargeStyle() -> some View {
        modifier(TitleLargeStyle())
    }
    public func titleSmallStyle() -> some View {
        modifier(TitleSmallStyle())
    }
    public func bodyLargeStyle() -> some View {
        modifier(BodyLargeStyle())
    }
    public func bodyMediumStyle() -> some View {
        modifier(BodyMediumStyle())
    }
    public func bodySmallStyle() -> some View {
        modifier(BodySmallStyle())
    }
    public func buttonStyle() -> some View {
        modifier(ButtonStyle())
    }
}

struct TextStylesGuide_Previews: PreviewProvider {
    static var previews: some View {
        TextStylesGuide()
    }
}
