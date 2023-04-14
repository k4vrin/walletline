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
                .font(.largeTitle)
            
            Text("Title large")
                .titleLargeStyle()
            
            Text("Title Medium")
                .titleMediumStyle()
            
            Text("Title Small")
                .titleSmallStyle()
            
            Text("Body Medium")
                .bodyMediumStyle()
            
            Text("Body Small")
                .bodySmallStyle()
            
            
            Text("Label")
                .labelSmallStyle()
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
    static let mediumItalic = "MediumItalic"
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
                    PlusJakartaSans.bold,
                    size: 20,
                    relativeTo: .headline
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct HeadlineMediumStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
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
                    PlusJakartaSans.semiBold,
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
                    size: 20,
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
                    PlusJakartaSans.medium,
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
                    PlusJakartaSans.semiBold,
                    size: 14,
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
                    PlusJakartaSans.semiBold,
                    size: 16,
                    relativeTo: .subheadline
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct BodyMediumStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
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
                    PlusJakartaSans.regular,
                    size: 14,
                    relativeTo: .body
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct LabelLargeStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
                    size: 20,
                    relativeTo: .footnote
                )
            )
            .lineSpacing(Padding.small)
    }
}

struct LabelMediumStyle: ViewModifier {
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

struct LabelSmallStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(
                .custom(
                    PlusJakartaSans.semiBold,
                    size: 12,
                    relativeTo: .footnote
                )
            )
            .lineSpacing(Padding.small)
    }
}

extension View {
    public func titleLargeStyle() -> some View {
        modifier(TitleLargeStyle())
    }
    public func titleMediumStyle() -> some View {
        modifier(TitleMediumStyle())
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
    public func labelSmallStyle() -> some View {
        modifier(LabelSmallStyle())
    }
}

struct TextStylesGuide_Previews: PreviewProvider {
    static var previews: some View {
        TextStylesGuide()
    }
}
