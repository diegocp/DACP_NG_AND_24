package com.ngtv.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.Movie
import com.ngtv.ui.IMAGE_BASE_URL

@Composable
fun BannerList(
  popular: List<Movie>,
  onItemClicked: (String, String?) -> Unit
) {

  LazyRow(
    contentPadding = PaddingValues(horizontal = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp)
  ) {
    items(popular) { item ->
      val image = "${IMAGE_BASE_URL}${item.backdropPath}"

      OutlinedCard(
        onClick = {
          onItemClicked.invoke(item.id.toString(), item.mediaType ?: "movie")
        },
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
      ) {
        Box(Modifier.width(200.dp).aspectRatio(3 / 4f)) {
          AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
          )
          Text(
            text = item.title ?: item.originalTitle ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier
              .align(alignment = Alignment.BottomStart)
              .background(Color.Black.copy(alpha = 0.6f))
              .padding(8.dp)
              .fillMaxWidth()
          )
        }
      }
    }
  }
}