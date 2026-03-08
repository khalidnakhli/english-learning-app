# دليل بناء ملف APK - تطبيق تعلم الإنجليزية

## 📱 كيفية تصدير التطبيق إلى صيغة APK

هذا الدليل يشرح خطوات بناء ملف APK من مشروع Android.

---

## 🔧 المتطلبات الأساسية

قبل البدء، تأكد من تثبيت:
- ✅ **Android Studio** (النسخة Arctic Fox أو أحدث)
- ✅ **Java Development Kit (JDK)** 11 أو أحدث
- ✅ **Android SDK** مع API level 30
- ✅ اتصال بالإنترنت (لتنزيل المكتبات في المرة الأولى)

---

## 📋 خطوات بناء APK

### الطريقة 1: باستخدام Android Studio (موصى به)

#### 1️⃣ فتح المشروع
```bash
# افتح Android Studio
# File → Open
# اختر مجلد: english-learning-app
```

#### 2️⃣ مزامنة Gradle
```
انتظر حتى تكتمل مزامنة Gradle تلقائياً
أو اذهب إلى: File → Sync Project with Gradle Files
```

#### 3️⃣ بناء APK للتطوير (Debug)
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

أو من القائمة العلوية:
```
Build → Generate Signed Bundle / APK → APK → Next
```

#### 4️⃣ موقع ملف APK
بعد اكتمال البناء، سيظهر إشعار يحتوي على رابط "locate":
```
app/build/outputs/apk/debug/app-debug.apk
```

---

### الطريقة 2: باستخدام سطر الأوامر (Gradle)

#### بناء APK للتطوير (Debug APK)

```bash
# انتقل إلى مجلد المشروع
cd english-learning-app

# بناء APK للتطوير
./gradlew assembleDebug

# أو في Windows:
gradlew.bat assembleDebug
```

**موقع الملف:**
```
app/build/outputs/apk/debug/app-debug.apk
```

#### بناء APK للإنتاج (Release APK)

```bash
# بناء APK للإنتاج
./gradlew assembleRelease

# أو في Windows:
gradlew.bat assembleRelease
```

**موقع الملف:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

⚠️ **ملاحظة:** ملف Release يحتاج إلى التوقيع الرقمي قبل النشر.

---

## 🔐 توقيع APK للإنتاج (اختياري)

لنشر التطبيق في متجر Google Play، تحتاج لتوقيع APK:

### 1. إنشاء Keystore

```bash
keytool -genkey -v -keystore my-release-key.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias my-key-alias
```

### 2. تعديل app/build.gradle

أضف في ملف `app/build.gradle`:

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

### 3. بناء Release APK موقّع

```bash
./gradlew assembleRelease
```

**موقع الملف:**
```
app/build/outputs/apk/release/app-release.apk
```

---

## 📱 تثبيت APK على الجهاز

### عبر USB:

```bash
# تأكد من تمكين USB Debugging على الجهاز
adb install app/build/outputs/apk/debug/app-debug.apk
```

### عبر ملف APK مباشرة:

1. انسخ ملف APK إلى جهاز Android
2. افتح الملف من مدير الملفات
3. اسمح بتثبيت التطبيقات من مصادر غير معروفة (إذا طُلب منك)
4. انقر "تثبيت"

---

## 📊 معلومات البناء

### إعدادات التطبيق الحالية:

```groovy
applicationId: com.englishlearning
versionCode: 1
versionName: 1.0
minSdk: 30 (Android 11)
targetSdk: 30
```

### حجم APK المتوقع:
- **Debug APK**: ~5-8 MB
- **Release APK**: ~3-5 MB (بدون تصغير)

---

## 🐛 حل المشكلات الشائعة

### مشكلة: "SDK not found"
**الحل:**
```bash
# عيّن ANDROID_HOME
export ANDROID_HOME=/path/to/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### مشكلة: "Could not resolve dependencies"
**الحل:**
- تأكد من اتصالك بالإنترنت
- نظف المشروع: `./gradlew clean`
- أعد مزامنة Gradle

### مشكلة: "Unsupported class file version"
**الحل:**
- تأكد من استخدام JDK 11 أو أحدث
- تحقق من إعدادات Java في Android Studio

---

## 📝 أوامر Gradle الإضافية

```bash
# تنظيف المشروع
./gradlew clean

# بناء جميع أنواع APK
./gradlew assemble

# عرض جميع المهام المتاحة
./gradlew tasks

# بناء وتثبيت على جهاز متصل
./gradlew installDebug

# إلغاء تثبيت من جهاز
./gradlew uninstallDebug
```

---

## 📦 بناء Android App Bundle (AAB)

لرفع التطبيق إلى Google Play Store، يُفضل استخدام AAB:

```bash
./gradlew bundleRelease
```

**موقع الملف:**
```
app/build/outputs/bundle/release/app-release.aab
```

---

## ✅ قائمة التحقق قبل النشر

- [ ] اختبار التطبيق على أجهزة مختلفة
- [ ] تحديث versionCode و versionName
- [ ] تفعيل ProGuard للتصغير (minifyEnabled true)
- [ ] توقيع APK بـ Keystore
- [ ] اختبار Release APK
- [ ] إنشاء أيقونات بجميع الأحجام
- [ ] كتابة وصف التطبيق
- [ ] التقاط لقطات الشاشة

---

## 📞 الدعم

إذا واجهت مشاكل:
1. راجع ملف `SETUP.md`
2. تحقق من سجلات البناء (Build logs)
3. تأكد من تثبيت جميع المتطلبات

---

## 🎯 ملخص سريع

**للحصول على APK بسرعة:**

```bash
# 1. افتح terminal في مجلد المشروع
cd english-learning-app

# 2. نفذ الأمر
./gradlew assembleDebug

# 3. الملف موجود في
app/build/outputs/apk/debug/app-debug.apk
```

**لبناء APK للإنتاج:**

```bash
./gradlew assembleRelease
# الملف في: app/build/outputs/apk/release/app-release-unsigned.apk
```

---

**تاريخ الإنشاء:** 8 مارس 2026  
**الإصدار:** 1.0  
**حالة التطبيق:** ✅ جاهز للبناء  

🎉 **التطبيق جاهز للتصدير إلى APK!** 🎉
