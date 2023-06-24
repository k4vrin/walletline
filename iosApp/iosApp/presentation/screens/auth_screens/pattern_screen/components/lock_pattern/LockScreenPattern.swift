//
//  LockScreenAdapter.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/26/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LockScreenPattern: UIViewRepresentable {
    typealias UIViewType = LockScreen
    var frame: CGRect = .init(x: -100, y: -100, width: 300, height: 300)
    var resetView = false
    let action: (Double, [Int]) -> Void
    
    func makeUIView(context: Context) -> LockScreen {
        // Return LockScreen instance.
        var config = LockScreen.Config()
        config.lineColor = UIColor(Color.neutralColor)
        config.circleHighlightColor = UIColor(Color.red)
        config.circleOuterRingColor = UIColor(Color.neutralColor)
        config.circleInnerRingColor = UIColor(Color.neutralColor)
        config.lineEdgeColor = UIColor(Color.neutralColor.opacity(0.2))
        config.lineWidth = 1.5
        
        return LockScreen(
            frame: frame,
            size: 3,
            allowClosedPattern: false,
            config: config,
            handler: action
        )
    }
    
    func updateUIView(_ uiView: LockScreen, context: Context) {
        // Updates the state of the specified view with new information from SwiftUI.
            uiView.resetScreen(resetView)
    }
}

struct LockScreenAdapter_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            LockScreenPattern(action: { _, _ in })
                .background(content: {
                    Rectangle()
                        .fill(.red)
                })
                .border(.blue)
            Text("Hello")
            Spacer()
        }
    }
}
