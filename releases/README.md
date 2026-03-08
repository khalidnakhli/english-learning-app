# 📱 Releases / الإصدارات

هذا المجلد يحتوي على جميع إصدارات تطبيق تعلم الإنجليزية.

This directory contains all releases of the English Learning App.

---

## 📋 الإصدارات المتاحة / Available Releases

### [v1.0](v1.0/) - الإصدار الأول / First Release
**التاريخ / Date:** 8 مارس 2026 / March 8, 2026

**المميزات / Features:**
- 🎤 التعلم بالصوت / Speech Learning
- 🖼️ التعلم بالصور / Visual Learning
- 💬 الدردشة التفاعلية / Interactive Chat
- 🌍 واجهة عربية كاملة / Complete Arabic UI

**الملفات / Files:**
- [RELEASE_NOTES.md](v1.0/RELEASE_NOTES.md) - ملاحظات الإصدار
- [BUILD_PROCESS.md](v1.0/BUILD_PROCESS.md) - عملية البناء
- [HOW_TO_BUILD.md](v1.0/HOW_TO_BUILD.md) - دليل البناء السريع

---

## 🔨 بناء APK / Building APK

لبناء APK على جهازك المحلي:

```bash
# استنسخ المستودع
git clone https://github.com/khalidnakhli/english-learning-app.git
cd english-learning-app

# بناء APK
./gradlew assembleRelease

# أو استخدم السكريبت
./build_apk.sh release
```

**موقع APK / APK Location:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 📊 مواصفات التطبيق / App Specifications

```
اسم الحزمة / Package:     com.englishlearning
الإصدار / Version:        1.0
رقم البناء / Build:       1
الحد الأدنى / Min SDK:    API 30 (Android 11)
الهدف / Target SDK:       API 30
الحجم / Size:             ~5-8 MB
```

---

## 📚 التوثيق / Documentation

- [BUILD_APK_AR.md](../BUILD_APK_AR.md) - دليل البناء الكامل
- [BUILD_APK.md](../BUILD_APK.md) - Complete build guide
- [QUICK_BUILD_REFERENCE.md](../QUICK_BUILD_REFERENCE.md) - مرجع سريع
- [WHERE_IS_APK_AR.md](../WHERE_IS_APK_AR.md) - موقع APK

---

## ⚠️ ملاحظة مهمة / Important Note

**ملف APK الفعلي يجب بناؤه على جهاز محلي مع Android Studio.**

**The actual APK file must be built on a local machine with Android Studio.**

بسبب قيود بيئة الساندبوكس (لا يوجد Android SDK أو اتصال إنترنت)، تم توفير التوثيق الكامل والسكريبتات اللازمة للبناء على جهازك.

Due to sandbox environment limitations (no Android SDK or internet access), complete documentation and necessary scripts have been provided for building on your machine.

---

## 🎯 الإصدارات القادمة / Future Releases

للإصدارات المستقبلية:
- v1.1 - إضافة المزيد من الكلمات
- v1.2 - تحسينات في التعرف على الصوت
- v2.0 - ميزات جديدة

---

**آخر تحديث / Last Update:** 8 مارس 2026
