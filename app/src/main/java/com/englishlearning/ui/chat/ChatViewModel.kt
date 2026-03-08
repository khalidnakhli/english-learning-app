package com.englishlearning.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.englishlearning.data.model.ChatMessage
import com.englishlearning.utils.GrammarChecker

class ChatViewModel : ViewModel() {

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> = _messages

    private val messageList = mutableListOf<ChatMessage>()

    init {
        // Add initial bot message
        addBotMessage("مرحباً! أنا هنا لمساعدتك في تعلم اللغة الإنجليزية. ابدأ بكتابة جملة باللغة الإنجليزية.")
    }

    fun sendMessage(text: String) {
        // Add user message
        val userMessage = ChatMessage(
            text = text,
            isUser = true
        )
        messageList.add(userMessage)
        _messages.value = messageList.toList()

        // Check grammar and respond
        val grammarResult = GrammarChecker.checkGrammar(text)
        
        if (grammarResult.errors.isNotEmpty()) {
            // Has errors - provide corrections
            val corrections = grammarResult.errors.map { error ->
                "${error.explanation}\n" +
                "الخطأ: ${error.original}\n" +
                "الصواب: ${error.correction}"
            }
            
            val botResponse = "لقد وجدت بعض الأخطاء:\n\n" +
                corrections.joinToString("\n\n") +
                "\n\nالجملة الصحيحة:\n${grammarResult.correctedText}"
            
            addBotMessage(botResponse, corrections)
        } else {
            // No errors - encourage
            val responses = listOf(
                "ممتاز! جملتك صحيحة تماماً. 👍",
                "رائع! لا توجد أخطاء في جملتك. استمر! 🎉",
                "أحسنت! كتابتك صحيحة. 💯",
                "جيد جداً! جملة صحيحة. واصل التقدم! ⭐"
            )
            addBotMessage(responses.random())
        }
    }

    private fun addBotMessage(text: String, corrections: List<String>? = null) {
        val botMessage = ChatMessage(
            text = text,
            isUser = false,
            corrections = corrections
        )
        messageList.add(botMessage)
        _messages.value = messageList.toList()
    }
}
