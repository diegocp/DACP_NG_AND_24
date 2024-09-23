package com.ngtv.viewmodels

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.ngtv.data.domain.Genre
import com.ngtv.data.domain.Movies
import com.ngtv.usecases.MovieHomeData
import com.ngtv.usecases.MovieUseCaseContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieViewModelTest {

  @Test
  fun `on launch movies are loaded um multiple sections`() = runTest {

    val movieUseCase = FakeMovieUseCase()

    moleculeFlow(mode = RecompositionMode.Immediate) {
      MoviePresenter(emptyFlow(), movieUseCase)
    }.distinctUntilChanged().test {
      assertEquals(
        expected = MovieUiState.Loading,
        actual = awaitItem(),
        message = "Should start with loading state"
      )
      cancel()
    }

  }

  @Test
  fun `on launch news screen should show display data`() = runTest {
    turbineScope {
      val useCase = FakeMovieUseCase()
      val events = MutableSharedFlow<MovieUiEvents>(extraBufferCapacity = 20)
      moleculeFlow(mode = RecompositionMode.Immediate) {
        MoviePresenter(
          events,
          useCase
        )
      }.distinctUntilChanged().test {
        events.emit(MovieUiEvents.LoadMovies)
        awaitItem()
        assertEquals(
          expected = MovieUiState.Success(MovieHomeData(
            Movies(emptyList()),
            Movies(emptyList()),
            Movies(emptyList()),
            Movies(emptyList())
          )),
          actual = awaitItem(),
          message = "Should display data if available"
        )
        cancel()
      }
    }
  }


}

class FakeMovieUseCase : MovieUseCaseContract {
  override suspend fun fetchMovies(): MovieHomeData = MovieHomeData(
    Movies(emptyList()), Movies(emptyList()), Movies(emptyList()), Movies(emptyList())
  )

  override suspend fun searchMovies(query: String) = Movies(emptyList())

  override suspend fun searchByGenre(idsGenre: String) = Movies(emptyList())

  override suspend fun fetchGenres(): List<Genre> = emptyList()

}
