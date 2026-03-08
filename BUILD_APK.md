# APK Build Guide - English Learning App

## 📱 How to Export the App to APK Format

This guide explains the steps to build an APK file from the Android project.

---

## 🔧 Prerequisites

Before starting, ensure you have:
- ✅ **Android Studio** (Arctic Fox or newer)
- ✅ **Java Development Kit (JDK)** 11 or newer
- ✅ **Android SDK** with API level 30
- ✅ Internet connection (for downloading dependencies on first build)

---

## 📋 Building APK Steps

### Method 1: Using Android Studio (Recommended)

#### 1️⃣ Open Project
```bash
# Open Android Studio
# File → Open
# Select folder: english-learning-app
```

#### 2️⃣ Sync Gradle
```
Wait for Gradle sync to complete automatically
Or go to: File → Sync Project with Gradle Files
```

#### 3️⃣ Build Debug APK
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

Or from the top menu:
```
Build → Generate Signed Bundle / APK → APK → Next
```

#### 4️⃣ APK Location
After build completes, a notification will show with "locate" link:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

### Method 2: Using Command Line (Gradle)

#### Build Debug APK

```bash
# Navigate to project folder
cd english-learning-app

# Build debug APK
./gradlew assembleDebug

# Or on Windows:
gradlew.bat assembleDebug
```

**File Location:**
```
app/build/outputs/apk/debug/app-debug.apk
```

#### Build Release APK

```bash
# Build release APK
./gradlew assembleRelease

# Or on Windows:
gradlew.bat assembleRelease
```

**File Location:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

⚠️ **Note:** Release APK needs to be signed before publishing.

---

## 🔐 Signing APK for Release (Optional)

To publish the app on Google Play Store, you need to sign the APK:

### 1. Create Keystore

```bash
keytool -genkey -v -keystore my-release-key.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias my-key-alias
```

### 2. Edit app/build.gradle

Add to `app/build.gradle`:

```groovy
android {
    ...
    signingConfigs {
        release {
            storeFile file("my-release-key.jks")
            storePassword "your_password"
            keyAlias "my-key-alias"
            keyPassword "your_password"
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

### 3. Build Signed Release APK

```bash
./gradlew assembleRelease
```

**File Location:**
```
app/build/outputs/apk/release/app-release.apk
```

---

## 📱 Installing APK on Device

### Via USB:

```bash
# Ensure USB Debugging is enabled on device
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Via APK File Directly:

1. Copy APK file to Android device
2. Open file from file manager
3. Allow installation from unknown sources (if prompted)
4. Tap "Install"

---

## 📊 Build Information

### Current App Settings:

```groovy
applicationId: com.englishlearning
versionCode: 1
versionName: 1.0
minSdk: 30 (Android 11)
targetSdk: 30
```

### Expected APK Size:
- **Debug APK**: ~5-8 MB
- **Release APK**: ~3-5 MB (without minification)

---

## 🐛 Troubleshooting Common Issues

### Issue: "SDK not found"
**Solution:**
```bash
# Set ANDROID_HOME
export ANDROID_HOME=/path/to/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### Issue: "Could not resolve dependencies"
**Solution:**
- Check your internet connection
- Clean project: `./gradlew clean`
- Re-sync Gradle

### Issue: "Unsupported class file version"
**Solution:**
- Ensure you're using JDK 11 or newer
- Check Java settings in Android Studio

---

## 📝 Additional Gradle Commands

```bash
# Clean project
./gradlew clean

# Build all APK variants
./gradlew assemble

# Show all available tasks
./gradlew tasks

# Build and install on connected device
./gradlew installDebug

# Uninstall from device
./gradlew uninstallDebug
```

---

## 📦 Building Android App Bundle (AAB)

For uploading to Google Play Store, AAB format is preferred:

```bash
./gradlew bundleRelease
```

**File Location:**
```
app/build/outputs/bundle/release/app-release.aab
```

---

## ✅ Pre-Release Checklist

- [ ] Test app on different devices
- [ ] Update versionCode and versionName
- [ ] Enable ProGuard for minification (minifyEnabled true)
- [ ] Sign APK with Keystore
- [ ] Test Release APK
- [ ] Create icons in all sizes
- [ ] Write app description
- [ ] Capture screenshots

---

## 📞 Support

If you encounter issues:
1. Review `SETUP.md` file
2. Check build logs
3. Ensure all prerequisites are installed

---

## 🎯 Quick Summary

**To get APK quickly:**

```bash
# 1. Open terminal in project folder
cd english-learning-app

# 2. Run command
./gradlew assembleDebug

# 3. File located at
app/build/outputs/apk/debug/app-debug.apk
```

**To build production APK:**

```bash
./gradlew assembleRelease
# File at: app/build/outputs/apk/release/app-release-unsigned.apk
```

---

**Created:** March 8, 2026  
**Version:** 1.0  
**App Status:** ✅ Ready to Build  

🎉 **The app is ready to export to APK!** 🎉
