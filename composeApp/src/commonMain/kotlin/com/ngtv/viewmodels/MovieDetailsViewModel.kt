package com.ngtv.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.ngtv.data.MovieRepositoryContract
import com.ngtv.data.domain.MovieDetails
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class MovieDetailsUiState {
  data object Loading : MovieDetailsUiState()
  data class Error(val message: String) : MovieDetailsUiState()
  data class Success(val movieDetail: MovieDetails) : MovieDetailsUiState()
}

sealed class MovieDetailsUiEvents {
  data class LoadMovie(val id: String) : MovieDetailsUiEvents()
  data class LoadTvShow(val id: String) : MovieDetailsUiEvents()
  data class ShareMovie(val movie: MovieDetails) : MovieDetailsUiEvents()
}

open class MovieDetailsViewModel : ViewModel(), KoinComponent {
  private val movieRepository: MovieRepositoryContract by inject()
  private val events = MutableSharedFlow<MovieDetailsUiEvents>(extraBufferCapacity = 20)
  val viewState: StateFlow<MovieDetailsUiState> = viewModelScope.coroutineScope.launchMolecule(
    mode = RecompositionMode.Immediate
  ) {
    MovieDetailsPresenter(events)
  }

  fun load(id: String, type: String?) {
    when (type){
      "movie" -> events.tryEmit(MovieDetailsUiEvents.LoadMovie(id))
      "tv" -> events.tryEmit(MovieDetailsUiEvents.LoadTvShow(id))
    }
  }

  @Composable
  fun MovieDetailsPresenter(events: Flow<MovieDetailsUiEvents>): MovieDetailsUiState {
    var uiState by remember { mutableStateOf<MovieDetailsUiState>(MovieDetailsUiState.Loading) }

    LaunchedEffect(Unit) {
      events.collect { event ->
        when (event) {
          is MovieDetailsUiEvents.LoadMovie -> {
            try {
              val movies = movieRepository.fetchMovieDetails(event.id)
              uiState = MovieDetailsUiState.Success(movies)
            } catch (e: Exception) {
              e.printStackTrace()
              uiState = MovieDetailsUiState.Error(e.message ?: "Unknown Error")
            }
          }
          is MovieDetailsUiEvents.LoadTvShow -> {
            try {
              val movies = movieRepository.fetchTvShowDetails(event.id)
              uiState = MovieDetailsUiState.Success(movies)
            } catch (e: Exception) {
              e.printStackTrace()
              uiState = MovieDetailsUiState.Error(e.message ?: "Unknown Error")
            }
          }

          is MovieDetailsUiEvents.ShareMovie -> {

          }

        }
      }
    }
    return uiState
  }

}

