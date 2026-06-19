# ECE Educational Club App

An Android application for the **ECE Educational Club** at Sri Vasavi Engineering College. The app provides quick access to three external websites via WebView, along with Firebase-backed push notifications and a remote-config-driven in-app update system.

---

## Features

- **ECE Educational Club Website** — Club announcements, events, and resources
- **Sri Vasavi LMS** — Learning Management System login portal
- **Sri Vasavi Engineering College Website** — College official website
- **Push Notifications** — Firebase Cloud Messaging for club announcements
- **In-App Update Prompts** — Driven by Firebase Remote Config

---

## Tech Stack

| Category       | Technology                              |
|----------------|-----------------------------------------|
| Language       | Java                                    |
| Min SDK        | 16                                      |
| Target SDK     | 29                                      |
| Build System   | Gradle                                  |
| Firebase       | Analytics, Remote Config, FCM           |
| UI             | WebView, ConstraintLayout, Material UI  |

---

## App Architecture

### Activity Flow

```
SplashScreenActivity (Launcher)
    └─► MainActivity (3 quick-access buttons)
            ├─► website1  →  ECE Educational Club website
            ├─► website2  →  Sri Vasavi LMS
            └─► website3  →  Sri Vasavi Engineering College
```

### Key Components

- **`SplashScreenActivity`** — 2-second splash screen using the `EasySplashScreen` library, then navigates to `MainActivity`.
- **`MainActivity`** — Main hub; three styled buttons launch WebView screens. Also runs an update check on every launch.
- **`website1 / website2 / website3`** — Full-screen WebView activities. All support back-navigation within the web history and share a common overflow menu (Refresh, Home).
- **`App`** — `Application` subclass that initialises Firebase Remote Config defaults on startup.
- **`UpdateHelper`** — Builder-pattern utility that reads Remote Config (`is_update`, `version`, `update_url`) and notifies `MainActivity` when a newer version is available.
- **`MyFirebaseInstanceService`** — FCM service that handles both notification-payload and data-payload messages and posts system notifications.

---

## Firebase Remote Config — Update Keys

| Key          | Default Value                                        | Description                       |
|--------------|------------------------------------------------------|-----------------------------------|
| `is_update`  | `false`                                              | Toggle to enable the update prompt|
| `version`    | `2.0`                                                | Target version string to compare  |
| `update_url` | GitHub raw URL of `app/release/app-release.apk`     | Download link shown in the dialog |

To push a new update: bump `versionName` in `app/build.gradle`, build a release APK, replace `app/release/app-release.apk`, then update `version` in the Firebase console and set `is_update` to `true`.

---

## Build & Run

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest

# Clean
./gradlew clean
```

The signing keystore is `Version2Ece.jks` located at the project root.

---

## Project Structure

```
app/src/main/
├── java/com/example/eceeducationalclub/
│   ├── App.java                        # Application class, Remote Config init
│   ├── MainActivity.java               # Home screen with 3 navigation buttons
│   ├── SplashScreenActivity.java       # Launcher splash screen
│   ├── UpdateHelper.java               # Remote Config update-check utility
│   ├── website1.java                   # WebView → ECE Educational Club site
│   ├── website2.java                   # WebView → Sri Vasavi LMS
│   ├── website3.java                   # WebView → Sri Vasavi Engineering College
│   └── services/
│       └── MyFirebaseInstanceService.java  # FCM push notification handler
├── res/
│   ├── layout/                         # XML layouts for each activity
│   ├── menu/example_menu.xml           # Shared overflow menu (Refresh, Home)
│   ├── drawable/                       # Icons, background, rounded button shape
│   ├── font/                           # Custom fonts (Aclonica, Eagle Lake, Sancreek)
│   └── values/                         # Colors, strings, themes, dimensions
└── AndroidManifest.xml
```

---

## Permissions

| Permission             | Reason                                      |
|------------------------|---------------------------------------------|
| `INTERNET`             | Required to load WebView content and Firebase|
| `ACCESS_NETWORK_STATE` | Check connectivity (SDK 23+)                |

`android:usesCleartextTraffic="true"` is enabled in the manifest to allow the HTTP URLs used by website2 (LMS) and website3 (college site).
