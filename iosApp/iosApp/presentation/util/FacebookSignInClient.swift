//
//  FacebookSignInClient.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 5/16/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import FBSDKLoginKit
import shared


class FacebookSignInClient {
    
    func signIn(onSuccess: @escaping (SocialSignTypeFacebookAuth) -> Void) {
        LoginManager().logIn(permissions: ["email", "public_profile"], from: nil) { (result, error) in
            if let error = error {
                print(error)
                return
            }
            
            guard let accessToken = result?.token?.tokenString else { return }
            onSuccess(SocialSignTypeFacebookAuth(accessToken: accessToken))
        }
    }
    
    
    func signOut() {
        LoginManager().logOut()
    }
}
