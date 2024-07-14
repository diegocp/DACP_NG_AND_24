package com.ngtv.ui.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Headline(
  modifier: Modifier = Modifier,
  title: String,
  composableContent: @Composable () -> Unit
) {
  Column {
    Text(
      modifier = modifier.padding(16.dp),
      text = title,
      fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
      style = MaterialTheme.typography.headlineMedium
    )

    composableContent.invoke()
  }
}