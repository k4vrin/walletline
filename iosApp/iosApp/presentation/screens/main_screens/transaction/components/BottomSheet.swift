//
//  BottomSheet.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BottomSheet<Content: View>: View {
    var title: String
    var buttonTitle: String = "Add"
    
    @State var offset: CGFloat = 0
    @Binding var showSheet: Bool
    
    var geo: GeometryProxy? = nil
    
    @ViewBuilder var content: () -> Content
    
    var onDoneClick: () -> Void
    var onCancelClick: () -> Void

    var body: some View {
        VStack(spacing: Padding.defaultPadding) {
            Spacer()
                
            VStack(spacing: Padding.defaultPadding) {
                Capsule()
                    .fill(Color.neutralColorDark)
                    .frame(width: 40, height: 5)
                
                HStack(spacing: Padding.defaultPadding) {
                    Text(title)
                        .headlineLargeStyle()
                        .foregroundColor(.neutralColorDark)
                    
                    Spacer()
                    
                    Button {
                        onCancelClick()
                    } label: {
                        Text("Cancel")
                            .buttonStyle()
                            .foregroundColor(.infoColorShade4)
                    }
                }
                .padding(.horizontal, Padding.medium)
                .padding(.vertical)
                
                VStack(spacing: Padding.defaultPadding) {
                    content()
                        .frame(alignment: .top)
                        .simultaneousGesture(DragGesture(minimumDistance: 10), including: .all)
                        
                    WalletLineButton(
                        title: buttonTitle,
                        action: {
                            onDoneClick()
                        }
                    )
                    .shadow(
                        color: .mainColor.opacity(0.5),
                        radius: 8,
                        y: 8
                    )
                    .padding(.horizontal, Padding.medium)
                    .padding(.bottom, geo?.safeAreaInsets.bottom ?? Padding.large)
                }
            }
            .padding(.top)
            .background(Color.neutralColor)
            .clipShape(
                RoundedCorner(
                    radius: 24,
                    corners: [.topLeft, .topRight]
                )
            )
            .offset(y: offset)
            .offset(y: showSheet ? 0 : UIScreen.main.bounds.height)
            .gesture(
                DragGesture()
                    .onChanged(onChanged)
                    .onEnded(onEnded)
            )
            .onTapGesture {}
        }
        .ignoresSafeArea()
        .padding(.top, geo?.safeAreaInsets.top)
        .background(Color.black.opacity(showSheet ? 0.3 : 0.0).ignoresSafeArea())
        .onTapGesture {
            onCancelClick()
        }
        .animation(.easeIn(duration: 0.2), value: showSheet)
        .frame(height: showSheet ? nil : 0)
    }
    
    func onChanged(value: DragGesture.Value) {
        if value.translation.height > 0 {
            offset = value.translation.height
        }
    }
    
    func onEnded(value: DragGesture.Value) {
        if value.translation.height > 0 {
            withAnimation(.easeIn(duration: 0.2)) {
                let height = UIScreen.main.bounds.height / 3
                
                if value.translation.height > height / 3 {
                    showSheet.toggle()
                }
                
                offset = 0
            }
        }
    }
}

struct BottomSheet_Previews: PreviewProvider {
    static var previews: some View {
        BottomSheet(
            title: "Date",
            showSheet: .constant(true), content: {
            ScrollView {
                HStack {
                    Text("Select line to borrow from")
                    Spacer()
                    Text("Cnacel")
                }
                
                HStack {
                    Text("Select line to borrow from")
                    Spacer()
                    Text("Cnacel")
                }
            }
        },
        onDoneClick: {},
        onCancelClick: {})
    }
}
