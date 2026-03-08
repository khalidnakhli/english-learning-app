#!/bin/bash

# Script to build APK for English Learning App
# الاستخدام: ./build_apk.sh [debug|release]

set -e

echo "═══════════════════════════════════════════════════════════════"
echo "    بناء APK - تطبيق تعلم الإنجليزية"
echo "    Building APK - English Learning App"
echo "═══════════════════════════════════════════════════════════════"
echo ""

# Check if Gradle wrapper exists
if [ ! -f "./gradlew" ]; then
    echo "❌ Error: gradlew not found!"
    echo "❌ خطأ: ملف gradlew غير موجود!"
    exit 1
fi

# Make gradlew executable
chmod +x ./gradlew

# Determine build type
BUILD_TYPE=${1:-debug}

echo "📱 Build type / نوع البناء: $BUILD_TYPE"
echo ""

# Clean previous builds
echo "🧹 Cleaning previous builds..."
echo "🧹 تنظيف البناءات السابقة..."
./gradlew clean --no-daemon

# Build APK
if [ "$BUILD_TYPE" = "release" ]; then
    echo ""
    echo "🔨 Building Release APK..."
    echo "🔨 بناء APK للإنتاج..."
    ./gradlew assembleRelease --no-daemon
    
    echo ""
    echo "✅ Release APK built successfully!"
    echo "✅ تم بناء APK للإنتاج بنجاح!"
    echo ""
    echo "📦 APK Location / موقع الملف:"
    echo "   app/build/outputs/apk/release/app-release-unsigned.apk"
    echo ""
    echo "⚠️  Note: This APK needs to be signed before publishing"
    echo "⚠️  ملاحظة: يحتاج هذا الملف إلى التوقيع قبل النشر"
    
elif [ "$BUILD_TYPE" = "debug" ]; then
    echo ""
    echo "🔨 Building Debug APK..."
    echo "🔨 بناء APK للتطوير..."
    ./gradlew assembleDebug --no-daemon
    
    echo ""
    echo "✅ Debug APK built successfully!"
    echo "✅ تم بناء APK للتطوير بنجاح!"
    echo ""
    echo "📦 APK Location / موقع الملف:"
    echo "   app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "📱 You can install this APK directly on your device"
    echo "📱 يمكنك تثبيت هذا الملف مباشرة على جهازك"
    
else
    echo "❌ Invalid build type: $BUILD_TYPE"
    echo "❌ نوع بناء غير صحيح: $BUILD_TYPE"
    echo ""
    echo "Usage / الاستخدام:"
    echo "  ./build_apk.sh debug    # For testing / للتطوير"
    echo "  ./build_apk.sh release  # For production / للإنتاج"
    exit 1
fi

echo ""
echo "═══════════════════════════════════════════════════════════════"
echo "✅ Build Complete! / البناء مكتمل!"
echo "═══════════════════════════════════════════════════════════════"
