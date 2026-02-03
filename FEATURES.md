# Features Documentation

## Overview

English Learning App is a comprehensive Android application designed to help Arabic speakers learn English through three interactive methods:

1. **Speech Learning** - Practice pronunciation with instant feedback
2. **Visual Learning** - Learn vocabulary with images and examples
3. **Interactive Chat** - Practice writing with automatic grammar correction

## Feature Details

### 1. 🎤 Speech Learning (التعلم بالصوت)

#### Purpose
Help users improve their English pronunciation through listening and speaking exercises.

#### How It Works

**Listening:**
- Tap "استمع للنطق الصحيح" (Listen to Pronunciation)
- The app uses Android's Text-to-Speech to pronounce the sentence
- Listen carefully to the native pronunciation

**Speaking:**
- Tap "سجل صوتك" (Record Your Speech)
- Grant microphone permission if prompted
- Repeat the sentence shown on screen
- The app uses Speech Recognition to transcribe your speech

**Evaluation:**
- The app compares your speech with the original sentence
- Uses Levenshtein distance algorithm for similarity calculation
- Displays accuracy percentage
- Provides feedback in Arabic:
  - 90%+ accuracy: "ممتاز! نطقك صحيح جداً" (Excellent!)
  - 70-89%: "جيد! حاول مرة أخرى لتحسين النطق" (Good!)
  - 50-69%: "يحتاج إلى تحسين" (Needs improvement)
  - Below 50%: "حاول الاستماع بتركيز أكبر" (Listen more carefully)

#### Technical Implementation

**Components:**
- `SpeechFragment.kt`: UI and user interaction
- `SpeechViewModel.kt`: Business logic and sentence management
- Android TextToSpeech API
- Android SpeechRecognizer API

**Sentences Included:**
- "Hello, how are you?"
- "I am learning English."
- "What is your name?"
- "The weather is nice today."
- "I like to read books."
- And more...

### 2. 🖼️ Visual Learning (التعلم بالصور)

#### Purpose
Build vocabulary through visual association and contextual examples.

#### How It Works

**Word Display:**
- Each word is shown with:
  - English word in large text
  - Arabic translation (الترجمة العربية)
  - Phonetic pronunciation (e.g., /ˈæp.əl/)
  - Example sentence showing usage
  - Placeholder for word image

**Progress Tracking:**
- Shows "الكلمات المتعلمة: X / Y" (Words Learned: X / Y)
- Progress bar visualization
- Mark words as learned by tapping "تحديد كمتعلم"

**Navigation:**
- "الكلمة التالية" (Next Word) - Move to next word
- Words cycle through the entire vocabulary list

#### Technical Implementation

**Components:**
- `VisualLearningFragment.kt`: UI and user interaction
- `VisualLearningViewModel.kt`: Word management and progress tracking

**Sample Vocabulary:**
- Apple (تفاحة) - /ˈæp.əl/
- Book (كتاب) - /bʊk/
- Car (سيارة) - /kɑːr/
- Dog (كلب) - /dɒɡ/
- House (منزل) - /haʊs/
- Water (ماء) - /ˈwɔː.tər/
- Sun (شمس) - /sʌn/
- Moon (قمر) - /muːn/
- Friend (صديق) - /frend/
- School (مدرسة) - /skuːl/

### 3. 💬 Interactive Chat (الدردشة التفاعلية)

#### Purpose
Practice English writing skills with instant grammar feedback and corrections.

#### How It Works

**Chat Interface:**
- Two-sided chat layout
- User messages on the right (blue)
- Bot responses on the left (white)
- Scroll through conversation history

**Grammar Checking:**
The bot analyzes each message for:

1. **Capitalization Errors**
   - First letter of sentence should be uppercase
   - Pronoun "I" should always be capitalized
   - Example: "i am happy" → "I am happy"

2. **Punctuation Errors**
   - Sentences should end with proper punctuation (. ! ?)
   - Example: "Hello there" → "Hello there."

3. **Contraction Errors**
   - Corrects missing apostrophes in contractions
   - Example: "dont" → "don't", "cant" → "can't"
   - Supports 30+ common contractions

4. **Error Feedback**
   - Each error is explained in Arabic
   - Shows original vs corrected version
   - Displays the complete corrected sentence

**Responses:**
- If errors found: Detailed explanation with corrections
- If no errors: Encouraging messages like:
  - "ممتاز! جملتك صحيحة تماماً"
  - "رائع! لا توجد أخطاء في جملتك"
  - "أحسنت! كتابتك صحيحة"

#### Technical Implementation

**Components:**
- `ChatFragment.kt`: UI and chat interface
- `ChatViewModel.kt`: Message management and bot logic
- `ChatAdapter.kt`: RecyclerView adapter for messages
- `GrammarChecker.kt`: Grammar analysis utility

