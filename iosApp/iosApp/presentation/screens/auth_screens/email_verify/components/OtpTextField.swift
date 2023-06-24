//
//  OtpTextField.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/22/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct OtpTextField: View {
    @Binding var otpText: String
    @FocusState private var isKeyboardShowing: Bool
    var body: some View {
        HStack(spacing: 20) {
            ForEach(0 ..< 4, id: \.self) { index in
                OTPTextContainer(index: index)
            }
        }
        .background {
            TextField("", text: $otpText.limit(length: 4))
                .keyboardType(.numberPad)
                .textContentType(.oneTimeCode)
                .frame(width: 1, height: 1)
                .opacity(0.001)
                .blendMode(.screen)
                .focused($isKeyboardShowing)
                // Prevent type letters
                .onReceive(
                    Just(otpText)
                ) { newValue in
                    let filtered = newValue.filter {
                        "0123456789".contains($0)
                    }
                    if filtered != newValue {
                        self.otpText = filtered
                    }
                }
        }
        .contentShape(Rectangle())
        .onTapGesture {
            isKeyboardShowing.toggle()
        }
        .toolbar {
            ToolbarItem(placement: .keyboard) {
                Button("Done") {
                    isKeyboardShowing.toggle()
                }
                .frame(maxWidth: .infinity, alignment: .trailing)
            }
        }
    }

    @ViewBuilder
    func OTPTextContainer(index: Int) -> some View {
        var borderColor: Color {
            if isKeyboardShowing && otpText.count == index {
                return .mainColorShade4
            }
            if otpText.count < index {
                return .neutralColorShade3
            }
            return .mainColorShade3
        }
        ZStack {
            if otpText.count > index {
                let startIndex = otpText.startIndex
                let charIndex = otpText.index(startIndex, offsetBy: index)
                let charToString = String(otpText[charIndex])
                Text(charToString)
            } else {
                Text("")
            }
        }
        .titleLargeStyle()
        .foregroundColor(.neutralColorDark)
        .frame(width: 47, height: 57)
        .background {
            Rectangle()
                .fill(Color.primaryColor.opacity(0))
                .overlay(
                    RoundedCorner(radius: 8)
                        .stroke(style: StrokeStyle(lineWidth: 1.5))
                        .foregroundColor(borderColor),
                    alignment: .bottom
                )
                .animation(.easeInOut(duration: 0.2), value: index)
        }
    }
}

extension Binding where Value == String {
    func limit(length: Int) -> Self {
        if self.wrappedValue.count > length {
            DispatchQueue.main.async {
                self.wrappedValue = String(self.wrappedValue.prefix(length))
            }
        }
        return self
    }
}

struct OtpTextField_Previews: PreviewProvider {
    static var previews: some View {
        OtpTextField(otpText: .constant("12"))
    }
}
