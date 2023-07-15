//
//  BottomNavBar.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BottomNavBar: View {
    @ObservedObject var navController: NavigationController


    @State private var isNavActive = false

    var body: some View {
        ZStack {
            NavigationLink(isActive: $isNavActive) {
                AddEditTransactionScreen(walletId: navController.currentWalletId ?? "")
            } label: {
                EmptyView()
            }.hidden()
            BottomBarShape(clipHeight: 15)
                .fill(
                    LinearGradient(
                        gradient: Gradient(colors: [.black.opacity(0.5), .black.opacity(0.8)]),
                        startPoint: .top,
                        endPoint: .bottom
                    )
                )
                .background(Material.ultraThinMaterial)
                .clipShape(BottomBarShape(clipHeight: 15))

            Button {
                if let id = navController.currentWalletId, !id.isEmpty {
                    isNavActive = true
                }
            } label: {
                ZStack {
                    Circle()
                        .fill(Color.mainColor)
                        .frame(width: 56, height: 56)
                        .shadow(radius: 10, y: 10)

                    Image(systemName: "plus")
                        .resizable()
                        .foregroundColor(.white)
                        .frame(width: 16, height: 16)
                }
            }

            HStack(spacing: Padding.defaultPadding) {
                Button {
                    navController.currentGraph = .home
                } label: {
                    VStack(spacing: Padding.defaultPadding) {
                        Image("ic_home")
                            .renderingMode(.template)
                            .foregroundColor(
                                navController.currentGraph == .home ? Color.neutralColor : Color.neutralColorShade3
                            )
                        Text("Home")
                            .foregroundColor(
                                navController.currentGraph == .home ? Color.neutralColor : Color.neutralColorShade3
                            )
                            .bodyMediumStyle()
                    }
                }
                .offset(CGSize(width: 0, height: 10))

                Spacer()

                Button {
                    navController.currentGraph = .wallet
                } label: {
                    VStack(spacing: Padding.defaultPadding) {
                        Image("ic_wallet")
                            .renderingMode(.template)
                            .foregroundColor(
                                navController.currentGraph == .wallet ? Color.neutralColor : Color.neutralColorShade3
                            )
                        Text("Wallet")
                            .foregroundColor(
                                navController.currentGraph == .wallet ? Color.neutralColor : Color.neutralColorShade3
                            )
                            .bodyMediumStyle()
                    }
                }
                .offset(CGSize(width: 0, height: 10))
            }
            .padding(.horizontal, Padding.extraLarge)
            .frame(maxWidth: .infinity)
        }
        .frame(height: 86)
    }
}

struct BottomNavBar_Previews: PreviewProvider {
    static var previews: some View {
        BottomNavBar(navController: NavigationController())
    }
}

struct BottomBarShape: Shape {
    var cornerRadius: CGFloat = 25
    var middleCornerRadius: CGFloat = 30
    var clipHeight: CGFloat
    func path(in rect: CGRect) -> Path {
        var path = Path()

        path.addArc(
            center: CGPoint(x: cornerRadius, y: cornerRadius + clipHeight),
            radius: cornerRadius,
            startAngle: .degrees(-180),
            endAngle: .degrees(-90),
            clockwise: false
        )
        path.addLine(to: CGPoint(x: rect.width/2 - (middleCornerRadius/3) - middleCornerRadius, y: clipHeight))

        path.addCurve(
            to: CGPoint(x: rect.width/2, y: 1),
            control1: CGPoint(x: rect.width/2 - middleCornerRadius, y: clipHeight),
            control2: CGPoint(x: rect.width/2 - (middleCornerRadius/2), y: 0)
        )

        path.addCurve(
            to: CGPoint(x: rect.width/2 + middleCornerRadius + (middleCornerRadius/3), y: clipHeight),
            control1: CGPoint(x: rect.width/2 + (middleCornerRadius/2), y: 0),
            control2: CGPoint(x: rect.width/2 + middleCornerRadius, y: clipHeight)
        )

        path.addLine(to: CGPoint(x: rect.width - cornerRadius, y: clipHeight))

        path.addArc(
            center: CGPoint(x: rect.width - cornerRadius, y: cornerRadius + clipHeight),
            radius: cornerRadius,
            startAngle: .degrees(-90),
            endAngle: .degrees(0),
            clockwise: false
        )
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - cornerRadius))
        path.addArc(
            center: CGPoint(x: rect.width - cornerRadius, y: rect.height - cornerRadius),
            radius: cornerRadius,
            startAngle: .degrees(0),
            endAngle: .degrees(90),
            clockwise: false
        )
        path.addLine(to: CGPoint(x: cornerRadius, y: rect.height))
        path.addArc(
            center: CGPoint(x: cornerRadius, y: rect.height - cornerRadius),
            radius: cornerRadius,
            startAngle: .degrees(90),
            endAngle: .degrees(180),
            clockwise: false
        )

        return path
    }
}
