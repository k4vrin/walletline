//
//  AuthCardTitle.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthCardTitle: View {
    let text: String
    init(_ text: String) {
        self.text = text
    }

    var body: some View {
        Text(text)
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
    }
}

struct AuthCardTitle_Previews: PreviewProvider {
    static var previews: some View {
        AuthCardTitle(
            "Enter by socials")
    }
}
