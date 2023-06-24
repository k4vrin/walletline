//
//  PositionObservingView.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PositionObservingView<Content: View>: View {
    var coordinateSpace: CoordinateSpace
    @Binding var position: CGPoint
    @ViewBuilder var content: () -> Content
    
    var body: some View {
        content()
            .background(
                GeometryReader { geo in
                    Color.clear.preference(
                        key: PreferenceKey.self,
                        value: geo.frame(in: coordinateSpace).origin
                    )
                }
            )
            .onPreferenceChange(PreferenceKey.self) { pos in
                self.position = pos
            }
    }
    
    struct PreferenceKey: SwiftUI.PreferenceKey {
            static var defaultValue: CGPoint { .zero }

            static func reduce(value: inout CGPoint, nextValue: () -> CGPoint) {
                // No-op
            }
        }
}

struct PositionObservingView_Previews: PreviewProvider {
    static var previews: some View {
        PositionObservingView(
            coordinateSpace: .named(UUID()),
            position: .constant(CGPoint.zero)) {
            EmptyView()
        }
    }
}
