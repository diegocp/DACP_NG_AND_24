package com.ngtv.data

import com.ngtv.data.domain.Genre
import com.ngtv.data.domain.MovieDetails
import com.ngtv.data.domain.Movies
import com.ngtv.data.domain.mapToDomain
import com.ngtv.data.remote.MovieApiContract

interface MovieRepositoryContract {
  suspend fun fetchTopRated(): Movies
  suspend fun fetchMovieDetails(id: String): MovieDetails
  suspend fun fetchTvShowDetails(id: String): MovieDetails
  suspend fun fetchPopular(): Movies
  suspend fun fetchUpcoming(): Movies
  suspend fun fetchLatestTv(): Movies
  suspend fun fetchGenreMovie(): List<Genre>
  suspend fun fetchGenreTv(): List<Genre>
  suspend fun searchByMovie(query: String): Movies
  suspend fun searchByGenre(idsGenre: String): Movies
}

class MovieRepository(
  private val api: MovieApiContract
) : MovieRepositoryContract {

  override suspend fun fetchTopRated(): Movies = api
    .fetchTopRated()
    .mapToDomain()

  override suspend fun fetchMovieDetails(id: String): MovieDetails = api
    .fetchMovieDetails(id)
    .mapToDomain()

  override suspend fun fetchTvShowDetails(id: String): MovieDetails = api
    .fetchTvShowDetails(id)
    .mapToDomain()

  override suspend fun fetchPopular(): Movies = api
    .fetchPopular()
    .mapToDomain()

  override suspend fun fetchUpcoming(): Movies = api
    .fetchUpcoming()
    .mapToDomain()

  override suspend fun fetchLatestTv(): Movies = api
    .fetchLatestTv()
    .mapToDomain()

  override suspend fun fetchGenreMovie(): List<Genre> = api
    .fetchGenreMovie()
    .mapToDomain()

  override suspend fun fetchGenreTv(): List<Genre> = api
    .fetchGenreTv()
    .mapToDomain()

  override suspend fun searchByMovie(query: String): Movies =
    api.searchByMovie(query)
      .mapToDomain()

  override suspend fun searchByGenre(idsGenre: String): Movies = api
    .searchByGenre(idsGenre)
    .mapToDomain()
}