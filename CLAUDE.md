# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Android application (Java) for the **ECE Educational Club** at Sri Vasavi Engineering College. The app is a WebView-based launcher that provides quick access to three external websites, with Firebase-backed push notifications and a remote-config-driven in-app update mechanism.

- **Package**: `com.example.eceeducationalclub`
- **Version**: 1.1.3 (versionCode 2)
- **Min SDK**: 16 | **Target/Compile SDK**: 29

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run a single unit test class
./gradlew test --tests "com.example.eceeducationalclub.ExampleUnitTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Clean build artifacts
./gradlew clean
```

## Architecture

### Activity Flow

```
SplashScreenActivity (LAUNCHER)
    └─► MainActivity (hub with 3 buttons)
            ├─► website1 (WebView)
            ├─► website2 (WebView)
            └─► website3 (WebView)
```

### Key Classes

- **`App.java`** — `Application` subclass; initializes Firebase Remote Config with default update values on startup. Sets default keys: `is_update` (false), `version` ("2.0"), `update_url` (GitHub APK URL). Fetches fresh config with a 30-second cache.

- **`UpdateHelper.java`** — Builder-pattern utility that reads Remote Config to compare the installed app `versionName` against the remote `version` key. If `is_update` is true and versions differ, fires `OnUpdateCheckListener.onUpdateCheckListener(updateURL)`.

- **`MainActivity.java`** — Implements `UpdateHelper.OnUpdateCheckListener`; shows an `AlertDialog` prompting the user to update. Forces light mode via `AppCompatDelegate.MODE_NIGHT_NO`. Three buttons launch the three WebView activities.

- **`website1/2/3.java`** — Nearly identical WebView wrappers. Each loads a hardcoded URL with JavaScript and DOM storage enabled. Back-press navigates within WebView history before finishing the activity. All share `res/menu/example_menu.xml` (refresh + home actions).

- **`services/MyFirebaseInstanceService.java`** — FCM service; handles both notification-payload messages and data-payload messages, creating a `NotificationChannel` (required on Android O+) and posting the notification.

### Hosted URLs

| Activity  | URL                                                        | Purpose                     |
|-----------|------------------------------------------------------------|-----------------------------|
| website1  | `https://sites.google.com/view/ece-educational-club/home` | ECE Educational Club website |
| website2  | `http://srivasaviengg.digivarsity.com/login/index.php`    | Sri Vasavi LMS               |
| website3  | `http://srivasaviengg.ac.in/`                             | Sri Vasavi Engineering College |

`android:usesCleartextTraffic="true"` is set in the manifest to allow the HTTP URLs for website2 and website3.

### Firebase Services

- **Firebase Remote Config** — drives the update-check flow (configured in `App.java` / `UpdateHelper.java`)
- **Firebase Cloud Messaging (FCM)** — push notifications handled by `MyFirebaseInstanceService`
- **Firebase Analytics** — included but not explicitly called in app code

### Update Distribution

Release APKs are hosted directly in this repository under `app/release/`. The default `update_url` in `App.java` points to the raw GitHub URL of `app-release.apk` on the `master` branch. To ship an update: bump `versionName` in `app/build.gradle`, build a release APK, replace `app/release/app-release.apk`, and update the `version` key in Firebase Remote Config to match the new `versionName`.

The signing keystore is `Version2Ece.jks` (root of the repo).
