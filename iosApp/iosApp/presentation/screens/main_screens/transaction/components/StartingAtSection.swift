//
//  StartingAtSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct StartingAtSection: View {
    
    @Binding var selectedDate: Date?
    
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.defaultPadding) {
                Text(
                NSLocalizedString("Starting At", comment: "")
                )
                    .bodySmallStyle()
                    .foregroundColor(.neutralColorDark)
                
                Spacer()
            }
            .padding(.horizontal, Padding.smallMedium)
            
            WLDatePicker(selectedDate: $selectedDate)
                .padding(.top, Padding.extraMedium)
                
        }
    }
}

struct StartingAtSection_Previews: PreviewProvider {
    static var previews: some View {
        StartingAtSection(selectedDate: .constant(.now))
    }
}
