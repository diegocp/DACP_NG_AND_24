package com.ngtv.data.domain


data class Movies(
  val results: List<Movie>
)

data class Movie(
  val adult: Boolean?,
  val backdropPath: String?,
  val genreIds: List<Long>?,
  val id: Long,
  val originalLanguage: String?,
  val originalTitle: String?,
  val overview: String?,
  val popularity: Double?,
  val posterPath: String?,
  val releaseDate: String?,
  val title: String?,
  val video: Boolean?,
  val voteAverage: Double?,
  val voteCount: Long?,
  val mediaType: String?,
  val name: String?,
  val originalName: String?,
)