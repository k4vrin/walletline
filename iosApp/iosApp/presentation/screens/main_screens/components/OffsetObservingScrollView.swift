//
//  OffsetObservingScrollView.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct OffsetObservingScrollView<Content: View>: View {
    var axes: Axis.Set = [.vertical]
    var showIndicators = false
    @Binding var offset: CGPoint
    @ViewBuilder var content: () -> Content
    private let coordinateSpaceName = UUID()
    
    var body: some View {
        ScrollView(axes, showsIndicators: showIndicators) {
            PositionObservingView(
                coordinateSpace: .named(coordinateSpaceName),
                position: Binding(
                    get: { offset },
                    set: { newOffset in
                        offset = CGPoint(x: -newOffset.x, y: -newOffset.y)
                    }
                ),
                content: content
            )
        }
        .coordinateSpace(name: coordinateSpaceName)
    }
}

struct OffsetObservingScrollView_Previews: PreviewProvider {
    static var previews: some View {
        OffsetObservingScrollView(offset: .constant(CGPoint.zero)) {
            Text("ssdd")
        }
    }
}
