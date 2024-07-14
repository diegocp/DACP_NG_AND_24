package com.ngtv.data.domain

data class MovieDetails(
  val adult: Boolean?,
  val budget: Long?,
  val genres: List<Genre>?,
  val homepage: String?,
  val id: Long,
  val originalLanguage: String?,
  val originalTitle: String?,
  val overview: String?,
  val popularity: Double?,
  val posterPath: String?,
  val backdropPath: String?,
  val productionCompanies: List<ProductionCompany>?,
  val productionCountries: List<ProductionCountry>?,
  val releaseDate: String?,
  val revenue: Long?,
  val spokenLanguages: List<SpokenLanguage>?,
  val status: String?,
  val tagline: String?,
  val title: String?,
  val video: Boolean?,
  val voteAverage: Double?,
  val voteCount: Long?,
  val watchProviders: WatchProviders?,
  val videos: Videos?,
  val reviews: Reviews? = null,
  val images: Images? = null,
  val recommendations: Recommendations? = null,
  val networks: List<Networks>?
)

data class Networks(
  val id: Long?,
  val name: String?,
  val logoPath: String?,
)

data class Genre(
  val id: Long?,
  val name: String?,
)

data class ProductionCompany(
  val id: Long?,
  val logoPath: String?,
  val name: String?,
  val originCountry: String?,
)

data class ProductionCountry(
  val name: String?,
)

data class SpokenLanguage(
  val englishName: String?,
  val name: String?,
)

data class WatchProviders(
  val results: WatchProvidersResults?,
)

data class WatchProvidersResults(
  val br: WatchProvidersCountry?,
  val de: WatchProvidersCountry?,
  val es: WatchProvidersCountry?,
  val gb: WatchProvidersCountry?,
  val ie: WatchProvidersCountry?,
  val it: WatchProvidersCountry?,
  val nl: WatchProvidersCountry?,
  val pt: WatchProvidersCountry?,
)

data class WatchProvidersCountry(
  val link: String?,
  val rent: List<Rent>?,
  val buy: List<Buy>?,
)

data class Rent(
  val logoPath: String?,
  val providerId: Long?,
  val providerName: String?,
  val displayPriority: Long?,
)

data class Buy(
  val logoPath: String?,
  val providerId: Long?,
  val providerName: String?,
  val displayPriority: Long?,
)

data class Videos(
  val results: List<VideosResult>?,
)

data class VideosResult(
  val key: String?,
  val publishedAt: String?,
  val site: String?,
  val type: String?,
  val official: Boolean?,
  val id: String?,
)

data class Reviews(
  val results: List<ReviewsResult>?,
)

data class ReviewsResult(
  val author: String?,
  val content: String?,
  val createdAt: String?,
  val updatedAt: String?,
)

data class Images(
  val backdrops: List<Backdrop>?,
  val id: Long?,
  val logos: List<Logo>?,
  val posters: List<Poster>?,
)


data class Backdrop(
  val aspectRatio: Double?,
  val height: Long?,
  val iso6391: String?,
  val filePath: String?,
  val voteAverage: Double?,
  val voteCount: Long?,
  val width: Long?,
)


data class Logo(
  val aspectRatio: Double?,
  val height: Long?,
  val iso6391: String?,
  val filePath: String?,
  val voteAverage: Double?,
  val voteCount: Long?,
  val width: Long?,
)

data class Poster(
  val aspectRatio: Double?,
  val height: Long?,
  val iso6391: String?,
  val filePath: String?,
  val voteAverage: Double?,
  val voteCount: Long?,
  val width: Long?,
)

data class Recommendations(
  val results: List<RecommendationsResult>?
)

data class RecommendationsResult(
  val backdropPath: String?,
  val id: Long,
  val title: String?,
  val originalTitle: String?,
  val overview: String?,
  val posterPath: String?,
  val originalLanguage: String?,
  val genreIds: List<Long>?,
  val popularity: Double?,
  val releaseDate: String?,
  val video: Boolean?,
  val voteAverage: Double?,
  val voteCount: Long?
)
