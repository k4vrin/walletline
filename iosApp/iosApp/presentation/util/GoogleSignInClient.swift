//
//  GoogleSignInClient.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/15/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import FirebaseCore
import GoogleSignIn
import shared

class GoogleSignInClient {
    
    func signIn(onSuccess: @escaping (SocialSignTypeGoogleAuth) -> Void)  {
        var type = SocialSignTypeGoogleAuth(idToken: nil, accessToken: nil)
        if GIDSignIn.sharedInstance.hasPreviousSignIn() {
            GIDSignIn.sharedInstance.restorePreviousSignIn { user, error in
                if let error = error {
                    print(error.localizedDescription)
                    return
                }
                onSuccess(SocialSignTypeGoogleAuth(idToken: user?.idToken?.tokenString, accessToken: user?.accessToken.tokenString))
            }
        } else {
            guard let clientID = FirebaseApp.app()?.options.clientID else { return }
            let config = GIDConfiguration(clientID: clientID)
            GIDSignIn.sharedInstance.configuration = config
            
            guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene else { return }
            guard let rootViewController = windowScene.windows.first?.rootViewController  else { return }
            
            GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController) { user, error in
                onSuccess(SocialSignTypeGoogleAuth(idToken: user?.user.idToken?.tokenString, accessToken: user?.user.accessToken.tokenString))
            }
        }
    }
    
    func signOut() {
        GIDSignIn.sharedInstance.signOut()
    }
}
