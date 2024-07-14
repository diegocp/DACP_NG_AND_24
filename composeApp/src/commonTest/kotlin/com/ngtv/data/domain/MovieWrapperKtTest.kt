package com.ngtv.data.domain

import com.ngtv.data.remote.Dates
import com.ngtv.data.remote.MovieResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieResponseTest {

  @Test
  fun `mapToDomain maps non-empty list correctly`() {
    // Given
    val mockMovie = generateMockMovieResponse()

    // When
    val movies = mockMovie.mapToDomain()

    // Then
    assertEquals(1, movies.results.size)
    with(movies.results.first()) {
      assertEquals(false, adult )
      assertEquals("/pathToBackdrop.jpg", backdropPath )
      assertEquals(listOf(1L, 2L), genreIds )
      assertEquals(123L, id )
      assertEquals("en", originalLanguage )
      assertEquals("Mock Original Title", originalTitle )
      assertEquals("This is a mock overview of the movie.", overview )
      assertEquals(8.5, popularity )
      assertEquals("/pathToPoster.jpg", posterPath )
      assertEquals("movie", mediaType )
      assertEquals("2023-12-25", releaseDate )
      assertEquals("Mock Movie Title", title )
      assertEquals(false, video )
      assertEquals(7.8, voteAverage )
      assertEquals(200, voteCount )
    }
  }


  @Test
  fun `mapToDomain maps empty list correctly`() {
    // Given
    val movieResponse = MovieResponse(
      dates =Dates(maximum = "2023-12-31", minimum = "2023-01-01"),
      page = 1,
      totalPages = 0,
      totalResults = 0,
      results = emptyList()
    )

    // When
    val movies = movieResponse.mapToDomain()

    // Then
    assertEquals(0, movies.results.size)
  }


  private fun generateMockMovieResponse(): MovieResponse {
    return MovieResponse(
      dates = Dates(maximum = "2023-12-31", minimum = "2023-01-01"),
      page = 1L,
      results = listOf(
        com.ngtv.data.remote.Movie(
          adult = false,
          backdropPath = "/pathToBackdrop.jpg",
          genreIds = listOf(1L, 2L),
          id = 123L,
          originalLanguage = "en",
          originalTitle = "Mock Original Title",
          overview = "This is a mock overview of the movie.",
          popularity = 8.5,
          posterPath = "/pathToPoster.jpg",
          mediaType = "movie",
          releaseDate = "2023-12-25",
          title = "Mock Movie Title",
          video = false,
          voteAverage = 7.8,
          voteCount = 200L
        )
      ),
      totalPages = 10L,
      totalResults = 200L
    )
  }
}