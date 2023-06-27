//
//  WalletLineMainBackground.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineScaffold<Content: View, TopBar: View>: View {
    @State var scrollOffset = CGPoint.zero
    @Binding var isScrolling: Bool
    var backgroundColor: Color = .neutralColorShade1
    @ViewBuilder let content: (_ geo: GeometryProxy) -> Content
    @ViewBuilder let topBar: () -> TopBar

    var body: some View {
        GeometryReader { geo in
            OffsetObservingScrollView(offset: $scrollOffset) {
                Spacer(minLength: 75)
                content(geo)
                Spacer(minLength: 86)
            }
            .background(backgroundColor)
            .frame(
                width: geo.size.width
            )
        }
        .overlay {
            topBar()
                .frame(maxHeight: .infinity, alignment: .top)
        }
        .onChange(of: scrollOffset) { offset in

            if offset.y > 1 {
                isScrolling = true
            } else {
                isScrolling = false
            }
        }
        .animation(.default, value: isScrolling)
        
    }
}

struct WalletLineMainBackground_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
