package com.ngtv.data.remote

import com.ngtv.di.language
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface MovieApiContract {
  suspend fun fetchTopRated(): MovieResponse
  suspend fun fetchPopular(): MovieResponse
  suspend fun fetchUpcoming(): MovieResponse
  suspend fun fetchLatestTv(): MovieResponse
  suspend fun fetchMovieDetails(id: String): MovieDetailsResponse
  suspend fun fetchTvShowDetails(id: String): MovieDetailsResponse
  suspend fun fetchGenreMovie(): GenreResponse
  suspend fun fetchGenreTv(): GenreResponse
  suspend fun searchByMovie(query: String): MovieResponse
  suspend fun searchByGenre(idsGenre: String): MovieResponse
}

class MovieApi(
  private val client: HttpClient,
  private val baseUrl: String = "https://api.themoviedb.org/3",
) : MovieApiContract {

  private val params = "?language=$language" // todo add &region=B

  override suspend fun fetchTopRated(): MovieResponse {
    val response = client.get("$baseUrl/movie/top_rated$params")
    return response.body()
  }

  override suspend fun fetchPopular(): MovieResponse = client
    .get("$baseUrl/movie/popular$params")
    .body()


  override suspend fun fetchUpcoming(): MovieResponse = client
    .get("$baseUrl/movie/upcoming$params")
    .body()

  override suspend fun fetchLatestTv(): MovieResponse = client
  .get("$baseUrl/tv/top_rated$params")
  .body()

  override suspend fun fetchMovieDetails(id: String): MovieDetailsResponse {
    val appendToResponseParam =
      "watch%2Fproviders%2Cvideos%2Creviews%2Cimages%2Crecommendations"
    val response = client.get("$baseUrl/movie/$id${params}&append_to_response=$appendToResponseParam")
    return response.body()
  }

  override suspend fun fetchTvShowDetails(id: String): MovieDetailsResponse {
    val appendToResponseParam =
      "watch%2Fproviders%2Cvideos%2Creviews%2Cimages%2Crecommendations"
    val response = client.get("$baseUrl/tv/$id${params}&append_to_response=$appendToResponseParam")
    return response.body()
  }

  override suspend fun fetchGenreMovie(): GenreResponse =
    client.get("$baseUrl/genre/movie/list${params}").body<GenreResponse>()

  override suspend fun fetchGenreTv(): GenreResponse =
    client.get("$baseUrl/genre/tv/list${params}").body<GenreResponse>()

  override suspend fun searchByMovie(query: String): MovieResponse =
    client.get("$baseUrl/search/multi${params}&query=$query").body()

  override suspend fun searchByGenre(idsGenre: String): MovieResponse =
    client.get("$baseUrl/discover/movie${params}&with_genres=$idsGenre").body()

}