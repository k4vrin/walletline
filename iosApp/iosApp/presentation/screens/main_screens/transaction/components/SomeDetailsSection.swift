//
//  SomeDetailsSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SomeDetailsSection: View {
    
    @Binding var title: String
    @Binding var line: WalletLineUiItem?
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
            
            Text(
                NSLocalizedString("Line", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
            .padding(.top, Padding.extraMedium)
            
            if line != nil {
                WalletLineItem(
                    title: line?.title ?? "",
                    percentage: line?.percentage ?? -1,
                    balance: line?.balance ?? -1.1,
                    buttonEnable: false
                )
                .onTapGesture {
                    showLineSheet.toggle()
                }
                .padding(.top, Padding.small)
            } else {
                DashedBorderButton(title: "Add Line") {
                    showLineSheet.toggle()
                }
                .padding(.top, Padding.small)
            }
            
            Text(
                NSLocalizedString("Category", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
            .padding(.top, Padding.extraMedium)
            
            DashedBorderButton(title: "Add Category") {
                showCategorySheet.toggle()
            }
            .padding(.top, Padding.small)
        }
        .padding(.top, Padding.small)
    }
}

struct SomeDetailsSection_Previews: PreviewProvider {
    static var previews: some View {
        SomeDetailsSection(
            title: .constant(""),
            line: .constant(
                WalletLineUiItem(
                    id: "",
                    title: "Savings",
                    percentage: 20,
                    balance: 333.33,
                    description: nil,
                    categories: []
                )
            ), showLineSheet: .constant(false), showCategorySheet: .constant(false), focus: FocusState().projectedValue
        )
    }
}
