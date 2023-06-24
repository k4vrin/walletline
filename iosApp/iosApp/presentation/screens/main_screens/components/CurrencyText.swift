//
//  AccountCardCurrencyText.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CurrencyText: View {
    let amount: Decimal
    var currencyCode: String = "EUR"
    
    var symbolFont: Font = .custom(DMSans.medium, size: 24)
    var symbolColor: Color = .neutralColor
    
    var wholePartFont: Font = .custom(DMSans.medium, size: 24)
    var wholePartColor: Color = .neutralColor
    
    var fracPartFont: Font = .custom(DMSans.medium, size: 18)
    var fracPartColor: Color = .neutralColorShade2
    
    let locale = Locale.current as NSLocale
    
    var attText: AttributedString {
        let parts = modf(amount.doubleValue)
        let wholePart = parts.0
        let fractionPart = parts.1
        

        let symbol = locale.displayName(forKey: .currencySymbol, value: currencyCode)
        var attSymbol = AttributedString(symbol ?? "$")
        attSymbol.font = symbolFont
        attSymbol.foregroundColor = symbolColor
        
        let formattedWholePartWithoutFraction = wholePart.formatted(
            .number.rounded(rule: .towardZero).grouping(.automatic)
        )
        var firstAtt = AttributedString(formattedWholePartWithoutFraction)
        
        firstAtt.font = wholePartFont
        firstAtt.foregroundColor = wholePartColor
        
        var secondAtt = AttributedString(fractionPart.formatted(.number.precision(.integerAndFractionLength(integer: 0, fraction: 2))))
        
        secondAtt.font = fracPartFont
        secondAtt.foregroundColor = fracPartColor
        
        return attSymbol + " " + firstAtt + secondAtt
    }
    
    var body: some View {
        Text(attText)
    }
    
    
}

struct AccountCardCurrencyText_Previews: PreviewProvider {
    static var previews: some View {
        CurrencyText(amount: 2312345.4523)
            .background(.red)
    }
}
