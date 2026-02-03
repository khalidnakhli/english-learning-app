# Setup Guide - English Learning App

## Prerequisites

- **Android Studio**: Arctic Fox (2020.3.1) or later
- **Java Development Kit (JDK)**: Version 11 or later
- **Android SDK**: API level 30 (Android 11) or higher
- **Minimum Device Requirements**: Android 11 (API 30) or higher

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/khalidnakhli/english-learning-app.git
cd english-learning-app
```

### 2. Open in Android Studio

1. Open Android Studio
2. Click on "Open an Existing Project"
3. Navigate to the cloned repository folder and select it
4. Click "OK"

### 3. Gradle Sync

Android Studio will automatically start syncing Gradle. If it doesn't:
1. Click on "File" → "Sync Project with Gradle Files"
2. Wait for the sync to complete (may take a few minutes on first run)

### 4. Configure Android SDK

1. Open "Tools" → "SDK Manager"
2. Ensure Android 11 (API level 30) is installed
3. Install any missing components if prompted

### 5. Run the Application

#### Using an Emulator:
1. Click "Tools" → "AVD Manager"
2. Create a new virtual device with API level 30 or higher
3. Click the "Run" button (green play icon) in Android Studio
4. Select your emulator from the list

#### Using a Physical Device:
1. Enable "Developer Options" on your Android device:
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
2. Enable "USB Debugging" in Developer Options
3. Connect your device via USB
4. Click the "Run" button in Android Studio
5. Select your device from the list

### 6. Grant Permissions

When running the app for the first time:
- The app will request **Microphone** permission for speech recognition
- Grant the permission to use the Speech Learning feature

## Project Structure Overview

```
english-learning-app/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/englishlearning/
│   │       │   ├── MainActivity.kt              # Main entry point
│   │       │   ├── ui/
│   │       │   │   ├── speech/                  # Speech learning feature
│   │       │   │   ├── visual/                  # Visual learning feature
│   │       │   │   └── chat/                    # Chat feature
│   │       │   ├── data/model/                  # Data models
│   │       │   └── utils/                       # Utility classes
│   │       ├── res/
│   │       │   ├── layout/                      # XML layouts
│   │       │   ├── values/                      # Strings, colors, themes (English)
│   │       │   ├── values-ar/                   # Arabic translations
│   │       │   ├── navigation/                  # Navigation graph
│   │       │   └── menu/                        # Bottom navigation menu
│   │       └── AndroidManifest.xml
│   └── build.gradle                             # App-level Gradle config
├── build.gradle                                 # Project-level Gradle config
├── settings.gradle                              # Gradle settings
└── README.md
```

## Features

### 1. التعلم بالصوت (Speech Learning)
Navigate to the "التعلم بالصوت" tab to:
- Listen to English sentences
- Record your pronunciation
- Get instant feedback on accuracy
- See your pronunciation score

### 2. التعلم بالصور (Visual Learning)
Navigate to the "التعلم بالصور" tab to:
- View English words with Arabic translations
- See phonetic pronunciations
- Read example sentences
- Track your learning progress

### 3. الدردشة التفاعلية (Interactive Chat)
Navigate to the "الدردشة التفاعلية" tab to:
- Practice writing in English
- Get instant grammar corrections
- Receive error explanations in Arabic
- Improve your writing skills

## Troubleshooting

### Gradle Sync Failed
- Ensure you have a stable internet connection
- Try "File" → "Invalidate Caches / Restart"
- Check that you're using JDK 11 or later

### Build Errors
- Clean the project: "Build" → "Clean Project"
- Rebuild: "Build" → "Rebuild Project"
- Check that all dependencies are downloaded

### Emulator Issues
- Ensure you have enough RAM (minimum 4GB recommended)
- Try creating a new virtual device
- Update HAXM (Intel) or enable Hyper-V (Windows)

### Speech Recognition Not Working
- Ensure microphone permission is granted
- Check device microphone is working
- Try on a physical device if emulator has issues

## Customization

### Adding More Words
Edit `app/src/main/java/com/englishlearning/ui/visual/VisualLearningViewModel.kt` and add new `Word` objects to the list.

### Adding More Sentences
Edit `app/src/main/java/com/englishlearning/ui/speech/SpeechViewModel.kt` and add new sentences to the list.

### Changing Theme Colors
Edit `app/src/main/res/values/colors.xml` to customize the app colors.

### Updating Translations
Edit the following files:
- English: `app/src/main/res/values/strings.xml`
- Arabic: `app/src/main/res/values-ar/strings.xml`

## Building APK

### Debug APK
```bash
./gradlew assembleDebug
```
The APK will be in `app/build/outputs/apk/debug/`

### Release APK
```bash
./gradlew assembleRelease
```
Note: You'll need to configure signing for release builds.

## Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## Support

For issues or questions:
1. Check the [Issues](https://github.com/khalidnakhli/english-learning-app/issues) page
2. Create a new issue with detailed information
3. Include Android Studio version, device info, and error logs

## License

This project is created for educational purposes.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
