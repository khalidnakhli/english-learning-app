# 📱 English Learning App - Release v1.0

## معلومات الإصدار / Release Information

**الإصدار / Version:** 1.0  
**رقم البناء / Build Number:** 1  
**التاريخ / Date:** 8 مارس 2026 / March 8, 2026  
**حجم APK المتوقع / Expected APK Size:** ~5-8 MB  

---

## 📦 ملف APK / APK File

**ملاحظة هامة:** في بيئة الساندبوكس الحالية، لا يمكن بناء APK فعلي بسبب عدم توفر Android SDK والاتصال بالإنترنت.

**Important Note:** In the current sandbox environment, actual APK cannot be built due to unavailable Android SDK and internet connection.

---

## 🔨 كيفية بناء APK / How to Build APK

لبناء APK على جهازك المحلي مع Android Studio:

### الخطوات / Steps:

#### 1. فتح المشروع / Open Project
```bash
# استنسخ المستودع
git clone https://github.com/khalidnakhli/english-learning-app.git
cd english-learning-app

# افتح في Android Studio
# File → Open → Select project folder
```

#### 2. مزامنة Gradle / Sync Gradle
- انتظر حتى تكتمل المزامنة التلقائية
- أو: File → Sync Project with Gradle Files

#### 3. بناء APK / Build APK

**طريقة 1: Android Studio GUI**
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

**طريقة 2: سطر الأوامر / Command Line**
```bash
# للتطوير (Debug)
./gradlew assembleDebug

# للإنتاج (Release)
./gradlew assembleRelease
```

**طريقة 3: استخدام السكريبت / Using Script**
```bash
# Linux/Mac
./build_apk.sh release

# Windows
build_apk.bat release
```

---

## 📍 موقع ملف APK / APK Location

بعد البناء، ستجد ملف APK في:

### Debug APK:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Release APK (غير موقّع / Unsigned):
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 🔐 توقيع APK للنشر / Signing APK for Release

لنشر التطبيق في Google Play أو توزيعه، يجب توقيع APK:

### 1. إنشاء Keystore
```bash
keytool -genkey -v -keystore english-learning-app.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias english-learning-key
```

### 2. تعديل build.gradle
أضف في `app/build.gradle`:
```groovy
android {
    signingConfigs {
        release {
            storeFile file("../english-learning-app.jks")
            storePassword "your_password"
            keyAlias "english-learning-key"
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

### 3. بناء APK موقّع
```bash
./gradlew assembleRelease
```

---

## 📊 مواصفات التطبيق / App Specifications

```
اسم الحزمة / Package Name:     com.englishlearning
اسم التطبيق / App Name:         تطبيق تعلم الإنجليزية / English Learning App
الإصدار / Version:              1.0 (Build 1)
الحد الأدنى / Min SDK:          API 30 (Android 11)
الهدف / Target SDK:             API 30 (Android 11)
اللغة / Language:               Kotlin
المعمارية / Architecture:      MVVM
التصميم / UI:                   Material Design 3 with RTL
الحجم التقريبي / Est. Size:    5-8 MB (debug), 3-5 MB (release)
```

---

## ✨ المميزات / Features

1. **التعلم بالصوت** - Speech Learning with TTS & Recognition
2. **التعلم بالصور** - Visual Learning with 10 words
3. **الدردشة التفاعلية** - Interactive Chat with grammar checking
4. **واجهة عربية كاملة** - Complete Arabic UI with RTL support

---

## 📱 التثبيت / Installation

### عبر USB (ADB):
```bash
adb install app-release.apk
```

### عبر الملف مباشرة:
1. انسخ ملف APK إلى جهاز Android
2. افتح الملف من مدير الملفات
3. اسمح بالتثبيت من مصادر غير معروفة
4. اضغط "تثبيت"

---

## 🐛 الاختبار / Testing

قبل النشر، تأكد من اختبار:
- [ ] التعلم بالصوت على جهاز حقيقي
- [ ] التعرف على الصوت (يتطلب إذن الميكروفون)
- [ ] التعلم بالصور
- [ ] الدردشة التفاعلية
- [ ] التنقل بين الشاشات
- [ ] واجهة RTL للعربية

---

## 📚 المراجع / References

- [BUILD_APK_AR.md](../../BUILD_APK_AR.md) - دليل البناء الكامل
- [QUICK_BUILD_REFERENCE.md](../../QUICK_BUILD_REFERENCE.md) - مرجع سريع
- [WHERE_IS_APK_AR.md](../../WHERE_IS_APK_AR.md) - موقع APK

---

## 🔄 التحديثات المستقبلية / Future Updates

للتحديثات المستقبلية:
1. زيادة `versionCode` في build.gradle
2. تحديث `versionName`
3. بناء APK جديد
4. توقيع بنفس المفتاح
5. رفع إلى متجر التطبيقات

---

## 📞 الدعم / Support

للمشاكل أو الأسئلة:
- افتح Issue في المستودع
- راجع ملفات التوثيق

---

**آخر تحديث / Last Updated:** 8 مارس 2026  
**الحالة / Status:** ✅ جاهز للبناء على جهازك المحلي / Ready to build locally

🎉 **التطبيق جاهز للبناء والتوزيع!**
