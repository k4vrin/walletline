//
//  CustomToggleSwitch.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WLToggleSwitch: View {
    
    var title: String
    var desc: String
    
    @Binding var isOn: Bool
    
    var onSwitchClick: ((Bool) -> Void)? = nil
    
    var body: some View {
        VStack(alignment: .leading ,spacing: Padding.defaultPadding) {
            HStack {
                Text(
                    title
                )
                .titleLargeStyle()
                .foregroundColor(.neutralColorDark)
                
                Spacer()
                
                Toggle("", isOn: $isOn)
                    .toggleStyle(SwitchToggleStyle())
                    .tint(.mainColor)
                    .padding(.trailing, Padding.extraSmall)
                    .onTapGesture {
                        onSwitchClick?(isOn)
                    }
            }

            Text(
                desc
            )
            .bodySmallStyle()
            .padding(.trailing, Padding.extraLarge)
            .padding(.top, Padding.small)
            .foregroundColor(.neutralColorShade3)
        }
    }
}

struct CustomToggleSwitch_Previews: PreviewProvider {
    static var previews: some View {
        WLToggleSwitch(
            title: NSLocalizedString("Tax included", comment: ""),
            desc: "It shows the involvement of  this category’s transactions in taxation",
            isOn: .constant(false)
        )
    }
}
