# 🌌 Rick & Morty KMP

A production-grade Kotlin Multiplatform (KMP) application showcasing a unified, robust architecture across Android and iOS. This project demonstrates advanced implementation of shared business logic, multiplatform networking, and state management using modern declarative UI.

---

## 🚀 Overview

This project consumes the Rick and Morty API to display a character list and detailed views. Beyond a simple API fetcher, the architecture is designed to handle real-world edge cases, including corporate proxy network configurations, multiplatform image caching, and platform-specific lifecycle bindings.

## 🛠️ Tech Stack

* **UI:** Compose Multiplatform
* **Architecture:** MVVM (Model-View-ViewModel) with unidirectional data flow (UDF)
* **Dependency Injection:** Koin 4.0
* **Networking:** Ktor 2.x
  * `OkHttp` engine for Android
  * `Darwin` engine for iOS
* **Image Loading:** Coil 3 (configured with Ktor fetcher for KMP)
* **Concurrency:** Kotlin Coroutines & StateFlow

---

## ✨ Key Technical Implementations

### 1. Multiplatform ViewModels & Dependency Injection
Successfully bridged the gap between Android's native lifecycle and iOS's memory management:
* Implemented JetBrains' Multiplatform `ViewModel` to ensure states survive configuration changes (e.g., screen rotation) on Android while remaining safe and stable on iOS.
* Utilized `KoinContext` at the root of the Compose hierarchy, enabling seamless `koinViewModel()` injection inside shared `@Composable` routes without triggering `SIGABRT` crashes on iOS.
* Resolved complex binary incompatibilities (ABI) and linkage errors by strictly aligning JetBrains Lifecycle library versions with Koin expectations.

### 2. Cross-Platform Image Loading
* Integrated **Coil 3** directly into the Compose Multiplatform UI.
* Injected a custom `KtorNetworkFetcherFactory` into the singleton `ImageLoader` to ensure iOS instances can fetch network images seamlessly without relying on Android-specific URL connections.

---

## 🚧 Future Roadmap: Offline-First Architecture

The next major iteration of this project will focus on robust offline support and local data persistence using **Room for Kotlin Multiplatform**.

**Upcoming features include:**
* **KMP SQLite Integration:** Setting up Room with KSP to generate multiplatform Data Access Objects (DAOs).
* **Platform-Specific File Systems:** Implementing native path resolutions (`Context` in Android, `NSFileManager` in iOS) to safely instantiate the `.db` file across different operating systems.
* **Single Source of Truth:** Refactoring the repository layer to fetch from the local Room database, backed by a synchronization strategy with the Ktor remote client.

---

## 📱 Getting Started

### Prerequisites
* **Android Studio** (Latest version with KMP support)
* **Xcode** (For iOS compilation)

### Running the App
1. Clone the repository.
2. Sync the Gradle project.
3. **To run on Android:** Select the `composeApp` (or `androidApp`) run configuration and launch it on an emulator or physical device.
4. **To run on iOS:** Select the `iosApp` run configuration and launch it on an iPhone simulator. *(Note: Ensure `Clean Build Folder` is run in Xcode if testing after version dependency updates).*
