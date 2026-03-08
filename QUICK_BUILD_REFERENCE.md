# 📱 مرجع سريع - بناء APK

## أوامر البناء السريع

### 🔨 بناء APK للتطوير (Debug)
```bash
./build_apk.sh debug
```
**أو:**
```bash
./gradlew assembleDebug
```

**موقع الملف:**
```
app/build/outputs/apk/debug/app-debug.apk
```

---

### 🔨 بناء APK للإنتاج (Release)
```bash
./build_apk.sh release
```
**أو:**
```bash
./gradlew assembleRelease
```

**موقع الملف:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 📦 معلومات التطبيق

| المعلومة | القيمة |
|---------|--------|
| اسم الحزمة | com.englishlearning |
| رقم الإصدار | 1 |
| اسم الإصدار | 1.0 |
| الحد الأدنى | Android 11 (API 30) |
| الهدف | API 30 |

---

## 🚀 تثبيت APK على الجهاز

### عبر USB:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### عبر الملف مباشرة:
1. انسخ ملف APK إلى جهازك
2. افتح الملف من مدير الملفات
3. اضغط "تثبيت"

---

## 🔧 أوامر إضافية

```bash
# تنظيف المشروع
./gradlew clean

# بناء جميع الأنواع
./gradlew assemble

# تثبيت على جهاز متصل
./gradlew installDebug

# عرض جميع المهام
./gradlew tasks
```

---

## 📚 للمزيد من المعلومات

- [دليل كامل بالعربية](BUILD_APK_AR.md)
- [Complete English Guide](BUILD_APK.md)

---

**تاريخ:** 8 مارس 2026  
**الحالة:** ✅ جاهز للبناء
