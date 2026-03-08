package com.englishlearning.utils

import com.englishlearning.data.model.ErrorType
import com.englishlearning.data.model.GrammarCorrection
import com.englishlearning.data.model.GrammarError

/**
 * Utility class for checking and correcting grammar errors
 */
class GrammarChecker {

    companion object {
        private val contractions = mapOf(
            "dont" to "don't",
            "cant" to "can't",
            "wont" to "won't",
            "isnt" to "isn't",
            "arent" to "aren't",
            "wasnt" to "wasn't",
            "werent" to "weren't",
            "havent" to "haven't",
            "hasnt" to "hasn't",
            "hadnt" to "hadn't",
            "wouldnt" to "wouldn't",
            "shouldnt" to "shouldn't",
            "couldnt" to "couldn't",
            "didnt" to "didn't",
            "doesnt" to "doesn't",
            "im" to "I'm",
            "youre" to "you're",
            "hes" to "he's",
            "shes" to "she's",
            "its" to "it's",
            "were" to "we're",
            "theyre" to "they're",
            "ive" to "I've",
            "youve" to "you've",
            "weve" to "we've",
            "theyve" to "they've",
            "ill" to "I'll",
            "youll" to "you'll",
            "hell" to "he'll",
            "shell" to "she'll",
            "well" to "we'll",
            "theyll" to "they'll"
        )

        /**
         * Check text for grammar errors and return corrections
         */
        fun checkGrammar(text: String): GrammarCorrection {
            val errors = mutableListOf<GrammarError>()
            var correctedText = text

            // Check for capitalization at the beginning of sentences
            if (text.isNotEmpty() && text[0].isLowerCase()) {
                errors.add(
                    GrammarError(
                        errorType = ErrorType.CAPITALIZATION,
                        position = 0,
                        original = text[0].toString(),
                        correction = text[0].uppercaseChar().toString(),
                        explanation = "يجب أن تبدأ الجملة بحرف كبير"
                    )
                )
                correctedText = correctedText.replaceFirst(text[0], text[0].uppercaseChar())
            }

            // Check for missing punctuation at the end
            if (text.isNotEmpty() && !text.last().let { it == '.' || it == '!' || it == '?' }) {
                errors.add(
                    GrammarError(
                        errorType = ErrorType.PUNCTUATION,
                        position = text.length,
                        original = "",
                        correction = ".",
                        explanation = "يجب أن تنتهي الجملة بعلامة ترقيم"
                    )
                )
                correctedText += "."
            }

            // Check for contractions
            val words = correctedText.split(" ")
            val correctedWords = words.map { word ->
                val lowerWord = word.lowercase()
                if (contractions.containsKey(lowerWord)) {
                    val correction = contractions[lowerWord]!!
                    errors.add(
                        GrammarError(
                            errorType = ErrorType.CONTRACTION,
                            position = correctedText.indexOf(word),
                            original = word,
                            correction = correction,
                            explanation = "الاختصار الصحيح هو: $correction"
                        )
                    )
                    correction
                } else {
                    word
                }
            }
            correctedText = correctedWords.joinToString(" ")

            // Check for "I" capitalization
            correctedText = correctedText.replace(Regex("\\bi\\b"), "I")
            if (text.contains(Regex("\\bi\\b"))) {
                errors.add(
                    GrammarError(
                        errorType = ErrorType.CAPITALIZATION,
                        position = text.indexOf(" i "),
                        original = "i",
                        correction = "I",
                        explanation = "الضمير 'I' يجب أن يكون دائماً بحرف كبير"
                    )
                )
            }

            return GrammarCorrection(
                originalText = text,
                correctedText = correctedText,
                errors = errors
            )
        }

        /**
         * Calculate similarity between two strings (for speech recognition accuracy)
         */
        fun calculateSimilarity(original: String, recognized: String): Float {
            val s1 = original.lowercase().trim()
            val s2 = recognized.lowercase().trim()

            if (s1 == s2) return 1.0f
            if (s1.isEmpty() || s2.isEmpty()) return 0.0f

            val longer = if (s1.length > s2.length) s1 else s2
            val shorter = if (s1.length > s2.length) s2 else s1

            val editDistance = levenshteinDistance(longer, shorter)
            return ((longer.length - editDistance).toFloat() / longer.length.toFloat())
        }

        /**
         * Calculate Levenshtein distance between two strings
         */
        private fun levenshteinDistance(s1: String, s2: String): Int {
            val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

            for (i in 0..s1.length) {
                dp[i][0] = i
            }

            for (j in 0..s2.length) {
                dp[0][j] = j
            }

            for (i in 1..s1.length) {
                for (j in 1..s2.length) {
                    val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                    dp[i][j] = minOf(
                        dp[i - 1][j] + 1,      // deletion
                        dp[i][j - 1] + 1,      // insertion
                        dp[i - 1][j - 1] + cost // substitution
                    )
                }
            }

            return dp[s1.length][s2.length]
        }
    }
}
