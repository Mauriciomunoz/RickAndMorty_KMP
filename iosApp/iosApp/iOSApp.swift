import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoiniosKt.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}