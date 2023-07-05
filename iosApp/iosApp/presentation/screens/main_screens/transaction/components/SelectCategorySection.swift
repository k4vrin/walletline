//
//  SelectCategorySection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import WrappingHStack

struct SelectCategorySection: View {
    @Binding var selectedCategories: Set<String>
    
    var catagories: [String]
    
    var onAddClick: () -> Void
    var onCancelClick: () -> Void
    
    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.defaultPadding) {
                Text("Select Category")
                    .headlineLargeStyle()
                
                Spacer()
                
                Button {
                    onCancelClick()
                } label: {
                    Text("Cancel")
                        .buttonStyle()
                        .foregroundColor(.infoColorShade4)
                }
            }
            
            WrappingHStack(Array(selectedCategories), id: \.self, alignment: .center) { catagory in
                CategoryChip(name: catagory)
                    .padding(.horizontal, Padding.extraSmall)
                    .padding(.vertical, Padding.small)
            }
            .padding(.vertical, Padding.medium)
            
            ScrollView {
                ForEach(catagories, id: \.self) { catagory in
                    CategorySelectionItem(
                        name: catagory,
                        isSelected: selectedCategories.contains(catagory)
                    )
                    .padding(.horizontal)
                    .onTapGesture {
                        if selectedCategories.contains(catagory) {
                            selectedCategories.remove(catagory)
                        } else {
                            selectedCategories.insert(catagory)
                        }
                    }
                }
            }
            .frame(height: 300)
            .background(Color.neutralColorShade1)
            .clipShape(RoundedCorner(radius: 16))
            
            Button {
                onAddClick()
            } label: {
                Text("+ Add new category")
                    .buttonStyle()
                    .foregroundColor(.infoColorShade4)
            }
            .padding()
        }
        .animation(.default, value: selectedCategories)
    }
}

struct CategorySelectionItem: View {
    var name: String
    var image: Image = .init(systemName: "dumbbell")
    var isSelected: Bool
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8)
                .stroke(isSelected ? Color.infoColorShade2 : Color.clear)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(isSelected ? Color.infoColorShade1 : Color.clear)
                )
            
            HStack {
                ZStack {
                    image
                        .resizable()
                        .scaledToFit()
                }
                .frame(width: 24, height: 24)
                
                Text(name)
                    .bodyLargeStyle()
                Spacer()
                if isSelected {
                    Image(systemName: "checkmark.circle.fill")
                        .renderingMode(.template)
                        .foregroundColor(.infoColor)
                }
            }
            .padding(.horizontal)
        }
        .frame(maxWidth: .infinity)
        .frame(height: 48)
        .animation(.default, value: isSelected)
    }
}

struct SelectCategorySection_Previews: PreviewProvider {
    static var previews: some View {
        SelectCategorySection(
            selectedCategories: .constant([]),
            catagories: ["Gym", "Gym2", "Gym4", "Gymsda", "Gym5", "Gym6", "Gym7"],
            onAddClick: {},
            onCancelClick: {}
        )
    }
}
