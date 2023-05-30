//
//  WalletLineBackground.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineBackground<Content: View>: View {
    @ViewBuilder let content: (_ geo: GeometryProxy) -> Content
    let width = UIScreen.main.bounds.width
    let height = UIScreen.main.bounds.height
    var bigCircleDiam: CGFloat {
        return max(width, height) / 1.1
    }

    var smallCircleDiam: CGFloat {
        return max(width, height) / 1.5
    }

    var body: some View {
        GeometryReader { geo in
            Color.mainColor
                .ignoresSafeArea()

            Circle()
                .frame(
                    width: bigCircleDiam,
                    height: bigCircleDiam
                )
                .offset(
                    x: -bigCircleDiam / 2.09,
                    y: bigCircleDiam / 4.6
                )
                .foregroundColor(.neutralColor)
                .opacity(0.04)

            Circle()
                .frame(
                    width: smallCircleDiam,
                    height: smallCircleDiam
                )
                .offset(
                    x: -smallCircleDiam / 2,
                    y: -smallCircleDiam / 2.9
                )
                .foregroundColor(.neutralColor)
                .opacity(0.04)
            ScrollView(.vertical) {
                content(geo)
            }
            .frame(
                width: geo.size.width
            )
        }
    }
}

struct WalletLineBackground_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground { geo in
            Text("Hello World")
        }
    }
}
