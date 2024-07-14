package com.ngtv.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
  val dates: Dates? = null,
  val page: Long? = null,
  val results: List<Movie>? = null,
  @SerialName("total_pages")
  val totalPages: Long? = null,
  @SerialName("total_results")
  val totalResults: Long? = null,
)

@Serializable
data class Dates(
  val maximum: String? = null,
  val minimum: String? = null,
)

@Serializable
data class Movie(
  val adult: Boolean?,
  @SerialName("backdrop_path")
  val backdropPath: String?,
  @SerialName("genre_ids")
  val genreIds: List<Long>?,
  val id: Long,
  @SerialName("original_language")
  val originalLanguage: String?,
  @SerialName("original_title")
  val originalTitle: String?,
  val overview: String?,
  val popularity: Double?,
  @SerialName("poster_path")
  val posterPath: String?,
  @SerialName("media_type")
  val mediaType: String? = null,
  @SerialName("release_date")
  val releaseDate: String?,
  val title: String?,
  val video: Boolean?,
  @SerialName("vote_average")
  val voteAverage: Double?,
  @SerialName("vote_count")
  val voteCount: Long?,
  val name: String? = null,
  @SerialName("original_name")
  val originalName: String? = null,
)