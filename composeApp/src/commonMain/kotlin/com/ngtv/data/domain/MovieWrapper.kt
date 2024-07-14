package com.ngtv.data.domain

import com.ngtv.data.remote.GenreResponse
import com.ngtv.data.remote.MovieResponse

fun MovieResponse.mapToDomain(): Movies {
  return Movies(
    results = this.results?.map {
      Movie(
        adult = it.adult,
        backdropPath = it.backdropPath,
        genreIds = it.genreIds,
        id = it.id,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        overview = it.overview,
        popularity = it.popularity,
        posterPath = it.posterPath,
        releaseDate = it.releaseDate,
        title = it.title,
        video = it.video,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        mediaType = it.mediaType,
        name = it.name,
        originalName = it.originalName
      )
    } ?: emptyList()
  )
}

fun GenreResponse.mapToDomain(): List<Genre> {
  return this.genres.map { Genre(it.id, it.name) }
}