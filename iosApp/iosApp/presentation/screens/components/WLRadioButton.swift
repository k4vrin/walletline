//
//  WalletLineRadioButton.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WLRadioButton<Tag, Label>: View where Tag: Hashable, Label: View {
    
    let tag: Tag
    
    @Binding var selection: Tag?
    
    @ViewBuilder let label: () -> Label

    var body: some View {
        Button {
//            if selection != tag {
//                selection = tag
//            } else {
//                selection = nil
//            }
            selection = tag
        } label: {
            HStack {
                Circle()
                    .stroke(Color.mainColorShade4, lineWidth: 2.5)
                    .overlay(
                        selection == tag ? Circle()
                            .frame(width: 16)
                            .foregroundColor(.mainColorShade4)
                        
                        : nil
                    )
                    .frame(width: 24)
                label()
            }
        }
        .buttonStyle(.plain)
        
    }
}

struct WalletLineRadioButton_Previews: PreviewProvider {
    static var previews: some View {
        WLRadioButton(
            tag: "abc",
            selection: .constant("abc"),
            label: {
                Text("Str")
            }
        )
    }
}
