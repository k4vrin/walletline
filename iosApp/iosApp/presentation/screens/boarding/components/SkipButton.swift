//
//  SkipButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SkipButton: View {
    let onClick: () -> Void
    var body: some View {
        Button {
            onClick()
        } label: {
            Text(
                NSLocalizedString("Skip", comment: "")
            )
                .bodySmallStyle()
                .padding(.vertical, Padding.small)
                .padding(.horizontal, Padding.medium)
                .foregroundColor(.neutralColor)
                .background(
                    Capsule()
                        .fill(Color.neutralColor.opacity(0.15))
                )
        }
    }
}

struct SkipButton_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground {
            
            SkipButton {
                
            }
        }
    }
}
