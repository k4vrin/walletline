//
//  LineTransTabRow.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineTabRow: View {
    @Binding var isFirstTabSelected: Bool
    
    let firstTabName: String
    let secondTabName: String

    var body: some View {
        ZStack {
            Color.neutralColorShade6

            HStack(spacing: Padding.defaultPadding) {
                Button {
                    isFirstTabSelected = true
                } label: {
                    Text(
                        firstTabName
                    )
                    .tabRowButtonStyle(
                        selectedBgColor: .neutralColor,
                        unSelectedBgColor: .clear,
                        selectedFgColor: .neutralColorDark,
                        unSelectedFgColor: .neutralColorShade3,
                        isSelected: isFirstTabSelected
                    )
                }
                .padding(.horizontal, 6)

                Button {
                    isFirstTabSelected = false
                } label: {
                    Text(
                        secondTabName
                    )
                    .tabRowButtonStyle(
                        selectedBgColor: .neutralColor,
                        unSelectedBgColor: .clear,
                        selectedFgColor: .neutralColorDark,
                        unSelectedFgColor: .neutralColorShade3,
                        isSelected: !isFirstTabSelected
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
        WalletLineTabRow(isFirstTabSelected: .constant(true), firstTabName: "Lines", secondTabName: "Trans")
    }
}
