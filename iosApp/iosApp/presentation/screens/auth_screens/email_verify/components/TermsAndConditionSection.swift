//
//  TermsAndConditionSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TermsAndConditionSection: View {
    var attText: AttributedString {
        var normalStr = AttributedString(
            NSLocalizedString("By clicking continue, you agree with our", comment: "")
        )
        normalStr.foregroundColor = .neutralColorShade2
        normalStr.font = .custom(
            PlusJakartaSans.regular,
            size: 14
        )
        
        
        var underlineStr = AttributedString(
            NSLocalizedString("Terms & Conditions", comment: "")
        )
        underlineStr.underlineStyle = Text.LineStyle(
            pattern: .solid,
            color: .neutralColorShade2
        )
        underlineStr.font = .custom(
            PlusJakartaSans.regular,
            size: 14
        )
        underlineStr.foregroundColor = .neutralColorShade2
        underlineStr.link = URL(string: "https://datarivers.org")
        
        var normalStr2 = AttributedString(
            NSLocalizedString("and", comment: "")
        )
        normalStr2.foregroundColor = .neutralColorShade2
        normalStr2.font = .custom(
            PlusJakartaSans.regular,
            size: 14
        )
        
        var underlineStr2 = AttributedString(
            NSLocalizedString("Privacy Policy", comment: "")
        )
        underlineStr2.underlineStyle = Text.LineStyle(
            pattern: .solid,
            color: .neutralColorShade2
        )
        underlineStr2.font = .custom(
            PlusJakartaSans.regular,
            size: 14
        )
        underlineStr2.foregroundColor = .neutralColorShade2
        underlineStr2.link = URL(string: "https://datarivers.org")
        
        return normalStr + " " + underlineStr + " " + normalStr2 + " " + underlineStr2
    }
    var body: some View {
        Text(attText)
            .multilineTextAlignment(.center)
    }
}

struct TermsAndConditionSection_Previews: PreviewProvider {
    static var previews: some View {
        TermsAndConditionSection()
    }
}
