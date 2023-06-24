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
        .foregroundColor(.neutralColorShade2)
        .bodySmallStyle()
    }
}

struct DatariversTeamText_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineAuthBackground { geo in
            DatariversTeamText()
        }
    }
}
