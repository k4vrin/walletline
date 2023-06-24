//
//  PatternTitle.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PatternTitle: View {
    let text: String
    init(_ text: String) {
        self.text = text
    }
    var body: some View {
        Text(text)
            .foregroundColor(.neutralColorShade1)
            .headlineSmallStyle()
    }
}

struct PatternTitle_Previews: PreviewProvider {
    static var previews: some View {
        PatternTitle("Create your pattern")
    }
}
