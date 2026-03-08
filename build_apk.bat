@echo off
REM Script to build APK for English Learning App on Windows
REM الاستخدام: build_apk.bat [debug|release]

echo ===============================================================
echo     بناء APK - تطبيق تعلم الإنجليزية
echo     Building APK - English Learning App
echo ===============================================================
echo.

REM Check if Gradle wrapper exists
if not exist "gradlew.bat" (
    echo ❌ Error: gradlew.bat not found!
    echo ❌ خطأ: ملف gradlew.bat غير موجود!
    exit /b 1
)

REM Determine build type
set BUILD_TYPE=%1
if "%BUILD_TYPE%"=="" set BUILD_TYPE=debug

echo 📱 Build type / نوع البناء: %BUILD_TYPE%
echo.

REM Clean previous builds
echo 🧹 Cleaning previous builds...
echo 🧹 تنظيف البناءات السابقة...
call gradlew.bat clean --no-daemon

REM Build APK
if "%BUILD_TYPE%"=="release" (
    echo.
    echo 🔨 Building Release APK...
    echo 🔨 بناء APK للإنتاج...
    call gradlew.bat assembleRelease --no-daemon
    
    echo.
    echo ✅ Release APK built successfully!
    echo ✅ تم بناء APK للإنتاج بنجاح!
    echo.
    echo 📦 APK Location / موقع الملف:
    echo    app\build\outputs\apk\release\app-release-unsigned.apk
    echo.
    echo ⚠️  Note: This APK needs to be signed before publishing
    echo ⚠️  ملاحظة: يحتاج هذا الملف إلى التوقيع قبل النشر
    
) else if "%BUILD_TYPE%"=="debug" (
    echo.
    echo 🔨 Building Debug APK...
    echo 🔨 بناء APK للتطوير...
    call gradlew.bat assembleDebug --no-daemon
    
    echo.
    echo ✅ Debug APK built successfully!
    echo ✅ تم بناء APK للتطوير بنجاح!
    echo.
    echo 📦 APK Location / موقع الملف:
    echo    app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 📱 You can install this APK directly on your device
    echo 📱 يمكنك تثبيت هذا الملف مباشرة على جهازك
    
) else (
    echo ❌ Invalid build type: %BUILD_TYPE%
    echo ❌ نوع بناء غير صحيح: %BUILD_TYPE%
    echo.
    echo Usage / الاستخدام:
    echo   build_apk.bat debug    # For testing / للتطوير
    echo   build_apk.bat release  # For production / للإنتاج
    exit /b 1
)

echo.
echo ===============================================================
echo ✅ Build Complete! / البناء مكتمل!
echo ===============================================================
