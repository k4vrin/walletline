//
//  WalletLineLogoHeader.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineLogoHeader: View {
    var body: some View {
        VStack(spacing: 0) {
            Image("wallet_icon")
                .frame(
                    minWidth: Dimen.walletlineLogoSize,
                    minHeight: Dimen.walletlineLogoSize
                )
            Text(
                NSLocalizedString(
                    "Walletline+",
                    comment: "Walletline+"
                )
            )
            .displaySmallStyle()
            .foregroundColor(.surfaceColor)
        }
    }
}

struct WalletLineLogoHeader_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground {
            WalletLineLogoHeader()
                .padding(.vertical, 100)
        }
    }
}
