//
//  AuthButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineButton: View {
    let title: String
    var isLoading: Bool = false
    let action: () -> Void
    var body: some View {
        Button {
            action()
        } label: {
            if !isLoading {
                Text(title)
                    .primaryButtonStyle()
            } else {
                // FIXME: Change animation to fading dots
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
                    .primaryButtonStyle()
            }
        }
        .disabled(isLoading)
        .animation(.default, value: isLoading)
    }
}

struct AuthButton_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineButton(
            title: NSLocalizedString("Continue", comment: "")
        ) {
            
        }
    }
}
