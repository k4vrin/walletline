import SwiftUI
import shared

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
		}
	}
}
