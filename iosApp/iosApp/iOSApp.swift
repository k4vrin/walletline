import SwiftUI
import shared

@main
struct iOSApp: App {
    
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
