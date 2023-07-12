//
//  FrequencySection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FrequencySection: View {
    @Binding var selected: Frequency?
    @Binding var customSelection: [DayFrequency]
    
    var columns: [GridItem] {
        [GridItem(.fixed(2))]
    }

    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.defaultPadding) {
                Text("Frequency")
                    .bodySmallStyle()
                    .foregroundColor(.neutralColorDark)

                Spacer()
            }

            HStack(alignment: .top, spacing: 90) {
                VStack(alignment: .leading, spacing: Padding.extraMedium) {
                    WLRadioButton(
                        tag: .Daily,
                        selection: $selected,
                        label: {
                            Text("Daily")
                                .titleLargeStyle()
                                .foregroundColor(.neutralColorDark)
                        }
                    )
                    
                    WLRadioButton(
                        tag: .Monthly,
                        selection: $selected,
                        label: {
                            Text("Monthly")
                                .titleLargeStyle()
                                .foregroundColor(.neutralColorDark)
                        }
                    )
                    
                    WLRadioButton(
                        tag: .Custom,
                        selection: $selected,
                        label: {
                            Text("Custom")
                                .titleLargeStyle()
                                .foregroundColor(.neutralColorDark)
                        }
                    )
                }
                
                VStack(alignment: .leading, spacing: Padding.extraMedium) {
                    WLRadioButton(
                        tag: .Weekly,
                        selection: $selected,
                        label: {
                            Text("Weekly")
                                .titleLargeStyle()
                                .foregroundColor(.neutralColorDark)
                        }
                    )
                    
                    WLRadioButton(
                        tag: .Yearly,
                        selection: $selected,
                        label: {
                            Text("Yearly")
                                .titleLargeStyle()
                                .foregroundColor(.neutralColorDark)
                        }
                    )
                }
            }
            .padding(.top, Padding.extraMedium)
            
            if selected == .Custom {
                FrequencyCustom(selections: $customSelection)
                    .padding(.top, Padding.extraMedium)
            }
        }
        
    }
}

struct FrequencyCustom: View {
    @Binding var selections: [DayFrequency]
    
    var body: some View {
        HStack(spacing: Padding.smallMedium) {
            Text("On")
                .titleLargeStyle()
                .foregroundColor(.neutralColorDark)
            
            FrequencyCustomButton(
                text: "S",
                tag: .Sunday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "M",
                tag: .Monday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "T",
                tag: .Tuesday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "W",
                tag: .Wednesday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "T",
                tag: .Thursday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "F",
                tag: .Friday,
                selections: $selections
            )
            
            FrequencyCustomButton(
                text: "S",
                tag: .Saturday,
                selections: $selections
            )
        }
    }
}

struct FrequencyCustomButton<Tag>: View where Tag: Hashable {
    var text: String
    
    let tag: Tag
    
    @Binding var selections: [Tag]
    
    var body: some View {
        ZStack {
            RoundedCorner(radius: 4)
                .fill(
                    selections.contains(tag) ? Color.mainColorShade4 : Color.neutralColorShade6
                )
                
            Text(text)
                .titleLargeStyle()
                .foregroundColor(
                    selections.contains(tag) ? Color.neutralColor : Color.neutralColorDark
                )
        }
        .onTapGesture {
            if !selections.contains(tag) {
                selections.append(tag)
            } else if let index = selections.firstIndex(of: tag) {
                selections.remove(at: index)
            }
        }
        .frame(width: 28, height: 28)
    }
}

enum Frequency: String {
    case Daily, Weekly, Monthly, Yearly, Custom
}

enum DayFrequency: String {
    case Saturday, Sunday, Monday, Tuesday, Wednesday, Thursday, Friday
}

struct FrequencySection_Previews: PreviewProvider {
    static var previews: some View {
        FrequencySection(
            selected: .constant(.Daily), customSelection: .constant([])
        )
//        FrequencyCustom(selections: .constant([]))
    }
}