**Grammar Checker Features:**
```kotlin
// Supported contractions
don't, can't, won't, isn't, aren't, wasn't, weren't
haven't, hasn't, hadn't, wouldn't, shouldn't, couldn't
didn't, doesn't, I'm, you're, he's, she's, it's
we're, they're, I've, you've, we've, they've
I'll, you'll, he'll, she'll, we'll, they'll
```

### 4. 🔍 Grammar Checker Utility (محلل الأخطاء اللغوية)

#### Core Algorithm: Levenshtein Distance

Used for calculating similarity between spoken and expected text:

```
Similarity = (longer_length - edit_distance) / longer_length
```

**Edit Distance Calculation:**
- Insertion: Cost of 1
- Deletion: Cost of 1
- Substitution: Cost of 1
- Matches: Cost of 0

#### Error Detection System

**Error Types:**
1. `CAPITALIZATION`: Uppercase/lowercase issues
2. `PUNCTUATION`: Missing or incorrect punctuation
3. `CONTRACTION`: Incorrect apostrophe usage
4. `SPELLING`: Misspelled words (basic detection)
5. `GRAMMAR`: General grammar issues

**Error Structure:**
```kotlin
GrammarError(
    errorType: ErrorType,
    position: Int,           // Position in text
    original: String,        // Incorrect version
    correction: String,      // Correct version
    explanation: String      // Arabic explanation
)
```

## UI/UX Features

### Arabic Interface (RTL Support)
- All labels and messages in Arabic
- Right-to-left layout support
- Proper Arabic text rendering
- Cultural considerations in design

### Material Design
- Material Components library
- Card-based layouts
- Bottom navigation
- Proper elevation and shadows
- Consistent color scheme

### Navigation
- Bottom Navigation View with 3 tabs
- Fragment-based architecture
- Smooth transitions between screens
- Navigation Component integration

### Responsive Design
- ScrollView for content that may overflow
- Adaptive layouts for different screen sizes
- Proper padding and margins
- Touch-friendly button sizes

## Data Models

### Word
```kotlin
data class Word(
    val id: Int,
    val englishWord: String,
    val arabicTranslation: String,
    val phonetic: String,
    val exampleSentence: String,
    val imageUrl: String?,
    var isLearned: Boolean
)
```

### ChatMessage
```kotlin
data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long,
    val corrections: List<String>?
)
```

### SpeechResult
```kotlin
data class SpeechResult(
    val originalText: String,
    val recognizedText: String,
    val accuracy: Float,
    val feedback: String
)
```

### GrammarCorrection
```kotlin
data class GrammarCorrection(
    val originalText: String,
    val correctedText: String,
    val errors: List<GrammarError>
)
```

## Permissions

### Required Permissions

1. **RECORD_AUDIO**
   - Used for: Speech Recognition in Speech Learning
   - Runtime permission: Yes
   - Required for: Recording user's voice

2. **INTERNET**
   - Used for: Potential future online features
   - Runtime permission: No
   - Currently: Not actively used, reserved for expansion

## Architecture

### MVVM Pattern

**Model:**
- Data classes in `data/model/`
- Business logic in ViewModels

**View:**
- Fragments for each screen
- XML layouts
- UI event handling

**ViewModel:**
- LiveData for reactive updates
- Business logic separation
- Lifecycle-aware components

### Key Benefits:
- Separation of concerns
- Testability
- Lifecycle awareness
- Data persistence across configuration changes

## Future Enhancements

Potential features for future versions:

1. **Database Integration**
   - Room database for persistent storage
   - Save user progress
   - Custom word lists

2. **Advanced Grammar**
   - More complex grammar rules
   - Tense checking
   - Subject-verb agreement

3. **Speech Improvements**
   - Pronunciation scoring by phoneme
   - Detailed feedback on specific sounds
   - Speech waveform visualization

4. **Gamification**
   - Points and achievements
   - Daily challenges
   - Leaderboards

5. **Content Expansion**
   - More vocabulary categories
   - Themed word sets
   - Difficulty levels

6. **Offline TTS**
   - Download voice models
   - Better offline experience

7. **Writing Practice**
   - Essay checker
   - Vocabulary suggestions
   - Writing prompts

## Performance Considerations

### Memory Management
- ViewModels retained across configuration changes
- Proper lifecycle handling
- Efficient RecyclerView usage with DiffUtil

### Battery Optimization
- Speech recognition stopped when not in use
- TTS engine properly shutdown
- No background services

### Network Usage
- Currently minimal (no API calls)
- All processing done locally
- Fast and responsive

## Testing Recommendations

### Unit Tests
- GrammarChecker logic
- ViewModel business logic
- Data model validation

### UI Tests
- Fragment navigation
- Button interactions
- Text input and display

### Integration Tests
- Speech recognition flow
- Chat conversation flow
- Progress tracking

### Manual Testing
- Different Android versions
- Various device sizes
- RTL layout verification
- Permission handling
- Audio functionality
