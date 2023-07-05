//
//  CategoryChip.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CategoryChip: View {
    
    var name: String
    var image: Image = Image(systemName: "dumbbell")
    
    
    var body: some View {
        ZStack {
            Capsule()
                .stroke(Color.neutralColorShade6)
            
            HStack(alignment: .center, spacing: Padding.extraSmall) {
                
                ZStack {
                    image
                        .resizable()
                        .scaledToFit()
                }
                .frame(width: 24, height: 24)
                
                
                Text(name)
                    .bodySmallStyle()
                
                Button {
                    
                } label: {
                    Image(systemName: "xmark")
                        .renderingMode(.template)
                        .resizable()
                        .foregroundColor(.neutralColorShade3)
                        .frame(width: 8, height: 8)
                }
            }
            .padding(.all, 6)
        }
        .frame(height: 26)
        .fixedSize(horizontal: true, vertical: false)
    }
}

struct CategoryChip_Previews: PreviewProvider {
    static var previews: some View {
        CategoryChip(
            name: "Gym"
        )
    }
}
