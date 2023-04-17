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
    @FocusState private var focusField: Bool
    @State private var placeholderBottomPadding = 0.0
    @State private var placeholderFontSize = 16.0
    var error: String? = nil

    var body: some View {
        HStack(spacing: Padding.defaultPadd) {
            ZStack {
                Image("ic_email")
            }
            .frame(width: 51)

            Divider()
                .padding(.vertical, Padding.small)
                .padding(.trailing, Padding.smallMedium)

            ZStack {
                TextField(
                    NSLocalizedString("", comment: ""),
                    text: $text
                )
                .keyboardType(.emailAddress)
                .focused($focusField)
                .onChange(of: focusField) { focus in
                    withAnimation(.easeOut(duration: 0.1)) {
                        updatePlaceholder(focus)
                    }
                }

                HStack {
                    ZStack {
                        Text(
                            NSLocalizedString("Email Address", comment: "")
                        )
                            .animatableFont(size: placeholderFontSize)
                            .onTapGesture {
                                focusField.toggle()
                            }
                    }
                    .padding(.bottom, placeholderBottomPadding)
                    Spacer()
                }
            }

            Button {
                text = ""
            } label: {
                Image("ic_cancel")
            }
            .frame(width: 51)
            .opacity(text.isEmpty ? 0 : 1)
        }
        .frame(height: 56)
        .overlay(
            RoundedRectangle(cornerRadius: Dimen.defaultCornerRadius)
                .stroke(Color.gray, lineWidth: Dimen.orDividerHeight)
        )
        .toolbar {
            ToolbarItem(placement: .keyboard) {
                Button(
                    NSLocalizedString("Done", comment: "")
                ) {
                    focusField.toggle()
                }
                .frame(maxWidth: .infinity, alignment: .trailing)
            }
        }
    }

    func updatePlaceholder(_ isFocused: Bool) {
        placeholderFontSize = isFocused ? 11.0 : 14.0

        if isFocused {
            placeholderBottomPadding = 34.0
        } else {
            placeholderBottomPadding = 0.0
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
