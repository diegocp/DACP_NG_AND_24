package com.ngtv.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.error_msg
import org.jetbrains.compose.resources.stringResource

@Composable
fun Error(modifier: Modifier = Modifier) {
  Text(
    stringResource(Res.string.error_msg),
    modifier = modifier,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.error
  )
}