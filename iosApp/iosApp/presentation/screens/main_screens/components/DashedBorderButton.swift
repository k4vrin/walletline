//
//  DashedBorderButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DashedBorderButton: View {
    var title: String
    var onClick: () -> Void
    var body: some View {
        Button {
            onClick()
        } label: {
            ZStack {
                RoundedRectangle(cornerRadius: 12)
                    .stroke(style: StrokeStyle(dash: [5]))
                    .foregroundColor(.neutralColorShade3)
                
                Text(title)
                    .buttonStyle()
                    .foregroundColor(.neutralColorShade3)
            }
        }
        .frame(height: 48)
    }
}

struct DashedBorderButton_Previews: PreviewProvider {
    static var previews: some View {
        DashedBorderButton(title: "Create line") {
            
        }
    }
}
