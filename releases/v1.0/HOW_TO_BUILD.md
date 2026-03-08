# تعليمات بناء APK على جهازك المحلي

## 🎯 الهدف

بناء ملف APK نهائي لتطبيق تعلم الإنجليزية Android 11

---

## 📋 المتطلبات

- Android Studio Arctic Fox أو أحدث
- JDK 11+
- Android SDK API 30
- اتصال بالإنترنت (للمرة الأولى)

---

## 🚀 خطوات سريعة

### 1. استنسخ المستودع

```bash
git clone https://github.com/khalidnakhli/english-learning-app.git
cd english-learning-app
```

### 2. افتح في Android Studio

```
File → Open → اختر مجلد المشروع
```

انتظر مزامنة Gradle (قد تستغرق دقائق قليلة)

### 3. بناء APK

**أسهل طريقة:**
```bash
./build_apk.sh release
```

**أو باستخدام Gradle مباشرة:**
```bash
./gradlew assembleRelease
```

**أو من Android Studio:**
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

### 4. العثور على APK

```bash
# APK سيكون في:
ls -lh app/build/outputs/apk/release/app-release-unsigned.apk
```

### 5. نسخ APK إلى مجلد الإصدار

```bash
cp app/build/outputs/apk/release/app-release-unsigned.apk releases/v1.0/app-release.apk
```

### 6. إضافة إلى المستودع

```bash
git add releases/v1.0/app-release.apk
git commit -m "Add release v1.0 APK"
git push
```

---

## 🔐 توقيع APK (للنشر)

إذا أردت نشر التطبيق في Google Play:

### إنشاء Keystore

```bash
keytool -genkey -v -keystore english-learning-app.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias english-learning-key
```

### تعديل app/build.gradle

أضف:
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
            // ... rest of config
        }
    }
}
```

### بناء APK موقّع

```bash
./gradlew assembleRelease
```

الآن APK موقّع وجاهز للنشر!

---

## 📱 اختبار APK

```bash
# تثبيت على جهاز متصل
adb install releases/v1.0/app-release.apk

# أو انسخ الملف إلى جهازك وثبّته يدوياً
```

---

## ✅ التحقق

تأكد من اختبار:
- [ ] التطبيق يفتح بدون أخطاء
- [ ] التعلم بالصوت يعمل
- [ ] التعرف على الصوت يعمل (مع إذن الميكروفون)
- [ ] التعلم بالصور يعرض الكلمات
- [ ] الدردشة التفاعلية تصحح الأخطاء
- [ ] واجهة RTL العربية تعمل بشكل صحيح

---

## 📊 المعلومات النهائية

```
Package: com.englishlearning
Version: 1.0 (Build 1)
Min SDK: API 30 (Android 11)
Target SDK: API 30
Size: ~5-8 MB (debug), ~3-5 MB (release)
```

---

🎉 **APK جاهز للتوزيع!**
