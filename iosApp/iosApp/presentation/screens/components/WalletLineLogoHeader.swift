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
        VStack(spacing: Padding.defaultPadding) {
            Image("wallet_icon")
                .frame(
                    minWidth: Dimen.WalletlineLogoSize,
                    minHeight: Dimen.WalletlineLogoSize
                )
            Text(
                NSLocalizedString(
                    "Walletline+",
                    comment: "Walletline+"
                )
            )
            .displaySmallStyle()
            .foregroundColor(.neutralColor)
        }
    }
}

struct WalletLineLogoHeader_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground { geo in
            WalletLineLogoHeader()
                .padding(.vertical, 100)
        }
    }
}
