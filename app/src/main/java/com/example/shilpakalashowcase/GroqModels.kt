package com.example.shilpakalashowcase

import com.google.gson.annotations.SerializedName

data class GroqMessage(
    val role: String,
    val content: String
)

data class GroqRequest(
    val model: String = "openai/gpt-oss-20b",
    val messages: List<GroqMessage>,
    @SerializedName("max_tokens")
    val maxTokens: Int = 1024,
    @SerializedName("reasoning_effort")
    val reasoningEffort: String = "low"
)
data class GroqChoice(
    val message: GroqMessage
)

data class GroqResponse(
    val choices: List<GroqChoice>
)
