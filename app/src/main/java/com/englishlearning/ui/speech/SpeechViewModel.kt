package com.englishlearning.ui.speech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.englishlearning.data.model.SpeechResult
import com.englishlearning.utils.GrammarChecker

class SpeechViewModel : ViewModel() {

    private val sentences = listOf(
        "Hello, how are you?",
        "I am learning English.",
        "What is your name?",
        "The weather is nice today.",
        "I like to read books.",
        "Can you help me please?",
        "Thank you very much.",
        "Good morning everyone.",
        "Have a great day!",
        "See you later."
    )

    private val _currentSentence = MutableLiveData<String>()
    val currentSentence: LiveData<String> = _currentSentence

    private val _speechResult = MutableLiveData<SpeechResult>()
    val speechResult: LiveData<SpeechResult> = _speechResult

    init {
        loadNewSentence()
    }

    fun loadNewSentence() {
        _currentSentence.value = sentences.random()
    }

    fun evaluateSpeech(recognizedText: String) {
        val originalText = _currentSentence.value ?: return
        val accuracy = GrammarChecker.calculateSimilarity(originalText, recognizedText)

        val feedback = when {
            accuracy >= 0.9f -> "ممتاز! نطقك صحيح جداً"
            accuracy >= 0.7f -> "جيد! حاول مرة أخرى لتحسين النطق"
            accuracy >= 0.5f -> "يحتاج إلى تحسين. استمع جيداً وحاول مرة أخرى"
            else -> "حاول الاستماع بتركيز أكبر وتكرار الجملة"
        }

        _speechResult.value = SpeechResult(
            originalText = originalText,
            recognizedText = recognizedText,
            accuracy = accuracy,
            feedback = feedback
        )
    }
}
