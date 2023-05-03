//
//  DotIndicator.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DotIndicator: View {
    
    let totalDots: Int
    
    
    @Binding var index: Int
    
    var body: some View {
        HStack(alignment: .center, spacing: Padding.extraSmall) {
            ForEach(0..<totalDots, id: \.self) { dot in
                let height: CGFloat = index == dot ? 12 : 8
                RoundedRectangle(cornerRadius: 12)
                    .fill(index == dot ? Color.mainColor : Color.mainColorShade2)
                    .frame(width: 8, height: height)
            }
        }
    }
}

struct DotIndicator_Previews: PreviewProvider {
    static var previews: some View {
        DotIndicator(totalDots: 3, index: .constant(1))
    }
}
