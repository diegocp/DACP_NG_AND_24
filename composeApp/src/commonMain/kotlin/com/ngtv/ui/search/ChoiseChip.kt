package com.ngtv.ui.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ChoiceChip(
  text: String,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  CompositionLocalProvider(value = LocalMinimumInteractiveComponentSize provides 48.dp) {
    Surface(
      color = when {
        selected -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceContainer
      },
      contentColor = when {
        selected -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant
      },
      shape = MaterialTheme.shapes.medium,
      modifier = modifier,
      onClick = onClick,
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
          horizontal = when {
            selected -> 8.dp
            else -> 16.dp
          },
          vertical = 8.dp
        )
      ) {
        if (selected) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "",
            modifier = Modifier
              .height(18.dp)
              .padding(end = 8.dp)
          )
        }
        Text(
          text = text,
          style = MaterialTheme.typography.bodyMedium,
        )
      }
    }
  }
}