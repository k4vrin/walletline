//
//  DurationSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DurationSection: View {
    @Binding var selected: Duration?
    @Binding var times: Int
    @Binding var date: Date?
    var isTimesSelected: Bool {
        if case .Times = selected {
            return true
        } else {
            return false
        }
    }
    
    
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.defaultPadding) {
                Text(NSLocalizedString("Duration", comment: ""))
                    .bodySmallStyle()
                    .foregroundColor(.neutralColorDark)
                
                Spacer()
            }
            .padding(.horizontal, Padding.smallMedium)
           
            VStack(alignment: .leading, spacing: Padding.extraMedium) {
                WLRadioButton(
                    tag: .Forever,
                    selection: $selected,
                    label: {
                        Text(NSLocalizedString("Forever", comment: ""))
                            .titleLargeStyle()
                            .foregroundColor(.neutralColorDark)
                    }
                )
                
                WLRadioButton(
                    tag: .Times,
                    selection: $selected,
                    label: {
                        if isTimesSelected {
                            TextField(value: $times, format: .number, label: {})
                                .multilineTextAlignment(.center)
                                .frame(width: 32)
                                .background(
                                    RoundedRectangle(cornerRadius: 4)
                                        .fill(Color.neutralColorShade6)
                                )
                        }
                        
                        
                        
                        Text(isTimesSelected ? NSLocalizedString("time(s) total", comment: "") : NSLocalizedString("Specific number of times", comment: ""))
                            .titleLargeStyle()
                            .foregroundColor(.neutralColorDark)
                    }
                )
                
                WLRadioButton(
                    tag: .Until,
                    selection: $selected,
                    label: {
                        Text(NSLocalizedString("Until", comment: ""))
                            .titleLargeStyle()
                            .foregroundColor(.neutralColorDark)
                    }
                )
            }
            .padding(.top, Padding.extraMedium)
            .padding(.horizontal, Padding.smallMedium)
            
            if case .Until = selected {
                WLDatePicker(selectedDate: $date)
                    .padding(.top, Padding.extraMedium)
            }
        }
    }
}

enum Duration: Hashable {
    case Forever, Times, Until
}

struct DurationSection_Previews: PreviewProvider {
    static var previews: some View {
        DurationSection(selected: .constant(.Forever), times: .constant(1), date: .constant(nil))
    }
}
