package com.ngtv.ui.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.MovieDetails
import com.ngtv.ui.IMAGE_BASE_URL
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.photos
import org.jetbrains.compose.resources.stringResource


@Composable
fun PhotoSectionDetail(movieDetail: MovieDetails) {
  Column(Modifier.padding(vertical = 16.dp)) {
    Text(
      text = stringResource(Res.string.photos),
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(Modifier.height(16.dp))
    val baseurl = IMAGE_BASE_URL
    val photos = listOf(
      baseurl + movieDetail.posterPath,
      baseurl + movieDetail.backdropPath,
    )
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
      items(photos) { photo ->
        Card {
          AsyncImage(
            model = photo,
            modifier = Modifier.border(
              width = Dp.Hairline,
              color = MaterialTheme.colorScheme.onSurface,
              shape = MaterialTheme.shapes.medium
            ).clip(MaterialTheme.shapes.medium).width(144.dp).aspectRatio(3 / 4f),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(Res.string.photos)
          )
        }
      }
    }
  }
}