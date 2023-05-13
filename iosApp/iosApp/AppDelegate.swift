//
//  AppDelegate.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import UserNotifications

class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        UNUserNotificationCenter.current().delegate = self

        return true
    }
    
    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        willPresent notification: UNNotification,
        withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void)
    {
        // Update the app interface directly.
        
        // Show a banner
        completionHandler(.banner)

    }
}
