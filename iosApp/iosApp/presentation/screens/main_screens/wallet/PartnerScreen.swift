//
//  PartnerScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PartnerScreen: View {
    @State var patners: [Partner] = []
    @State var isScrolling: Bool = false
    @State var isEditWallet = false
    var body: some View {
        ZStack {
            WalletLineScaffold(isScrolling: $isScrolling, backgroundColor: .neutralColor) { geo in
                ForEach(patners, id: \.name) { patner in
                    ZStack {
                        RoundedRectangle(cornerRadius: 18)
                            .stroke(Color.neutralColorShade6)
                            
                        HStack(spacing: Padding.defaultPadding) {
                            AsyncImage(url: URL(string: patner.pic)) { img in
                                img
                                    .resizable()
                                    .scaledToFit()
                                    .background(Color.neutralColor)
                                    .frame(width: 48, height: 48)
                            } placeholder: {
                                ProgressView()
                                    .frame(width: 48, height: 48)
                            }
                            .clipShape(Circle())
                            .padding(.horizontal, Padding.medium)
                            
                            Text(patner.name)
                                .headlineSmallStyle()
                                .foregroundColor(.neutralColorDark)
                            
                            Spacer()
                        }
                    }
                    .padding(.horizontal, Padding.medium)
                    .frame(height: 65)
                }
            } topBar: {
                DefaultTopBar(title: isEditWallet ? "Choose Partners" : "Partners List", isMoreEnable: false, isScrolling: $isScrolling, menu: {EmptyView()}) {
                    
                }
            }
            
            VStack {
                Spacer()
                WalletLineButton(title: isEditWallet ? "Save" : "Apply") {}
                    .shadow(
                        color: .mainColor.opacity(0.5),
                        radius: 8,
                        y: 8
                    )
                    .padding(.all, Padding.medium)
            }
        }
    }
}

struct PartnerScreen_Previews: PreviewProvider {
    static var previews: some View {
        PartnerScreen(
            patners: [
                Partner(name: "Sarah", pic: "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"),
                Partner(name: "Deana", pic: "https://www.kasandbox.org/programming-images/avatars/spunky-sam-green.png"),
                Partner(name: "Alex", pic: "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
                Partner(name: "Alex", pic: "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
                Partner(name: "Alex", pic: "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
            ]
        )
    }
}
