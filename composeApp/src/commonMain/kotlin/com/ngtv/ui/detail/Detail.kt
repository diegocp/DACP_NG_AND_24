package com.ngtv.ui.detail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ngtv.data.domain.MovieDetails
import com.ngtv.ui.util.Error
import com.ngtv.ui.util.Loading
import com.ngtv.viewmodels.MovieDetailsUiState.Error
import com.ngtv.viewmodels.MovieDetailsUiState.Loading
import com.ngtv.viewmodels.MovieDetailsUiState.Success
import com.ngtv.viewmodels.MovieDetailsViewModel
import org.koin.compose.koinInject


@Composable
fun Detail(
  id: String,
  type: String?,
  onBackPress: () -> Unit
) {
  val movieViewModel = koinInject<MovieDetailsViewModel>()
  val movieUiState by movieViewModel.viewState.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) {
    movieViewModel.load(id, type)
  }

  when (movieUiState) {
    is Loading -> Loading()
    is Error -> Error()
    is Success -> {
      val movieDetail: MovieDetails = (movieUiState as Success).movieDetail
      MovieDetailLayout(
        movieDetail = movieDetail,
        onBackPress = onBackPress,
        onDetailMovieClick = { idMovie ->
          movieViewModel.load(idMovie, type ?: "movie")
        }
      )
    }
  }
}

val fillColor = Color(0xFFFFC107)
val emptyColor = Color(0xFFEEEEEE)

@Composable
fun MovieDetailLayout(
  movieDetail: MovieDetails,
  onBackPress: () -> Unit,
  onDetailMovieClick: (String) -> Unit
) {
  Scaffold(
    containerColor = MaterialTheme.colorScheme.surface,
    topBar = {
      TopBarDetail(onBackPress = onBackPress)
    }

  ) {
    val listState = rememberLazyListState(
      initialFirstVisibleItemIndex = 0,
      initialFirstVisibleItemScrollOffset = 0
    )

    LaunchedEffect(movieDetail) {
      listState.scrollToItem(0)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
      LazyColumn(
        state = listState,
        userScrollEnabled = false,
        modifier = Modifier
          .scrollable(
            orientation = Orientation.Vertical,
            reverseDirection = true,
            state = listState,
          )
      ) {
        item { HeaderSection(movieDetail) }
        item { HorizontalDivider() }
        item { MoreInfoDetail(movieDetail) }
        item { HorizontalDivider() }
        item { PhotoSectionDetail(movieDetail) }
        item { HorizontalDivider() }
        item { ReviewsSection(movieDetail) }
        item { HorizontalDivider() }
        item { PeopleAlsoLikeSection(movieDetail, onDetailMovieClick) }
        item { Spacer(Modifier.height(24.dp)) }
      }
    }
  }
}

