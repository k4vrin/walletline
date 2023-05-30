//
//  FingerPrintButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FingerPrintButton: View {
    
    let action: () -> Void
    
    var body: some View {
        Button {
            action()
        } label: {
            HStack {
                Spacer()
                Image("ic_touch")
                Spacer()
                Text(
                    NSLocalizedString("Use my face/finger recognition", comment: "")
                )
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .frame(height: Padding.large)
            .buttonStyle()
            .foregroundColor(Color.neutralColorShade2)
            .padding(.horizontal, Padding.smallMedium)
            .background(
                RoundedCorner(radius: Padding.smallMedium)
                    .stroke(Color.neutralColorShade2)
            )
        }
    }
}

struct FingerPrintButton_Previews: PreviewProvider {
    static var previews: some View {
        FingerPrintButton {
            
        }
    }
}
