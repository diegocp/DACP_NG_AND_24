package com.ngtv.viewmodels

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.Turbine
import app.cash.turbine.test
import com.ngtv.data.domain.Genre
import com.ngtv.data.domain.Movies
import com.ngtv.usecases.MovieHomeData
import com.ngtv.usecases.MovieUseCaseContract
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

//import org.junit.Assert.assertEquals
//import org.junit.Test

class MovieViewModelTest {

  @Ignore //todo fix
  @Test
  fun `on launch movies are loaded um multiple sections`() = runTest {

    val movieUseCase = FakeMovieUseCase()

    moleculeFlow(mode = RecompositionMode.Immediate) {
      MoviePresenter(emptyFlow(), movieUseCase)
    }.distinctUntilChanged().test {
      assertEquals(
        expected = MovieUiState.Success(MovieHomeData(Movies(emptyList()),Movies(emptyList()), Movies(emptyList()), Movies(emptyList()))),
        actual = awaitItem(),
        message = "Should start with empty movies"
      )
    }

  }

}

class FakeMovieUseCase : MovieUseCaseContract {
  override suspend fun fetchMovies(): MovieHomeData = Turbine<MovieHomeData>().awaitItem()

  override suspend fun searchMovies(query: String) = Turbine<Movies>().awaitItem()

  override suspend fun searchByGenre(idsGenre: String) = Turbine<Movies>().awaitItem()

  override suspend fun fetchGenres(): List<Genre> = Turbine<List<Genre>>().awaitItem()

}
