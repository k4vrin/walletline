//
//  MoreDetailSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MoreDetailsSection: View {
    var date: Date?
    @Binding var desc: String
    @Binding var isTaxIncluded: Bool
    @Binding var isPeriodical: Bool
    
    var focus: FocusState<Bool>.Binding
    
    var onDateClick: () -> Void
    var onPeriodicalClick: (() -> Void)? = nil
    
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            Text(
                NSLocalizedString("Date", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
                
            WalletLineNormalTextField(
                text: Binding(
                    get: {
                        date?.formatted(date: .abbreviated, time: .omitted) ?? ""
                    },
                    set: { _ in }
                ),
                placeHolder: NSLocalizedString("yyyy/mm/dd", comment: "")
            ) { _ in
                Image("ic_calender")
                    .renderingMode(.template)
                    .foregroundColor(.neutralColorDark)
            }
            .onTapGesture(perform: onDateClick)
            .disabled(true)
            .focused(focus)
            .padding(.top, Padding.small)
            
            
            WLToggleSwitch(
                title: NSLocalizedString("Tax included", comment: ""),
                desc: "It shows the involvement of  this category’s transactions in taxation",
                isOn: $isTaxIncluded
            )
            .padding(.top, Padding.extraMedium)
            
            WLToggleSwitch(
                title: NSLocalizedString("Periodical Withdrawal", comment: ""),
                desc: "By activating this option, it becomes possible to record the periodic withdrawal of this fee.",
                isOn: $isPeriodical,
                onSwitchClick: onPeriodicalClick
            )
            .padding(.top, Padding.extraMedium)
            
            Text(
                NSLocalizedString("Description", comment: "")
            )
            .titleLargeStyle()
            .foregroundColor(.neutralColorDark)
            .padding(.top, Padding.extraMedium)
            
            WalletLineTextEditor(
                text: $desc,
                placeHolder: NSLocalizedString("Describe your category", comment: "")
            )
            .focused(focus)
            .padding(.top, Padding.small)
        }
    }
}

struct MoreDetailsSection_Previews: PreviewProvider {
    static var previews: some View {
        MoreDetailsSection(
            date: .now,
            desc: .constant(""),
            isTaxIncluded: .constant(false),
            isPeriodical: .constant(false),
            focus: FocusState().projectedValue,
            onDateClick: {}
        )
    }
}
