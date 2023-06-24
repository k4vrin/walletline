//
//  PatternButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PatternButton: View {
    let title: String
    var isLoading: Bool = false
    var bgColor: Color = .neutralColor
    var contentColor: Color = .neutralColorDark
    var borderColor: Color? = nil
    let action: () -> Void
    var body: some View {
        Button {
            action()
        } label: {
            if !isLoading {
                Text(title)
                    .primaryButtonStyle(
                        backgroundColor: bgColor,
                        contentColor: contentColor,
                        borderColor: borderColor
                    )
            } else {
                // FIXME: Change animation to fading dots
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
                    .primaryButtonStyle(
                        backgroundColor: bgColor,
                        contentColor: contentColor,
                        borderColor: borderColor
                    )
            }
        }
        .disabled(isLoading)
        .animation(.default, value: isLoading)
    }
}

struct PatternButton_Previews: PreviewProvider {
    static var previews: some View {
        PatternButton(
            title: NSLocalizedString("Continue", comment: "")
        ) {}
    }
}
