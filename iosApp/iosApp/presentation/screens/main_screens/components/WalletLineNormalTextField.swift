//
//  WalletLineNormalTextField.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineNormalTextField<LeadingIcon: View>: View {
    @Binding var text: String
    var error: String? = nil
    var placeHolder: String
    @ViewBuilder var leadingIcon: (_ error: String?) -> LeadingIcon
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            ZStack {
                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius)
                    .stroke(error.isNilOrEmpty() ? Color.neutralColorShade6 : .errorColorShade4, lineWidth: 2)

                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius)
                    .fill(error.isNilOrEmpty() ? Color.clear : .errorColorShade1)

                HStack(alignment: .center, spacing: Padding.defaultPadding) {
                    leadingIcon(error)
                        .padding(.horizontal, Padding.medium)

                    TextField("", text: $text)
                        .bodyLargeStyle()
                        .foregroundColor(.neutralColorDark)
                        .placeholder(when: text.isEmpty) {
                            Text(placeHolder)
                                .bodyLargeStyle()
                                .foregroundColor(.neutralColorShade3)
                        }

                    Button {
                        text = ""
                    } label: {
                        Image("ic_cancel")
                    }
                    .frame(width: Dimen.TextFieldLeadingIconWidth)
                    .opacity(text.isEmpty ? 0 : 1)
                }
            }
            .frame(height: 52)
            if error != nil {
                Text(error!)
                    .bodySmallStyle()
                    .foregroundColor(.errorColor)
                    .padding(.top, Padding.small)
            }
        }
        .animation(.default, value: error)
    }
}

struct WalletLineNormalTextField_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineNormalTextField(text: .constant(""), error: " ds", placeHolder: "Title") { _ in
            Image("ic_title")
                .renderingMode(.template)
                .foregroundColor(.neutralColorDark)
        }
    }
}
