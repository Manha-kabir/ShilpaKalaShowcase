package com.example.shilpakalashowcase

import com.google.gson.annotations.SerializedName

data class GroqMessage(
    val role: String,
    val content: String
)

data class GroqRequest(
    val model: String = "llama-3.1-8b-instant",
    val messages: List<GroqMessage>,
    @SerializedName("max_tokens")
    val maxTokens: Int = 400
)

data class GroqChoice(
    val message: GroqMessage
)

data class GroqResponse(
    val choices: List<GroqChoice>
)
