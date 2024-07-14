package com.ngtv.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ngtv.data.domain.Genre
import com.ngtv.ui.home.GridItem
import com.ngtv.ui.util.Error
import com.ngtv.ui.util.Loading
import com.ngtv.viewmodels.GenreUiState
import com.ngtv.viewmodels.GenreViewModel
import com.ngtv.viewmodels.SearchUiState
import com.ngtv.viewmodels.SearchViewModel
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.back
import ngtv.composeapp.generated.resources.no_movie_found
import ngtv.composeapp.generated.resources.popular_interests
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun Search(
  modifier: Modifier = Modifier,
  onItemClicked: (String, String?) -> Unit,
  onBackPressed: () -> Unit
) {

  val movieViewModel = koinInject<SearchViewModel>()
  val movieUiState by movieViewModel.viewState.collectAsStateWithLifecycle()
  val genreViewModel = koinInject<GenreViewModel>()
  val genreUiState by genreViewModel.viewState.collectAsStateWithLifecycle()
  var genres by remember { mutableStateOf(emptyList<Genre>()) }

  LaunchedEffect(Unit) {
    genreViewModel.fetchGenres()
  }

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier

      ) {
        IconButton(
          modifier = Modifier.align(alignment = Alignment.CenterVertically),
          onClick = { onBackPressed.invoke() }
        ) {
          Icon(
            modifier = Modifier,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(Res.string.back)
          )
        }
        SearchBarLayout(
          onQueryChange = {},
          isSearchActive = false,
          onActiveChanged = {},
          modifier = modifier,
          onBackPressed = onBackPressed,
          onSearch = {
            movieViewModel.searchByMovie(it)
          }
        )

      }
    }
  ) {
    Row(
      modifier = Modifier.padding(it)
    ) {
      when (genreUiState) {
        is GenreUiState.Error -> Error()
        GenreUiState.Loading -> Loading()
        is GenreUiState.Success -> {
          genres = (genreUiState as GenreUiState.Success).genres
        }
      }

      when (movieUiState) {
        is SearchUiState.Loading -> {
          ChipsFilter(
            modifier,
            genres
          ) { item ->
            movieViewModel.searchByGenre(item)
          }
        }

        is SearchUiState.Error -> Error()

        is SearchUiState.Success -> {
          Column(modifier = Modifier) {
            LazyVerticalGrid(
              modifier = Modifier.fillMaxWidth(),
              columns = GridCells.Fixed(1),
              verticalArrangement = Arrangement.spacedBy(16.dp),
              horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {

              if (movieUiState is SearchUiState.Success) {
                val results = (movieUiState as SearchUiState.Success).movies.results

                item {
                  ChipsFilter(modifier, genres) { item ->
                    movieViewModel.searchByGenre(item)
                  }
                }
                items(results) { item ->
                  GridItem(item, onItemClicked)
                }
              }
            }
          }
        }

        SearchUiState.NoMovieFound -> {
          Box(modifier = Modifier.fillMaxSize()) {
            Text(
              modifier = Modifier.align(Alignment.Center),
              text = stringResource(Res.string.no_movie_found)
            )
          }
        }
      }

    }
  }
}

@Composable
fun ChipsFilter(
  modifier: Modifier,
  genres: List<Genre>,
  onItemClicked: (String) -> Unit
) {
  Column(modifier = modifier.fillMaxSize()) {
    Text(
      modifier = modifier.padding(start = 16.dp, end = 16.dp),
      text = stringResource(Res.string.popular_interests),
      fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
      style = MaterialTheme.typography.headlineMedium
    )
    FilterChip(
      genres = genres,
      onItemClicked = {
        onItemClicked(it)
      }
    )
  }
}


@Composable
fun FilterChip(
  modifier: Modifier = Modifier,
  genres: List<Genre>,
  onItemClicked: (String) -> Unit
) {
  ScrollableTabRow(
    selectedTabIndex = 0,
    containerColor = Color.Transparent,
    divider = {}, /* Disable the built-in divider */
    edgePadding = 16.dp,
    indicator = emptyTabIndicator,
    modifier = Modifier
  ) {
    genres.forEach {
      ChoiceChip(
        text = "${it.name}",
        selected = false,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
        onClick = {
          onItemClicked.invoke(it.id.toString())
        },
      )
    }
  }
}

val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}