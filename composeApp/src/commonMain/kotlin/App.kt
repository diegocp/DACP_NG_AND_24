
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ui.theme.AppTypography
import com.ngtv.data.domain.Movie
import com.ngtv.ui.detail.Detail
import com.ngtv.ui.gemini.WhatToWatch
import com.ngtv.ui.home.HomeScreen
import com.ngtv.ui.search.Search
import com.ngtv.ui.util.Error
import com.ngtv.ui.util.Loading
import com.ngtv.viewmodels.MovieUiState
import com.ngtv.viewmodels.MovieViewModel
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App(darkTheme: Boolean = isSystemInDarkTheme()) {
  val movieViewModel = koinInject<MovieViewModel>()
  val movieUiState by movieViewModel.viewState.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) {
    movieViewModel.loadMovies()
  }

  val colorScheme = when {
    darkTheme -> darkScheme
    else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = {
      when (movieUiState) {
        is MovieUiState.Error -> Error()
        MovieUiState.Loading -> Loading()
        is MovieUiState.Success -> {
          val data = (movieUiState as MovieUiState.Success).movies
          Navigation(
            data.topRated.results,
            data.popular.results,
            data.latestTv.results,
            data.upcoming.results
          )
        }
      }
    }
  )
}


@Composable
fun Navigation(
  topRated: List<Movie>,
  popular: List<Movie>,
  latestTv: List<Movie>,
  upcoming: List<Movie>
) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Screens.MovieList,
  ) {
    composable<Screens.MovieList> {
      HomeScreen(
        popular = popular,
        topRated = topRated,
        latestTv = latestTv,
        upcoming = upcoming,
        onSearchIconClicked = {
          navController.navigate(Screens.Search)
        },
        onItemClicked = { id, type ->
          navController.navigate(Screens.MovieDetail(id, type))
        },
        onWhatToWatch = {
          navController.navigate(Screens.WhatToWatch)
        }
      )
    }

    composable<Screens.MovieDetail> { navBackStackEntry ->
      val id = navBackStackEntry.toRoute<Screens.MovieDetail>().id
      val type = navBackStackEntry.toRoute<Screens.MovieDetail>().type
      Detail(id, type) {
        navController.popBackStack()
      }
    }

    composable<Screens.Search> {
      Search(
        onItemClicked = { id, type ->
          navController.navigate(Screens.MovieDetail(id, type))
        },
        onBackPressed = {
          navController.popBackStack()
        }
      )
    }

    composable<Screens.WhatToWatch>{
      WhatToWatch(
        onBackPressed = {
          navController.popBackStack()
        }
      )
    }
  }
}


sealed interface Screens {
  @Serializable
  data object MovieList : Screens

  @Serializable
  data class MovieDetail(val id: String, val type: String?) : Screens

  @Serializable
  data object Search : Screens

  @Serializable
  data object WhatToWatch : Screens
}

