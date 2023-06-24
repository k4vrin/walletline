//
//  MainView.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainView: View {
    @StateObject var navController = NavigationController()
    var body: some View {
        ZStack {
            VStack {
                switch navController.currentGraph {
                case .home:
                    HomeScreen(navController: navController)
                case .wallet:
                    WalletScreen(navController: navController)
                }
                Spacer()
            }

            VStack {
                Spacer()
                if !navController.detailIsShown {
                    BottomNavBar(navController: navController)
                        .padding(.horizontal, Padding.medium)
                }
            }
        }
        .frame(maxHeight: .infinity)
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            MainView()
        }
    }
}
