//
//  NavigationController.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class NavigationController: ObservableObject {
    @Published var currentGraph: Page = .home
    @Published var detailIsShown: Bool = false
}

enum Page {
    case home
    case wallet
}
