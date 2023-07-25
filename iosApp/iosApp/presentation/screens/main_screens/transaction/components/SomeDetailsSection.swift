//
//  SomeDetailsSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import WrappingHStack

struct SomeDetailsSection: View {
    @Binding var title: String
    var isDeposite: Bool
    var line: WalletLineUiItem?
    var categories: Set<String>
    
    @Binding var showLineSheet: Bool
    @Binding var showCategorySheet: Bool
    
    var focus: FocusState<Bool>.Binding
    
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            Text(
                NSLocalizedString("Title", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
                
            WalletLineNormalTextField(
                text: $title,
                placeHolder: NSLocalizedString("Transaction's title", comment: "")
            ) { _ in
                Image("ic_title")
                    .renderingMode(.template)
                    .foregroundColor(.neutralColorDark)
            }
            .focused(focus)
            .padding(.top, Padding.small)
            if !isDeposite {
                Text(
                    NSLocalizedString("Line", comment: "")
                )
                .titleLargeStyle()
                .foregroundColor(.neutralColorDark)
                .padding(.top, Padding.extraMedium)
            
                if let line = line {
                    WalletLineItem(
                        title: line.title,
                        percentage: line.percentage,
                        balance: line.balance,
                        buttonEnable: false
                    )
                    .onTapGesture {
                        showLineSheet.toggle()
                    }
                    .padding(.top, Padding.small)
                } else {
                    DashedBorderButton(title: NSLocalizedString("Add Line", comment: "")) {
                        showLineSheet.toggle()
                    }
                    .padding(.top, Padding.small)
                }
            }
            
            Text(
                NSLocalizedString("Category", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
            .padding(.top, Padding.extraMedium)
            
            if !categories.isEmpty {
                ZStack {
                    HStack {
                        WrappingHStack(Array(categories), id: \.self) { cat in
                            CategoryChip(name: cat)
                                .padding(.horizontal, Padding.extraSmall)
                                .padding(.vertical, Padding.small)
                        }
                    }
                    .padding()
                }
                .background(
                    RoundedRectangle(cornerRadius: 18)
                        .fill(Color.neutralColor)
                        .overlay(
                            RoundedRectangle(cornerRadius: 18)
                                .stroke(
                                    Color.neutralColorShade6,
                                    lineWidth: 2
                                )
                        )
                )
                .padding(.top, Padding.small)
            } else {
                DashedBorderButton(title: NSLocalizedString("Add Category", comment: "")) {
                    showCategorySheet.toggle()
                }
                .padding(.top, Padding.small)
            }
        }
        .padding(.top, Padding.small)
    }
}

struct SomeDetailsSection_Previews: PreviewProvider {
    static var previews: some View {
        SomeDetailsSection(
            title: .constant(""),
            isDeposite: false,
            line: WalletLineUiItem(
                id: "",
                title: "Savings",
                percentage: 20,
                balance: 333.33,
                description: nil,
                categories: []
            ),
            categories: ["Gym", "Gym", "Gym", "Gym"],
            showLineSheet: .constant(false),
            showCategorySheet: .constant(false),
            focus: FocusState().projectedValue
        )
    }
}
