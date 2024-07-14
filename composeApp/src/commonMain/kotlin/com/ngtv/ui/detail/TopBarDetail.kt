package com.ngtv.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBarDetail(
  modifier: Modifier = Modifier,
  onBackPress: () -> Unit
) {
  Box {
    IconButton(
      modifier = Modifier.align(alignment = Alignment.TopStart)
        .padding(8.dp)
        .graphicsLayer {
          clip = true
          shape = CircleShape
        }
        .background(Color.White.copy(alpha = 0.4f)),
      onClick = { onBackPress.invoke() }
    ) {
      Icon(
        modifier = Modifier,
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = stringResource(Res.string.back)
      )
    }
  }
}
