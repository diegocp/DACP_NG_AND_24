package com.ngtv.usecases

import com.ngtv.data.MovieRepositoryContract
import com.ngtv.data.domain.Genre
import com.ngtv.data.domain.Movies

interface MovieUseCaseContract {
  suspend fun fetchMovies() : MovieHomeData
  suspend fun searchMovies(query: String) : Movies
  suspend fun searchByGenre(idsGenre: String) : Movies
  suspend fun fetchGenres(): List<Genre>
}


class MovieUseCase(
  private val repository: MovieRepositoryContract
) : MovieUseCaseContract {

  override suspend fun fetchMovies() : MovieHomeData {
    val topRated = repository.fetchTopRated()
    val popular = repository.fetchPopular()
    val upcoming = repository.fetchUpcoming()
    val latestTv = repository.fetchLatestTv()
    return MovieHomeData(
      topRated = topRated,
      popular = popular,
      upcoming = upcoming,
      latestTv = latestTv
    )
  }

  override suspend fun searchMovies(query: String): Movies =
    Movies(results =
    repository.searchByMovie(query)
      .results.filter { it.posterPath != null || it.title != null }
    )

  override suspend fun searchByGenre(idsGenre: String): Movies = repository.searchByGenre(idsGenre)

  override suspend fun fetchGenres(): List<Genre> {
    repository.fetchGenreMovie().let { movieGenres ->
      repository.fetchGenreTv().let { tvGenres ->
        return (movieGenres + tvGenres).toSet().toList()
      }
    }
  }
}


data class MovieHomeData(
  val topRated: Movies,
  val popular: Movies,
  val upcoming: Movies,
  val latestTv: Movies
)