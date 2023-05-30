import FBSDKCoreKit
import shared
import SwiftUI

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    init() {
        KoinHelperKt.doInitIosKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationView {
                SplashScreen()
            }
            .onOpenURL(
                perform: { url in
                    ApplicationDelegate.shared.application(
                        UIApplication.shared,
                        open: url,
                        sourceApplication: nil,
                        annotation: [UIApplication.OpenURLOptionsKey.annotation]
                    )
                }
            )
        }
    }
}
