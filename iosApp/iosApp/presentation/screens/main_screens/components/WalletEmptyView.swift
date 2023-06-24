//
//  WalletEmptyView.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletEmptyView: View {
    let title: String
    let desc: String
    var body: some View {
        ZStack {
            VStack {
                Image("ic_info_circle")
                    .renderingMode(.template)
                    .foregroundColor(.infoColor)
                
                Text(title)
                    .bodyLargeStyle()
                    .foregroundColor(.neutralColorShade4)
                    .padding(.vertical, Padding.smallMedium)
                
                Text(desc)
                    .bodySmallStyle()
                    .foregroundColor(.neutralColorShade3)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, Padding.extraLarge)
                    
            }
        }
    }
}

struct WalletEmptyView_Previews: PreviewProvider {
    static var previews: some View {
        WalletEmptyView(
            title: "There is no wallet yet!",
            desc: "Plan ahead and manage your money better. A description about wallet and Necessity of its creation."
        )
    }
}
