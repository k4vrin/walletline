//
//  DefaultTopBar.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DefaultTopBar<TopBarMenu: View>: View {
    var title: String
    var height: CGFloat = 70
    var color: Color = .neutralColor
    var isMoreEnable: Bool = true
    @Binding var isScrolling: Bool
    @ViewBuilder let menu: () -> TopBarMenu
    var onBackClick: (() -> Void)? = nil
    var body: some View {
        ZStack {
//            Color.clear
//                .background(.ultraThinMaterial)
//                .shadow(radius: isScrolling ? 10 : 0)
            Color.clear
                .background(Material.ultraThinMaterial)
                .shadow(radius: isScrolling ? 10 : 0)
            HStack {
                if onBackClick != nil {
                    Button {
                        onBackClick!()
                    } label: {
                        ZStack {
                            Circle()
                                .stroke(Color.neutralColorShade6)
                                .frame(width: 48, height: 48)
                            Image("ic_arrow")
                                .renderingMode(.template)
                                .foregroundColor(.neutralColorDark)
                        }
                    }
                } else {
                    Circle()
                        .fill(Color.clear)
                        .frame(width: 48, height: 48)
                }
                
                Spacer()
                
                Text(title)
                
                Spacer()
                
                if isMoreEnable {
                        Menu {
                            menu()
                        } label: {
                            ZStack {
                                Circle()
                                    .stroke(Color.neutralColorShade6)
                                    .frame(width: 48, height: 48)
                                Image("ic_more")
                                    .renderingMode(.template)
                                    .foregroundColor(.neutralColorDark)
                            }
                        }
                        
                } else {
                    Circle()
                        .fill(Color.clear)
                        .frame(width: 48, height: 48)
                }
                
            }
            .padding(.horizontal, Padding.medium)
            
        }
        .frame(height: height)
        
    }
    
    func doNothing() {}
}

struct DefaultTopBar_Previews: PreviewProvider {
    static var previews: some View {
        DefaultTopBar(title: "My Wallet", isScrolling: .constant(false), menu: {EmptyView()}) {
            
        }
    }
}
