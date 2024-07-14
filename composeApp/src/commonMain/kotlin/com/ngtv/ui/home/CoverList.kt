package com.ngtv.ui.home

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.Movie
import com.ngtv.ui.IMAGE_BASE_URL

@Composable
fun CoverList(
  modifier: Modifier = Modifier,
  covers: List<Movie>,
  defaultMediaType: String = "movie",
  onItemClicked: (String, String?) -> Unit
) {

  val lazyListState = rememberLazyListState()
  LazyRow(
    modifier = modifier,
    state = lazyListState,
    contentPadding = PaddingValues(horizontal = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    flingBehavior = rememberSnapFlingBehavior(lazyListState),
  ) {

    items(covers) { item ->
      OutlinedCard(
        onClick = { onItemClicked(item.id.toString(), item.mediaType ?: defaultMediaType) },
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
      ) {
        Box(Modifier.width(140.dp).aspectRatio(9 / 16f)) {
          AsyncImage(
            model = "${IMAGE_BASE_URL}${item.posterPath}",
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
          )
        }
      }
    }
  }
}


