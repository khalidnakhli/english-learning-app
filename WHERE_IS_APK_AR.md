# 📍 أين يوجد ملف APK؟

## موقع ملفات APK بعد البناء

### ✅ APK للتطوير والاختبار (Debug)
```
app/build/outputs/apk/debug/app-debug.apk
```

### ✅ APK للإنتاج والنشر (Release)
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## ⚠️ ملاحظة هامة

**ملف APK لن يظهر إلا بعد بناء المشروع!**

إذا لم تجد المجلد `app/build/outputs/apk/`، فهذا يعني أنك لم تقم ببناء APK بعد.

---

## 🔨 كيفية بناء APK (خطوة واحدة)

### الطريقة الأسرع:

```bash
./build_apk.sh debug
```

أو:

```bash
./gradlew assembleDebug
```

**بعد تنفيذ الأمر، ستجد الملف في:**
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📂 البحث عن الملف

### في نظام Linux/Mac:
```bash
find . -name "*.apk"
```

### في نظام Windows:
```cmd
dir /s *.apk
```

---

## 🎯 خطوات سريعة

1. **افتح Terminal** في مجلد المشروع
2. **نفذ الأمر:**
   ```bash
   ./gradlew assembleDebug
   ```
3. **انتظر** حتى ينتهي البناء (قد يستغرق دقائق قليلة)
4. **ابحث عن الملف** في:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📋 التحقق من وجود الملف

```bash
ls -lh app/build/outputs/apk/debug/app-debug.apk
```

إذا ظهر الملف، فهو جاهز للتثبيت! ✅

---

## 📱 تثبيت APK على جهازك

بعد العثور على الملف:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

أو انسخه إلى جهاز Android وافتحه من مدير الملفات.

---

## 📚 مراجع إضافية

- **[دليل البناء الكامل](BUILD_APK_AR.md)** - شرح مفصل
- **[مرجع سريع](QUICK_BUILD_REFERENCE.md)** - أوامر سريعة
- **[ملخص شامل](APK_BUILD_SUMMARY_AR.md)** - معلومات كاملة

---

**تاريخ:** 8 مارس 2026  
**آخر تحديث:** 8 مارس 2026  

✅ **ملف APK سيكون في: app/build/outputs/apk/debug/app-debug.apk**
