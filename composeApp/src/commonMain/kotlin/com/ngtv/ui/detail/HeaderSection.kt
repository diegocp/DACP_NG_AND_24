package com.ngtv.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.MovieDetails
import com.ngtv.ui.IMAGE_BASE_URL
import com.ngtv.ui.search.ChoiceChip
import com.ngtv.ui.search.emptyTabIndicator
import com.ngtv.ui.util.calculateRating

@Composable
fun HeaderSection(movieDetail: MovieDetails) {
  Column {
    Box {
      AsyncImage(
        model = "$IMAGE_BASE_URL${movieDetail.backdropPath}",
        modifier = Modifier.aspectRatio(16 / 9f).fillMaxWidth(),
        contentScale = ContentScale.Crop,
        contentDescription = "${movieDetail.title}"
      )

    }
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Text(
        text = movieDetail.title ?: movieDetail.originalTitle ?: "",
        style = MaterialTheme.typography.titleLarge,
      )
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        val maxRating = 5
        val ratingValue = movieDetail.voteAverage?.toFloat() ?: 0f
        val rating = ratingValue.calculateRating(maxRating)
        Text(rating.toString().take(4))
        RatingBar(rating = rating, maxRating = maxRating)
        Text("(${movieDetail.voteCount ?: 0})")
      }
      Text(
        text = movieDetail.overview ?: movieDetail.tagline ?: "",
        style = MaterialTheme.typography.bodyLarge,
      )

      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        ScrollableTabRow(
          selectedTabIndex = 0,
          containerColor = Color.Transparent,
          divider = {}, /* Disable the built-in divider */
          edgePadding = 0.dp,
          indicator = emptyTabIndicator,
          modifier = Modifier
        ) {
          movieDetail.genres?.forEach {
            ChoiceChip(
              text = "${it.name}",
              selected = false,
              modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
              onClick = { },
            )
          }
        }
      }
    }
  }
}