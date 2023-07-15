//
//  PeriodicalWithdrawSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PeriodicalWithdrawSection: View {
    @Binding var selectedFrequency: Frequency?
    @Binding var selectedCustomFrequency: [DayFrequency]
    @Binding var selectedStartDate: Date?
    @Binding var selectedEndDate: Date?
    @Binding var selectedDuration: Duration?
    @Binding var selectedDurationTimes: Int
    
    var body: some View {
        ScrollView {
            
            
            FrequencySection(
                selected: $selectedFrequency,
                customSelection: $selectedCustomFrequency
            )
            .padding(.top, Padding.extraMedium)
            .padding(.horizontal, Padding.medium)
            
            StartingAtSection(selectedDate: $selectedStartDate)
                .padding(.top, Padding.smallLarge)
                .padding(.horizontal, 2)
            
            DurationSection(
                selected: $selectedDuration,
                times: $selectedDurationTimes,
                date: $selectedEndDate
            )
                .padding(.top, Padding.smallLarge)
                .padding(.horizontal, 2)
        }
        .animation(.spring(), value: selectedFrequency)
    }
}

struct PeriodicalWithdrawSection_Previews: PreviewProvider {
    static var previews: some View {
        PeriodicalWithdrawSection(
            selectedFrequency: .constant(nil),
            selectedCustomFrequency: .constant([]),
            selectedStartDate: .constant(nil),
            selectedEndDate: .constant(nil),
            selectedDuration: .constant(nil),
            selectedDurationTimes: .constant(1)
        )
    }
}
