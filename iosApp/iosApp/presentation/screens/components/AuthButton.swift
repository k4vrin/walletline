//
//  AuthButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthButton: View {
    let title: String
    var isLoading: Bool = true
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
        AuthButton(
            title: NSLocalizedString("Continue", comment: "")
        ) {
            
        }
    }
}
