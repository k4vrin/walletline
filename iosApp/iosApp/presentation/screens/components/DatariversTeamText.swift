//
//  DatariversTeamText.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DatariversTeamText: View {
    var body: some View {
        HStack {
            Text(
                NSLocalizedString("Designed by", comment: "")
            )
                .fontWeight(.regular) +
            Text(" ") +
            Text(
                NSLocalizedString("datarivers.org", comment: "")
            )
                .fontWeight(.bold) +
            Text(" ") +
            Text(
                NSLocalizedString("developers team", comment: "")
            )
                .fontWeight(.regular)
        }
        .foregroundColor(.surfaceColor.opacity(0.4))
        .bodySmallStyle()
    }
}

struct DatariversTeamText_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineBackground {
            DatariversTeamText()
        }
    }
}
