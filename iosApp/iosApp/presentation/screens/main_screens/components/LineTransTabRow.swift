//
//  LineTransTabRow.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LineTransTabRow: View {
    @Binding var isLineSelected: Bool

    var body: some View {
        ZStack {
            Color.neutralColorShade6

            HStack(spacing: Padding.defaultPadding) {
                Button {
                    isLineSelected = true
                } label: {
                    Text(
                        NSLocalizedString("Lines", comment: "")
                    )
                    .tabRowButtonStyle(
                        selectedBgColor: .neutralColor,
                        unSelectedBgColor: .clear,
                        selectedFgColor: .neutralColorDark,
                        unSelectedFgColor: .neutralColorShade3,
                        isSelected: isLineSelected
                    )
                }
                .padding(.horizontal, 6)

                Button {
                    isLineSelected = false
                } label: {
                    Text(
                        NSLocalizedString("Transactions", comment: "")
                    )
                    .tabRowButtonStyle(
                        selectedBgColor: .neutralColor,
                        unSelectedBgColor: .clear,
                        selectedFgColor: .neutralColorDark,
                        unSelectedFgColor: .neutralColorShade3,
                        isSelected: !isLineSelected
                    )
                }
                .padding(.horizontal, 6)
            }
        }
        .clipShape(RoundedCorner(radius: Dimen.DefaultButtonCornerRadius))
        .frame(height: 53)
    }
}

struct LineTransTabRow_Previews: PreviewProvider {
    static var previews: some View {
        LineTransTabRow(isLineSelected: .constant(true))
    }
}
