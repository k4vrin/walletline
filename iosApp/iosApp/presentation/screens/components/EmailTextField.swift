//
//  EmailTextField.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/16/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct EmailTextField: View {
    @Binding var text: String
    var error: String? = nil

    var body: some View {
        WalletLineMaterialTextField(text: $text, placeHolder: "Email Address") { error in
            ZStack {
                Image("ic_email")
                    .renderingMode(.template)
                    .foregroundColor(error == nil ? .neutralColorShade5 : .errorColor)
            }
            .frame(width: Dimen.TextFieldLeadingIconWidth)

            Divider()
                .overlay(error == nil ? Color.neutralColorShade2 : Color.errorColor)
                .padding(.vertical, Padding.small)
        }
    }

    
}

struct EmailTextField_Previews: PreviewProvider {
    struct Container: View {
        @State var str = ""

        var body: some View {
            EmailTextField(text: $str)
        }
    }

    static var previews: some View {
        VStack {
            Container()
        }
        .padding()
    }
}
