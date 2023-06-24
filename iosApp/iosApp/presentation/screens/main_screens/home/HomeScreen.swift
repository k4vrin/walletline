//
//  HomeScreen.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @ObservedObject var navController: NavigationController
    var body: some View {
        VStack{
            Text("Home Screen")
            NavigationLink(
                destination: EmailLoginScreen(),
                isActive: $navController.detailIsShown
            ) {
                Button("Detail") {
                    navController.detailIsShown = true
                }
            }
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen(navController: NavigationController())
    }
}
