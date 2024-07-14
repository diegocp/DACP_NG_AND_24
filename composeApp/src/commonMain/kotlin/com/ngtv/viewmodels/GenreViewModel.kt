package com.ngtv.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.ngtv.data.domain.Genre
import com.ngtv.usecases.MovieUseCaseContract
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class GenreUiState {
  data object Loading : GenreUiState()
  data class Success(val genres: List<Genre>) : GenreUiState()
  data class Error(val message: String) : GenreUiState()
}

sealed class GenreUiEvents {
  data object FetchGenres : GenreUiEvents()
}

open class GenreViewModel : ViewModel(), KoinComponent {
  private val movieUseCase: MovieUseCaseContract by inject()
  private val events = MutableSharedFlow<GenreUiEvents>(extraBufferCapacity = 20)
  val viewState: StateFlow<GenreUiState> = viewModelScope.coroutineScope.launchMolecule(
    mode = RecompositionMode.Immediate
  ) {
    GenrePresenter(events)
  }

  fun fetchGenres() {
    events.tryEmit(GenreUiEvents.FetchGenres)
  }

  @Composable
  fun GenrePresenter(events: Flow<GenreUiEvents>): GenreUiState {
    var uiState by remember { mutableStateOf<GenreUiState>(GenreUiState.Loading) }

    LaunchedEffect(Unit) {
      events.collect { event ->
        when (event) {
          GenreUiEvents.FetchGenres -> {
            try {
              val genres = movieUseCase.fetchGenres()
              uiState = GenreUiState.Success(genres)
            } catch (e: Exception) {
              e.printStackTrace()
              uiState = GenreUiState.Error(e.message ?: "Unknown Error")
            }
          }
        }
      }
    }
    return uiState
  }

}

