//
//  AuthCard.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthCard<Content: View>: View {
    @ViewBuilder let content: () -> Content
    var body: some View {
        ZStack {
            
            RoundedRectangle(
                cornerRadius: 16,
                style: .continuous
            )
            .fill(Color.surfaceColor)
            .shadow(
                radius: 40,
                x: 0,
                y: 20
            )
            
            VStack(spacing: 0) {
                content()
            }
        }
        
        
    }
}

struct AuthCard_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground {
            VStack(spacing: 0) {
                AuthCard {
                    Text("Hello")
                        .padding()
                }
                .padding(.horizontal, 16)
                
            }
            
        }
    }
}
