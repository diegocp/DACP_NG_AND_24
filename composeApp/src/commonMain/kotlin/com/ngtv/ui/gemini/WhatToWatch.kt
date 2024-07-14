package com.ngtv.ui.gemini

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.m3.Markdown
import com.ngtv.data.remote.GeminiApiContract
import dev.shreyaspatil.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.back
import ngtv.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun WhatToWatch(
  onBackPressed: () -> Unit
) {
  val api = koinInject<GeminiApiContract>()
  val coroutineScope = rememberCoroutineScope()
  var prompt by remember { mutableStateOf("") }
  var content by remember { mutableStateOf("") }
  var showProgress by remember { mutableStateOf(false) }
  val canClearPrompt by remember {
    derivedStateOf {
      prompt.isNotBlank()
    }
  }
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .fillMaxWidth().padding(16.dp)
  ) {
    FlowRow {
      Row(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        IconButton(
          modifier = Modifier
            .padding(8.dp)
            .graphicsLayer {
              clip = true
              shape = CircleShape
            },
          onClick = { onBackPressed.invoke() }
        ) {
          Icon(
            modifier = Modifier,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(Res.string.back)
          )
        }

        OutlinedTextField(
          shape = RoundedCornerShape(32.dp),
          value = prompt,
          onValueChange = { prompt = it },
          modifier = Modifier
            .padding(top = 8.dp)
            .defaultMinSize(minHeight = 52.dp),
          label = {
            Text(stringResource(Res.string.search))
          },
          trailingIcon = {
            if (canClearPrompt) {
              IconButton(
                onClick = { prompt = "" }
              ) {
                Icon(
                  imageVector = Icons.Default.Clear,
                  contentDescription = "Clear"
                )
              }
            }
          }
        )
      }

      Button(
        onClick = {
          if (prompt.isNotBlank()) {
            coroutineScope.launch {
              println("prompt = $prompt")
              val promptCompleted = """
CONTEXT: 
You are a movie and tv show expert, a professional critic, or a travel enthusiast. You are recommending the best movies and tv shows to watch. 

GOAL:
I want to return mininum of 5 movie or/and tv show that matches with my input preferences. 
Also list 3 musics from the soundtrack of the movie or tv show

RESPONSE FORMAT:
- movie or tv show title
- small overview
- 3 musics from the soundtrack

MY AUDIENCE:
an avid movie goer who is looking for a new movie to watch or a tv show to binge watch
                                    $prompt
                                """.trimIndent()
              content = ""
              generateContentAsFlow(api, promptCompleted)
                .onStart { showProgress = true }
                .onCompletion { showProgress = false }
                .collect {
                  println("response = ${it.text}")
                  content += it.text
                }
            }
          }
        },
        enabled = prompt.isNotBlank(),
        modifier = Modifier
          .padding(all = 4.dp)
          .weight(1f)
          .align(Alignment.CenterVertically)
      ) {
        Text("Discover your next binge")
      }

    }

    Spacer(Modifier.height(16.dp))
    if (showProgress) {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        CircularProgressIndicator()
      }
    } else {
      Markdown(content)
    }
  }
}

fun generateContentAsFlow(
  api: GeminiApiContract,
  prompt: String
): Flow<GenerateContentResponse> =
  api.generateContent(prompt)

