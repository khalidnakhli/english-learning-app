# English Learning App - Android 11

This is a comprehensive Android application for learning English with an Arabic user interface.

## Features

### 1. Speech Learning (التعلم بالصوت)
- Listen to correct pronunciation using Text-to-Speech
- Record your voice using Speech Recognition
- Instant pronunciation evaluation
- Feedback in Arabic

### 2. Visual Learning (التعلم بالصور)
- Words with illustrative images
- Arabic translations and phonetic pronunciation
- Example sentences for word usage
- Progress tracking

### 3. Interactive Chat (الدردشة التفاعلية)
- Interactive conversation to practice English writing
- Automatic spelling and grammar correction
- Error explanations in Arabic
- Writing improvement suggestions

### 4. Grammar Checker (محلل الأخطاء اللغوية)
- Common error detection
- Contraction corrections (don't, can't, etc.)
- Capitalization checks
- Punctuation verification

## Technical Details

- **Minimum SDK**: API 30 (Android 11)
- **Target SDK**: API 30
- **Language**: Kotlin
- **Architecture**: MVVM
- **UI**: Material Design with RTL support
- **Navigation**: Jetpack Navigation Component

## Project Structure

```
app/
├── src/main/
│   ├── java/com/englishlearning/
│   │   ├── MainActivity.kt
│   │   ├── ui/
│   │   │   ├── speech/
│   │   │   │   ├── SpeechFragment.kt
│   │   │   │   └── SpeechViewModel.kt
│   │   │   ├── chat/
│   │   │   │   ├── ChatFragment.kt
│   │   │   │   ├── ChatViewModel.kt
│   │   │   │   └── ChatAdapter.kt
│   │   │   └── visual/
│   │   │       ├── VisualLearningFragment.kt
│   │   │       └── VisualLearningViewModel.kt
│   │   ├── data/
│   │   │   └── model/
│   │   │       └── Models.kt
│   │   └── utils/
│   │       └── GrammarChecker.kt
│   └── res/
│       ├── layout/
│       ├── menu/
│       ├── navigation/
│       └── values/
└── build.gradle
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Add launcher icons to mipmap folders (ic_launcher.png and ic_launcher_round.png)
5. Run the app on an Android 11+ device or emulator

## Permissions Required

- **RECORD_AUDIO**: For speech recognition feature
- **INTERNET**: For potential future online features

## Note on Launcher Icons

The app currently references standard launcher icons (`ic_launcher` and `ic_launcher_round`) that need to be added to the following directories:
- `app/src/main/res/mipmap-mdpi/`
- `app/src/main/res/mipmap-hdpi/`
- `app/src/main/res/mipmap-xhdpi/`
- `app/src/main/res/mipmap-xxhdpi/`
- `app/src/main/res/mipmap-xxxhdpi/`

You can use Android Studio's Image Asset tool to generate these icons.