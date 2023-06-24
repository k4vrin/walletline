//
//  WalletLineTextEditor.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WalletLineTextEditor: View {
    @Binding var text: String
    var error: String? = nil
    var placeHolder: String
    var body: some View {
        VStack(alignment: .leading,spacing: Padding.defaultPadding) {
            ZStack(alignment: .topLeading) {
                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius)
                    .stroke(error.isNilOrEmpty() ? Color.neutralColorShade6 : .errorColorShade4, lineWidth: 2)

                RoundedRectangle(cornerRadius: Dimen.DefaultButtonCornerRadius)
                    .fill(error.isNilOrEmpty() ? Color.clear : .errorColorShade1)

                TextEditor(text: $text)
                    .bodyLargeStyle()
                    .foregroundColor(.neutralColorDark)
                    .padding(.all, Padding.small)
                
                if text.isEmpty {
                    Text(placeHolder)
                        .bodyLargeStyle()
                        .foregroundColor(.neutralColorShade3)
                        .padding()
                }
            }
            .frame(height: 100)
            
            
            
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

struct WalletLineTextEditor_Previews: PreviewProvider {
    
    struct Container: View {
        @State var str = ""

        var body: some View {
            WalletLineTextEditor(text: $str, error: "", placeHolder: "Describe your category")
                .padding()
        }
    }
    static var previews: some View {
        Container()
    }
}
