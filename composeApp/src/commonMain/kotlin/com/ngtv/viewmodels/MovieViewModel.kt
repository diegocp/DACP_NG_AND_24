package com.ngtv.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.ngtv.usecases.MovieHomeData
import com.ngtv.usecases.MovieUseCaseContract
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class MovieUiState {
  data object Loading : MovieUiState()
  data class Error(val message: String) : MovieUiState()
  data class Success(val movies: MovieHomeData) : MovieUiState()
}

sealed class MovieUiEvents {
  data object LoadMovies : MovieUiEvents()
}

open class MovieViewModel : ViewModel(), KoinComponent {
  private val movieUseCase: MovieUseCaseContract by inject()
  private val events = MutableSharedFlow<MovieUiEvents>(extraBufferCapacity = 20)
  val viewState: StateFlow<MovieUiState> = viewModelScope.coroutineScope.launchMolecule(
    mode = RecompositionMode.Immediate
  ) {
    MoviePresenter(events, movieUseCase)
  }

  fun loadMovies() {
    events.tryEmit(MovieUiEvents.LoadMovies)
  }

}

@Composable
fun MoviePresenter(events: Flow<MovieUiEvents>, movieUseCase: MovieUseCaseContract): MovieUiState {
  var uiState by remember { mutableStateOf<MovieUiState>(MovieUiState.Loading) }

  LaunchedEffect(Unit) {
    events.collect { event ->
      when (event) {
        is MovieUiEvents.LoadMovies -> {
          try {
            val movies = movieUseCase.fetchMovies()
            uiState = MovieUiState.Success(movies)
          } catch (e: Exception) {
            e.printStackTrace()
            uiState = MovieUiState.Error(e.message ?: "Unknown Error")
          }
        }
      }
    }
  }
  return uiState
}


