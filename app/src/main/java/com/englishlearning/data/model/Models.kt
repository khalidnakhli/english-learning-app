package com.englishlearning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for chat messages
 */
data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val corrections: List<String>? = null
)

/**
 * Data class for words in visual learning
 */
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val englishWord: String,
    val arabicTranslation: String,
    val phonetic: String,
    val exampleSentence: String,
    val imageUrl: String? = null,
    var isLearned: Boolean = false
)

/**
 * Data class for speech recognition result
 */
data class SpeechResult(
    val originalText: String,
    val recognizedText: String,
    val accuracy: Float,
    val feedback: String
)

/**
 * Data class for grammar correction
 */
data class GrammarCorrection(
    val originalText: String,
    val correctedText: String,
    val errors: List<GrammarError>
)

/**
 * Data class for individual grammar errors
 */
data class GrammarError(
    val errorType: ErrorType,
    val position: Int,
    val original: String,
    val correction: String,
    val explanation: String
)

/**
 * Enum for error types
 */
enum class ErrorType {
    CAPITALIZATION,
    PUNCTUATION,
    CONTRACTION,
    SPELLING,
    GRAMMAR
}
