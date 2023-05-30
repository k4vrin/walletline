//
//  CountDownTimer.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct VerifyEmailTimer: View {
    var time: Int32
    var body: some View {
        HStack(alignment: .firstTextBaseline, spacing: 2) {
            Text(secondsToMinuteAndSeconds(time))
                .headlineLargeStyle()
                .foregroundColor(.neutralColorShade6)
            
            Text(
                NSLocalizedString("Left", comment: "Remain")
            )
            .bodySmallStyle()
            .foregroundColor(.neutralColorShade6)
                
        }
        .animation(.default, value: time)
    }
    
    func secondsToMinuteAndSeconds(_ seconds: Int32) -> String {
        let formatter = DateComponentsFormatter()
        formatter.allowedUnits = [.minute, .second]
        formatter.unitsStyle = .positional
        
        return formatter.string(from: TimeInterval(seconds)) ?? "0:0"
    }
}

struct CountDownTimer_Previews: PreviewProvider {
    static var previews: some View {
        VerifyEmailTimer(time: 32)
    }
}
