package com.ngtv.data.remote

import NGtv.composeApp.BuildConfig
import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import dev.shreyaspatil.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.flow.Flow

interface GeminiApiContract {
  fun generateContent(prompt: String): Flow<GenerateContentResponse>
}

class GeminiApi : GeminiApiContract {
  private val apiKey = BuildConfig.GEMINI_API_KEY

  private val generativeModel = GenerativeModel(
    modelName = "gemini-pro",
    apiKey = apiKey
  )

  override fun generateContent(prompt: String): Flow<GenerateContentResponse> {
    return generativeModel.generateContentStream(prompt)
  }
}