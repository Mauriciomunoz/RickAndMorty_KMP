import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        DIModulesKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}