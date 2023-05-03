//
//  OnBoardingScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct OnBoardingScreen: View {
    @State private var page = 0
    @State private var isNavActive = false
    
    let pages = [
        OnBoardingPage(
            image: "boarding_1",
            title: NSLocalizedString("You ought to know where your money goes", comment: ""),
            description: NSLocalizedString("Get an overview of how you are performing and motivate yourself to achieve even more.", comment: "")
        ),
        OnBoardingPage(
            image: "boarding_2",
            title: NSLocalizedString("Gain total  control of your money", comment: ""),
            description: NSLocalizedString("Track your transaction easily, with categories and financial report", comment: "")
        ),
        OnBoardingPage(
            image: "boarding_3",
            title: NSLocalizedString("Plan ahead and manage your money better", comment: ""),
            description: NSLocalizedString("Setup your budget for each category so you in control. Track categories you spend the most money on", comment: "")
        )
    ]

    var body: some View {
        WalletLineBackground {
            NavigationLink(
                destination: SocialLoginScreen(),
                isActive: $isNavActive
            ) {
                EmptyView()
            }
            .hidden()
            VStack(alignment: .center, spacing: Padding.defaultPadding) {
                HStack {
                    Spacer()
                    SkipButton {
                        isNavActive = true
                    }
                }
                .padding(.horizontal, Padding.medium)
                
                TabView(selection: $page) {
                    ForEach(0 ..< pages.count, id: \.self) { page in
                        Image(pages[page].image)
                            .tag(page)
                    }
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
                .frame(height: Dimen.OnBoardingImageHeight)
                .padding(.bottom, Padding.smallLarge)
                
                AuthCard {
                    VStack(spacing: Padding.defaultPadding) {
                        Text(pages[page].title)
                            .headlineLargeStyle()
                            .multilineTextAlignment(.center)
                            .padding(.horizontal, Padding.large)
                            .padding(.top, Padding.smallLarge)
                        
                        Text(pages[page].description)
                            .bodyLargeStyle()
                            .multilineTextAlignment(.center)
                            .padding(.horizontal, Padding.large)
                            .padding(.vertical, Padding.extraMedium)
                    }
                    .frame(height: Dimen.OnBoardingCardTextHeight)
                    
                    DotIndicator(totalDots: pages.count, index: $page)
                    
                    Button {
                        if page < pages.count - 1 {
                            page += 1
                        } else {
                            isNavActive = true
                        }
                        
                    } label: {
                        Text(
                            NSLocalizedString("Next", comment: "")
                        )
                        .primaryButtonStyle()
                    }
                    .padding(Padding.extraMedium)
                }
                .padding(.horizontal, Padding.medium)
            }
        }
        .navigationBarBackButtonHidden(true)
        .animation(.default, value: page)
    }
}

struct OnBoardingScreen_Previews: PreviewProvider {
    static var previews: some View {
        OnBoardingScreen()
    }
}
