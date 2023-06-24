//
//  PartnersSection.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PartnersSection: View {
    
    @State var patners: [Partner] = []
    
    var body: some View {
        VStack(alignment: .leading, spacing: Padding.defaultPadding) {
            HStack(spacing: Padding.smallMedium) {
                Text("Partners")
                    .headlineSmallStyle()
                    .foregroundColor(.neutralColorDark)
                if patners.count > 4 {
                    Spacer()
                    Button {
                        
                    } label: {
                        HStack {
                            Text("View all")
                            Image(systemName: "arrow.forward")
                        }
                        .bodySmallStyle()
                        .foregroundColor(.mainColorShade4)
                    }
                }
            }
            .padding(.bottom, Padding.smallMedium)
            HStack(spacing: Padding.smallMedium) {
                ForEach(patners, id: \.name) { partner in
                    VStack(spacing: Padding.defaultPadding) {
                        AsyncImage(url: URL(string: partner.pic)) { img in
                            img
                                .resizable()
                                .scaledToFit()
                                .background(.white)
                                .frame(width: 48, height: 48)
                        } placeholder: {
                            ProgressView()
                                .frame(width: 48, height: 48)
                        }
                        .clipShape(Circle())
                        
                        Text(partner.name)
                            .bodySmallStyle()
                            .foregroundColor(.neutralColorDark)
                            .padding(.top, Padding.extraSmall)
                    }
                }
                if patners.count > 4 {
                    VStack(spacing: Padding.defaultPadding) {
                        ZStack {
                            Color.neutralColor
                                .clipShape(Circle())
                                .frame(width: 48, height: 48)
                            
                            Text("+\(patners.count - 4)")
                        }
                        
                        Text("more")
                            .bodySmallStyle()
                            .foregroundColor(.neutralColorDark)
                            .padding(.top, Padding.extraSmall)
                    }
                }
                VStack(spacing: Padding.defaultPadding) {
                    Button {
                        
                    } label: {
                        ZStack {
                            Circle()
                                .stroke(style: StrokeStyle(dash: [4]))
                                .foregroundColor(.neutralColorShade3)
                            Text("+")
                                .foregroundColor(.neutralColorShade3)
                        }
                        .frame(width: 48, height: 48)
                    }
                    
                    Text("")
                        .bodySmallStyle()
                        .foregroundColor(.neutralColorDark)
                        .padding(.top, Padding.extraSmall)
                }
                
                
                
                Spacer()
            }
        }
        
    }
}

struct PartnersSection_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.neutralColorShade2
            
            PartnersSection(
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
}

struct Partner {
    let name: String
    let pic: String
}
