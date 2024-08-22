package com.ngtv.ui.detail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.MovieDetails
import com.ngtv.ui.IMAGE_BASE_URL
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.people_like
import org.jetbrains.compose.resources.stringResource


@Composable
fun PeopleAlsoLikeSection(
  movieDetail: MovieDetails,
  onDetailMovieClick: (String) -> Unit
) {
  val listState = rememberLazyListState()

  Column(Modifier.padding(vertical = 16.dp)) {
    Text(
      text = stringResource(Res.string.people_like),
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(horizontal = 16.dp)
    )
    val recommendations = movieDetail.recommendations?.results ?: emptyList()
    Spacer(Modifier.height(16.dp))
    LazyRow(
      state = listState,
      userScrollEnabled = false,
      modifier = Modifier
        .scrollable(
          orientation = Orientation.Horizontal,
          reverseDirection = true,
          state = listState,
        ),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
      items(recommendations) { item ->
        Card(onClick = { onDetailMovieClick.invoke(item.id.toString()) }) {
          Column(Modifier.width(160.dp)) {
            AsyncImage(
              model = "$IMAGE_BASE_URL${item.posterPath}",
              modifier = Modifier.aspectRatio(16 / 9f),
              contentScale = ContentScale.Crop,
              contentDescription = "${item.title}"
            )
            Column(
              modifier = Modifier.padding(8.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Text(text = item.title ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis)
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(item.voteAverage.toString())
                Icon(
                  imageVector = Icons.Filled.Star,
                  contentDescription = "Rating",
                  tint = fillColor
                )
              }
            }
          }
        }
      }
    }
  }
}