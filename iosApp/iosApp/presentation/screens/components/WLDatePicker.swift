//
//  WLDatePicker.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WLDatePicker: View {
    @State private var selectedMonthYear: Date = .now
    @State private var currentMonth: Int = 0
    
    @Binding var selectedDate: Date?
    
    let days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
    let columns = Array(repeating: GridItem(.flexible()), count: 7)
    
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            HStack(alignment: .top, spacing: Padding.defaultPadding) {
                Text(extractYearAndMonth())
                    .titleLargeStyle()
                    .foregroundColor(.neutralColorDark)
                
                Spacer()
                
                Button {
                    withAnimation {
                        currentMonth -= 1
                    }
                } label: {
                    Image(systemName: "chevron.left")
                        .titleLargeStyle()
                        .foregroundColor(.neutralColorDark)
                }
                .padding(.trailing, Padding.extraMedium)
                
                Button {
                    withAnimation {
                        currentMonth += 1
                    }
                } label: {
                    Image(systemName: "chevron.right")
                        .titleLargeStyle()
                        .foregroundColor(.neutralColorDark)
                }
            }
            .padding(.horizontal, Padding.smallMedium)
            
            
            HStack(spacing: Padding.defaultPadding) {
                ForEach(days, id: \.self) { day in
                    ZStack {
                        Text(day.uppercased())
                            .bodySmallStyle()
                            .foregroundColor(.neutralColorShade2)
                    }
                    .frame(maxWidth: .infinity)
                        
                    if day != days.last {
                        Spacer()
                    }
                }
            }
            .padding(.top, Padding.extraMedium)
            
            LazyVGrid(columns: columns, spacing: Padding.small) {
                ForEach(extractDate()) { value in
                    CardView(value: value)
                        .onTapGesture {
                            selectedDate = value.date
                        }
                }
            }
            .padding(.top, Padding.extraMedium)
        }
        .onChange(of: currentMonth) { _ in
            selectedMonthYear = getCurrentMonth()
        }
        
    }
    
    @ViewBuilder
    func CardView(value: DateValue) -> some View {
        VStack {
            if value.day != -1 {
                if let selectedDate = selectedDate, isSameDay(date1: value.date, date2: selectedDate) {
                    ZStack {
                        Text("\(value.day)")
                            .font(.custom(PlusJakartaSans.semiBold, size: 24))
                    }
                    .frame(width: 36 ,height: 36)
                    .background(
                        RoundedRectangle(cornerRadius: 6)
                            .fill(Color.mainColorShade1)
                            .overlay(
                                RoundedRectangle(cornerRadius: 6)
                                    .stroke(Color.mainColorShade4, lineWidth: 2)
                            )
                    )
                    
                } else if isSameDay(date1: value.date, date2: Date()) {
                    ZStack {
                        Text("\(value.day)")
                            .headlineLargeStyle()
                    }
                    .frame(width: 32 ,height: 32)
                    .background(
                        RoundedRectangle(cornerRadius: 6)
                            .stroke(lineWidth: 2)
                    )
                    
                    
                } else {
                    ZStack {
                        Text("\(value.day)")
                            .headlineLargeStyle()
                    }
                }
            }
        }
        .frame(height: 40)
        .frame(maxWidth: .infinity)
    }
    
    func isSameDay(date1: Date, date2: Date) -> Bool {
        let calendar = Calendar.current
        return calendar.isDate(date1, inSameDayAs: date2)
    }
    
    func extractYearAndMonth() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "MMMM YYYY"
        
        let date = formatter.string(from: selectedMonthYear)
        
//        return date.components(separatedBy: " ")
        return date
    }
    
    func getCurrentMonth() -> Date {
        let calendar = Calendar.current
        
        guard let currentMonth = calendar.date(byAdding: .month, value: currentMonth, to: Date()) else { return Date() }
        
        return currentMonth
    }
    
    func extractDate() -> [DateValue] {
        let calendar = Calendar.current
        
        let currentMonth = getCurrentMonth()
        
        var days = currentMonth.getAllDates().compactMap { date -> DateValue in
            let day = calendar.component(.day, from: date)
            return DateValue(day: day, date: date)
        }
        
        // adding offset days to get exact week day
        let firstWeekday = calendar.component(.weekday, from: days.first?.date ?? Date())
        
        for _ in 0 ..< firstWeekday - 1 {
            days.insert(DateValue(day: -1, date: Date()), at: 0)
        }
        
        return days
    }
}

struct WLDatePicker_Previews: PreviewProvider {
    static var previews: some View {
        WLDatePicker(selectedDate: .constant(.now))
    }
}

extension Date {
    func getAllDates() -> [Date] {
        let calendar = Calendar.current
        
        let startDate = calendar.date(
            from: Calendar.current.dateComponents([.year, .month], from: self)
        )!
        
        let range = calendar.range(of: .day, in: .month, for: startDate)!
        
        return range.compactMap { day -> Date in
            calendar.date(byAdding: .day, value: day - 1, to: startDate)!
        }
    }
}

struct DateValue: Identifiable {
    var id = UUID().uuidString
    var day: Int
    var date: Date
}
