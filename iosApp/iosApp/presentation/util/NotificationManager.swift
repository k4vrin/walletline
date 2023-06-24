//
//  NotificationManager.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UserNotifications


class NotificationManager {
    
    static let instance = NotificationManager()
    
    func requestPermission(handler: @escaping (Bool, Error?) -> Void) {
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound], completionHandler: handler)
    }
    
    func sendOtpNotification(otp: String) {
        let content = UNMutableNotificationContent()
        content.title = "Verification Code"
        content.subtitle = "Your verification code is"
        content.body = otp
        content.sound = UNNotificationSound.default

        let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 0.1, repeats: false)

        let request = UNNotificationRequest(identifier: UUID().uuidString, content: content, trigger: trigger)

        UNUserNotificationCenter.current().add(request)
    }
    
}
