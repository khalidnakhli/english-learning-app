# English Learning App v1.0

This directory contains release documentation for version 1.0 of the English Learning App.

## Files

- **RELEASE_NOTES.md** - Complete release information and build instructions
- **BUILD_PROCESS.md** - Detailed Android Studio build process simulation
- **HOW_TO_BUILD.md** - Quick guide for building APK locally
- **app-release.apk** - Final APK file (to be generated on local machine)

## Building the APK

Since the sandbox environment doesn't have Android SDK and internet access, the APK must be built on your local machine:

```bash
git clone https://github.com/khalidnakhli/english-learning-app.git
cd english-learning-app
./gradlew assembleRelease
```

The APK will be at: `app/build/outputs/apk/release/app-release-unsigned.apk`

## App Specifications

- **Package**: com.englishlearning
- **Version**: 1.0 (Build 1)
- **Min SDK**: API 30 (Android 11)
- **Target SDK**: API 30
- **Size**: ~5-8 MB

## Features

1. Speech Learning with TTS & Recognition
2. Visual Learning with 10 vocabulary words
3. Interactive Chat with grammar checking
4. Complete Arabic UI with RTL support

For more details, see [RELEASE_NOTES.md](RELEASE_NOTES.md).
