package com.ngtv.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.Movie
import com.ngtv.ui.IMAGE_BASE_URL

@Composable
fun GridItem(
  item: Movie,
  onclick: (String, String?) -> Unit
) {
  val title = item.title ?: item.name ?: item.originalName
  val image = "${IMAGE_BASE_URL}${item.posterPath}"
  val description = item.overview

  OutlinedCard(onClick = {
    onclick.invoke(item.id.toString(), item.mediaType ?: "movie")
  }) {
    Column {
      AsyncImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(16 / 9f)
          .background(MaterialTheme.colorScheme.surfaceVariant)
      )
      Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column {
          Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("$title", style = MaterialTheme.typography.titleLarge)
          }
          Spacer(Modifier.height(24.dp))
          Text(
            text = "$description",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 3,
            minLines = 3
          )
          Spacer(Modifier.height(12.dp))
        }
      }
    }
  }
}