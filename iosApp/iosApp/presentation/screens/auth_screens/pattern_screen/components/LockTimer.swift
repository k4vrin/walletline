//
//  LockTimer.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/29/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LockTimer: View {
    let size: CGFloat
    @State var timeRemain = 45

    var body: some View {
        ZStack {
            Group {
                Arc()
                    .stroke(style: StrokeStyle(lineWidth: 3, lineCap: .round, lineJoin: .round))
                    .foregroundColor(.neutralColor.opacity(0.2))

                ArcTimer(timeAmount: timePassedInRadians(seconds: timeRemain))
                    .stroke(style: StrokeStyle(lineWidth: 3, lineCap: .round, lineJoin: .round))
                    .foregroundColor(.neutralColor)

                ArcCircle(
                    timeAmount: timePassedInRadians(seconds: timeRemain),
                    circleRadius: 6
                )
                .fill(Color.neutralColor)
                .shadow(color: .neutralColor, radius: 15)
                .shadow(color: .neutralColor, radius: 10)
            }

            Text(formattedTimePassed(timeRemain: timeRemain))
                .font(
                    .custom(
                        PlusJakartaSans.medium,
                        size: 32,
                        relativeTo: .body
                    )
                )
                .foregroundColor(.neutralColor)

        }.frame(width: size, height: size)
    }

    private func timePassedInRadians(seconds: Int) -> Double {
        (Double(60 - seconds) / 60.0) * (Double.pi * 2)
    }

    private func formattedTimePassed(timeRemain: Int) -> String {
        let seconds = 60 - timeRemain
        let formatter = DateComponentsFormatter()
        formatter.allowedUnits = [.minute, .second]
        formatter.zeroFormattingBehavior = .pad
        formatter.unitsStyle = .positional

        return formatter.string(from: TimeInterval(seconds)) ?? "0:0"
    }
}

struct LockTimer_Previews: PreviewProvider {
    static var previews: some View {
        WalletLineAuthBackground { _ in
            LockTimer(size: 211)
                .frame(maxWidth: .infinity)
                .padding(.top)
        }
    }
}

struct Arc: Shape {
    func path(in rect: CGRect) -> Path {
        var path = Path()

        path.addArc(center: CGPoint(x: rect.width / 2, y: rect.height / 2), radius: rect.width / 2, startAngle: .degrees(0), endAngle: .degrees(360), clockwise: false)

        return path
    }
}

struct ArcTimer: Shape {
    var timeAmount: Double

    var animatableData: Double {
        get { timeAmount }
        set { timeAmount = newValue }
    }

    func path(in rect: CGRect) -> Path {
        var path = Path()

        let degree = (180 / Double.pi) * timeAmount

        path.addArc(
            center: CGPoint(x: rect.width / 2, y: rect.height / 2),
            radius: rect.width / 2,
            startAngle: .degrees(-90),
            endAngle: .degrees(degree - 90),
            clockwise: false
        )

        return path
    }
}

struct ArcCircle: Shape {
    var timeAmount: Double
    var circleRadius: CGFloat

    var animatableData: Double {
        get { timeAmount }
        set { timeAmount = newValue }
    }

    func path(in rect: CGRect) -> Path {
        var path = Path()

        let radius = rect.width / 2

        let x = (sin(Double.pi - timeAmount) * radius) + radius
        let y = (cos(Double.pi - timeAmount) * radius) + radius

        path.addArc(
            center: CGPoint(x: x, y: y),
            radius: circleRadius,
            startAngle: .degrees(0),
            endAngle: .degrees(360),
            clockwise: false
        )

        return path
    }
}
