package com.ngtv.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.ngtv.data.domain.Movies
import com.ngtv.usecases.MovieUseCaseContract
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class SearchUiState {
  data object NoMovieFound : SearchUiState()
  data object Loading : SearchUiState()
  data class Error(val message: String) : SearchUiState()
  data class Success(val movies: Movies) : SearchUiState()
}

sealed class SearchUiEvents {
  data class SearchByMovie(val query: String) : SearchUiEvents()
  data class SearchByGenre(val idGenre: String) : SearchUiEvents()
}

open class SearchViewModel : ViewModel(), KoinComponent {
  private val movieUseCase: MovieUseCaseContract by inject()
  private val events = MutableSharedFlow<SearchUiEvents>(extraBufferCapacity = 20)
  val viewState: StateFlow<SearchUiState> = viewModelScope.coroutineScope.launchMolecule(
    mode = RecompositionMode.Immediate
  ) {
    SearchPresenter(events)
  }

  fun searchByMovie(query: String) {
    events.tryEmit(SearchUiEvents.SearchByMovie(query))
  }
  
  fun searchByGenre(idGenre: String) {
    events.tryEmit(SearchUiEvents.SearchByGenre(idGenre))
  }
  
  @Composable
  fun SearchPresenter(events: Flow<SearchUiEvents>): SearchUiState {
    var uiState by remember { mutableStateOf<SearchUiState>(SearchUiState.Loading) }

    LaunchedEffect(Unit) {
      events.collect { event ->
        when (event) {
          is SearchUiEvents.SearchByMovie -> {
            try {
              val movies = movieUseCase.searchMovies(event.query)
              uiState = SearchUiState.Success(movies)
            } catch (e: Exception) {
              e.printStackTrace()
              uiState = SearchUiState.Error(e.message ?: "Unknown Error")
            }
          }

          is SearchUiEvents.SearchByGenre -> {
            try {
              val movies = movieUseCase.searchByGenre(event.idGenre)
              uiState = SearchUiState.Success(movies)
            } catch (e: Exception) {
              e.printStackTrace()
              uiState = SearchUiState.Error(e.message ?: "Unknown Error")
            }
          }
        }
      }
    }
    return uiState
  }

}

