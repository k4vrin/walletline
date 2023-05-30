//
//  BiometricPrompt.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import LocalAuthentication
import shared

class BiometricClient {
    
    func showBiometricPrompt(onSuccess: @escaping () -> Void, onError: @escaping (String) -> Void) {
        let context = LAContext()
        var error: NSError?
        
        
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            let reason = "Identify phone owner"
            
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: reason) { success, authError in
                DispatchQueue.main.async {
                    if success {
                        onSuccess()
                    } else {
                        onError(authError?.localizedDescription ?? NSLocalizedString("Unknown Error", comment: ""))
                    }
                }
            }
        } else {
            onError(error?.localizedDescription ?? NSLocalizedString("Unknown Error", comment: ""))
        }
    }
}
