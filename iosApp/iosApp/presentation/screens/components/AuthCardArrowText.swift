//
//  AuthCardArrowText.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthCardArrowText: View {
    let text: String
    let action: () -> Void
    
    init(_ text: String, action: @escaping () -> Void) {
        self.text = text
        self.action = action
    }
    var body: some View {
        Button {
            
        } label: {
            HStack(spacing: 0) {
                Text(text)
                    .bodySmallStyle()
                    .foregroundColor(.onSurfaceColor.opacity(0.4))
                    .padding(.trailing, Padding.small)
                
                Image("arrow-right")
                    .renderingMode(.template)
                    .foregroundColor(.onSurfaceColor.opacity(0.4))
            }
        }
    }
}

struct AuthCardArrowText_Previews: PreviewProvider {
    static var previews: some View {
        AuthCardArrowText("Enter by email address") {
            
        }
    }
}
