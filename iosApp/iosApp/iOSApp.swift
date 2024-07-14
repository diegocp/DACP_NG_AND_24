import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        CoreIosKt.doInitIosCore()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
