package com.englishlearning.ui.visual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.englishlearning.data.model.Word

class VisualLearningViewModel : ViewModel() {

    private val words = mutableListOf(
        Word(
            englishWord = "Apple",
            arabicTranslation = "تفاحة",
            phonetic = "/ˈæp.əl/",
            exampleSentence = "I eat an apple every day."
        ),
        Word(
            englishWord = "Book",
            arabicTranslation = "كتاب",
            phonetic = "/bʊk/",
            exampleSentence = "I am reading a good book."
        ),
        Word(
            englishWord = "Car",
            arabicTranslation = "سيارة",
            phonetic = "/kɑːr/",
            exampleSentence = "My car is red."
        ),
        Word(
            englishWord = "Dog",
            arabicTranslation = "كلب",
            phonetic = "/dɒɡ/",
            exampleSentence = "The dog is very friendly."
        ),
        Word(
            englishWord = "House",
            arabicTranslation = "منزل",
            phonetic = "/haʊs/",
            exampleSentence = "I live in a big house."
        ),
        Word(
            englishWord = "Water",
            arabicTranslation = "ماء",
            phonetic = "/ˈwɔː.tər/",
            exampleSentence = "Drink water every day."
        ),
        Word(
            englishWord = "Sun",
            arabicTranslation = "شمس",
            phonetic = "/sʌn/",
            exampleSentence = "The sun is bright today."
        ),
        Word(
            englishWord = "Moon",
            arabicTranslation = "قمر",
            phonetic = "/muːn/",
            exampleSentence = "I can see the moon at night."
        ),
        Word(
            englishWord = "Friend",
            arabicTranslation = "صديق",
            phonetic = "/frend/",
            exampleSentence = "She is my best friend."
        ),
        Word(
            englishWord = "School",
            arabicTranslation = "مدرسة",
            phonetic = "/skuːl/",
            exampleSentence = "Children go to school to learn."
        )
    )

    private var currentIndex = 0

    private val _currentWord = MutableLiveData<Word>()
    val currentWord: LiveData<Word> = _currentWord

    private val _progress = MutableLiveData<Pair<Int, Int>>()
    val progress: LiveData<Pair<Int, Int>> = _progress

    init {
        loadCurrentWord()
        updateProgress()
    }

    private fun loadCurrentWord() {
        if (words.isNotEmpty()) {
            _currentWord.value = words[currentIndex]
        }
    }

    fun loadNextWord() {
        currentIndex = (currentIndex + 1) % words.size
        loadCurrentWord()
    }

    fun markCurrentWordAsLearned() {
        words[currentIndex].isLearned = true
        loadCurrentWord()
        updateProgress()
    }

    private fun updateProgress() {
        val learnedCount = words.count { it.isLearned }
        _progress.value = Pair(learnedCount, words.size)
    }
}
