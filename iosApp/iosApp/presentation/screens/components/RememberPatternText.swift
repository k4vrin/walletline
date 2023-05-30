//
//  RememberPatternText.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RememberPatternText: View {
    var body: some View {
        Text(
            NSLocalizedString(
                "Remember this pattern",
                comment: ""
            )
        )
        .multilineTextAlignment(.center)
        .bodyMediumStyle()
        .foregroundColor(.neutralColorShade2)
    }
}

struct RememberPatternText_Previews: PreviewProvider {
    static var previews: some View {
        RememberPatternText()
    }
}
