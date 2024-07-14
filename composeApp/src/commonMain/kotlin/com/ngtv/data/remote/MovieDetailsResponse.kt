package com.ngtv.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
  val adult: Boolean?,
  @SerialName("backdrop_path")
  val backdropPath: String? = null,
  @SerialName("belongs_to_collection")
  val belongsToCollection: BelongsToCollection? = null,
  val budget: Long? = null,
  val genres: List<Genre>?,
  val homepage: String?,
  val id: Long,
  @SerialName("imdb_id")
  val imdbId: String? = null,
  @SerialName("origin_country")
  val originCountry: List<String>? = null,
  @SerialName("original_language")
  val originalLanguage: String? = null,
  @SerialName("original_name")
  val originalName: String? = null,
  @SerialName("original_title")
  val originalTitle: String? = null,
  val overview: String?,
  val popularity: Double?,
  @SerialName("poster_path")
  val posterPath: String?,
  @SerialName("production_companies")
  val productionCompanies: List<ProductionCompany>?,
  @SerialName("production_countries")
  val productionCountries: List<ProductionCountry>?,
  @SerialName("release_date")
  val releaseDate: String? = null,
  val revenue: Long? = null,
  val runtime: Long? = null,
  @SerialName("spoken_languages")
  val spokenLanguages: List<SpokenLanguage>? = null,
  @SerialName("networks")
  val networks: List<Networks>? = null,
  val status: String? = null,
  val tagline: String? = null,
  val title: String? = null,
  val video: Boolean? = null,
  @SerialName("vote_average")
  val voteAverage: Double? = null,
  @SerialName("vote_count")
  val voteCount: Long? = null,
  @SerialName("watch/providers")
  val watchProviders: WatchProviders? = null,
  val videos: Videos? = null,
  val reviews: Reviews? = null,
  val images: Images? = null,
  val recommendations: Recommendations? = null,
  @SerialName("created_by")
  val createdBy: List<CreatedBy>? = null,
  @SerialName("episode_run_time")
  val episodeRunTime: List<Int>? = null,
  @SerialName("languages")
  val languages: List<String>? = null,
  @SerialName("first_air_date")
  val firstAirDate: String? = null,
  @SerialName("in_production")
  val inProduction: Boolean? = null,
  @SerialName("last_air_date")
  val lastAirDate: String? = null,
  @SerialName("last_episode_to_air")
  val lastEpisodeToAir: LastEpisodeToAir? = null,
  @SerialName("next_episode_to_air")
  val nextEpisodeToAir: LastEpisodeToAir? = null,
  @SerialName("number_of_episodes")
  val numberEpisodes: Int? = null,
  @SerialName("number_of_seasons")
  val numberSeasons: Int? = null,
  val seasons: List<Seasons>? = null,
  val type: String? = null,
)

@Serializable
data class Seasons(
  @SerialName("air_date")
  val airDate: String? = null,
  @SerialName("episode_count")
  val episodeCount: Long? = null,
  val id: Long? = null,
  val name: String? = null,
  val overview: String? = null,
  @SerialName("poster_path")
  val posterPath: String? = null,
  @SerialName("season_number")
  val seasonNumber: Long? = null,
  @SerialName("vote_average")
  val voteAverage: Double? = null,
)

@Serializable
data class LastEpisodeToAir(
  val id: Long? = null,
  @SerialName("name")
  val name: String? = null,
  val overview: String? = null,
  @SerialName("vote_average")
  val voteAverage: Double? = null,
  @SerialName("vote_count")
  val voteCount: Long? = null,
  @SerialName("air_date")
  val airDate: String? = null,
  @SerialName("episode_number")
  val episodeNumber: Long? = null,
  @SerialName("production_code")
  val productionCode: String? = null,
  val runtime: Long? = null,
  @SerialName("season_number")
  val seasonNumber: Long? = null,
  @SerialName("show_id")
  val showId: Long? = null,
  @SerialName("still_path")
  val stillPath: String? = null,
)

@Serializable
data class CreatedBy(
  val id: Long? = null,
  @SerialName("credit_id")
  val creditId: String? = null,
  val name: String? = null,
  @SerialName("original_name")
  val originalName: String? = null,
  val gender: Long? = null,
  @SerialName("profile_path")
  val profilePath: String? = null,
)

@Serializable
data class Networks(
  val id: Long?,
  val name: String?,
  @SerialName("logo_path")
  val logoPath: String?,
  @SerialName("backdrop_path")
  val backdropPath: String? = null,
  @SerialName("origin_country")
  val originCountry: String? = null,
)

@Serializable
data class BelongsToCollection(
  val id: Long?,
  val name: String?,
  @SerialName("poster_path")
  val posterPath: String?,
  @SerialName("backdrop_path")
  val backdropPath: String?,
)

@Serializable
data class GenreResponse(
  val genres: List<Genre> = emptyList()
)

@Serializable
data class Genre(
  val id: Long?,
  val name: String?,
)

@Serializable
data class ProductionCompany(
  val id: Long?,
  @SerialName("logo_path")
  val logoPath: String?,
  val name: String?,
  @SerialName("origin_country")
  val originCountry: String?,
)

@Serializable
data class ProductionCountry(
  @SerialName("iso_3166_1")
  val iso31661: String?,
  val name: String?,
)

@Serializable
data class SpokenLanguage(
  @SerialName("english_name")
  val englishName: String?,
  @SerialName("iso_639_1")
  val iso6391: String?,
  val name: String?,
)

@Serializable
data class WatchProviders(
  val results: WatchProvidersResults? = null,
)

@Serializable
data class WatchProvidersResults(
  @SerialName("AD")
  val ad: WatchProvidersCountry? = null,
  @SerialName("AE")
  val ae: WatchProvidersCountry? = null,
  @SerialName("AG")
  val ag: WatchProvidersCountry? = null,
  @SerialName("AL")
  val al: WatchProvidersCountry? = null,
  @SerialName("AO")
  val ao: WatchProvidersCountry? = null,
  @SerialName("AR")
  val ar: WatchProvidersCountry? = null,
  @SerialName("AT")
  val at: WatchProvidersCountry? = null,
  @SerialName("AU")
  val au: WatchProvidersCountry? = null,
  @SerialName("AZ")
  val az: WatchProvidersCountry? = null,
  @SerialName("BA")
  val ba: WatchProvidersCountry? = null,
  @SerialName("BB")
  val bb: WatchProvidersCountry? = null,
  @SerialName("BE")
  val be: WatchProvidersCountry? = null,
  @SerialName("BF")
  val bf: WatchProvidersCountry? = null,
  @SerialName("BG")
  val bg: WatchProvidersCountry? = null,
  @SerialName("BH")
  val bh: WatchProvidersCountry? = null,
  @SerialName("BO")
  val bo: WatchProvidersCountry? = null,
  @SerialName("BR")
  val br: WatchProvidersCountry? = null,
  @SerialName("BY")
  val by: WatchProvidersCountry? = null,
  @SerialName("BZ")
  val bz: WatchProvidersCountry? = null,
  @SerialName("CA")
  val ca: WatchProvidersCountry? = null,
  @SerialName("CH")
  val ch: WatchProvidersCountry? = null,
  @SerialName("CL")
  val cl: WatchProvidersCountry? = null,
  @SerialName("CO")
  val co: WatchProvidersCountry? = null,
  @SerialName("CR")
  val cr: WatchProvidersCountry? = null,
  @SerialName("CV")
  val cv: WatchProvidersCountry? = null,
  @SerialName("CY")
  val cy: WatchProvidersCountry? = null,
  @SerialName("CZ")
  val cz: WatchProvidersCountry? = null,
  @SerialName("DE")
  val de: WatchProvidersCountry? = null,
  @SerialName("DK")
  val dk: WatchProvidersCountry? = null,
  @SerialName("DO")
  val doField: WatchProvidersCountry? = null,
  @SerialName("DZ")
  val dz: WatchProvidersCountry? = null,
  @SerialName("EC")
  val ec: WatchProvidersCountry? = null,
  @SerialName("EE")
  val ee: WatchProvidersCountry? = null,
  @SerialName("EG")
  val eg: WatchProvidersCountry? = null,
  @SerialName("ES")
  val es: WatchProvidersCountry? = null,
  @SerialName("FI")
  val fi: WatchProvidersCountry? = null,
  @SerialName("FR")
  val fr: WatchProvidersCountry? = null,
  @SerialName("GB")
  val gb: WatchProvidersCountry? = null,
  @SerialName("GF")
  val gf: WatchProvidersCountry? = null,
  @SerialName("GH")
  val gh: WatchProvidersCountry? = null,
  @SerialName("GI")
  val gi: WatchProvidersCountry? = null,
  @SerialName("GR")
  val gr: WatchProvidersCountry? = null,
  @SerialName("GT")
  val gt: WatchProvidersCountry? = null,
  @SerialName("HK")
  val hk: WatchProvidersCountry? = null,
  @SerialName("HN")
  val hn: WatchProvidersCountry? = null,
  @SerialName("HR")
  val hr: WatchProvidersCountry? = null,
  @SerialName("HU")
  val hu: WatchProvidersCountry? = null,
  @SerialName("ID")
  val id: WatchProvidersCountry? = null,
  @SerialName("IE")
  val ie: WatchProvidersCountry? = null,
  @SerialName("IL")
  val il: WatchProvidersCountry? = null,
  @SerialName("IN")
  val inField: WatchProvidersCountry? = null,
  @SerialName("IQ")
  val iq: WatchProvidersCountry? = null,
  @SerialName("IS")
  val isField: WatchProvidersCountry? = null,
  @SerialName("IT")
  val it: WatchProvidersCountry? = null,
  @SerialName("JM")
  val jm: WatchProvidersCountry? = null,
  @SerialName("JO")
  val jo: WatchProvidersCountry? = null,
  @SerialName("JP")
  val jp: WatchProvidersCountry? = null,
  @SerialName("KR")
  val kr: WatchProvidersCountry? = null,
  @SerialName("KW")
  val kw: WatchProvidersCountry? = null,
  @SerialName("LB")
  val lb: WatchProvidersCountry? = null,
  @SerialName("LC")
  val lc: WatchProvidersCountry? = null,
  @SerialName("LI")
  val li: WatchProvidersCountry? = null,
  @SerialName("LT")
  val lt: WatchProvidersCountry? = null,
  @SerialName("LU")
  val lu: WatchProvidersCountry? = null,
  @SerialName("LV")
  val lv: WatchProvidersCountry? = null,
  @SerialName("LY")
  val ly: WatchProvidersCountry? = null,
  @SerialName("MA")
  val ma: WatchProvidersCountry? = null,
  @SerialName("MC")
  val mc: WatchProvidersCountry? = null,
  @SerialName("MD")
  val md: WatchProvidersCountry? = null,
  @SerialName("MK")
  val mk: WatchProvidersCountry? = null,
  @SerialName("ML")
  val ml: WatchProvidersCountry? = null,
  @SerialName("MT")
  val mt: WatchProvidersCountry? = null,
  @SerialName("MU")
  val mu: WatchProvidersCountry? = null,
  @SerialName("MX")
  val mx: WatchProvidersCountry? = null,
  @SerialName("MY")
  val my: WatchProvidersCountry? = null,
  @SerialName("MZ")
  val mz: WatchProvidersCountry? = null,
  @SerialName("NI")
  val ni: WatchProvidersCountry? = null,
  @SerialName("NL")
  val nl: WatchProvidersCountry? = null,
  @SerialName("NO")
  val no: WatchProvidersCountry? = null,
  @SerialName("NZ")
  val nz: WatchProvidersCountry? = null,
  @SerialName("OM")
  val om: WatchProvidersCountry? = null,
  @SerialName("PA")
  val pa: WatchProvidersCountry? = null,
  @SerialName("PE")
  val pe: WatchProvidersCountry? = null,
  @SerialName("PF")
  val pf: WatchProvidersCountry? = null,
  @SerialName("PG")
  val pg: WatchProvidersCountry? = null,
  @SerialName("PH")
  val ph: WatchProvidersCountry? = null,
  @SerialName("PK")
  val pk: WatchProvidersCountry? = null,
  @SerialName("PL")
  val pl: WatchProvidersCountry? = null,
  @SerialName("PS")
  val ps: WatchProvidersCountry? = null,
  @SerialName("PT")
  val pt: WatchProvidersCountry? = null,
  @SerialName("PY")
  val py: WatchProvidersCountry? = null,
  @SerialName("QA")
  val qa: WatchProvidersCountry? = null,
  @SerialName("RO")
  val ro: WatchProvidersCountry? = null,
  @SerialName("RS")
  val rs: WatchProvidersCountry? = null,
  @SerialName("RU")
  val ru: WatchProvidersCountry? = null,
  @SerialName("SA")
  val sa: WatchProvidersCountry? = null,
  @SerialName("SE")
  val se: WatchProvidersCountry? = null,
  @SerialName("SG")
  val sg: WatchProvidersCountry? = null,
  @SerialName("SI")
  val si: WatchProvidersCountry? = null,
  @SerialName("SK")
  val sk: WatchProvidersCountry? = null,
  @SerialName("SM")
  val sm: WatchProvidersCountry? = null,
  @SerialName("SV")
  val sv: WatchProvidersCountry? = null,
  @SerialName("TC")
  val tc: WatchProvidersCountry? = null,
  @SerialName("TH")
  val th: WatchProvidersCountry? = null,
  @SerialName("TN")
  val tn: WatchProvidersCountry? = null,
  @SerialName("TR")
  val tr: WatchProvidersCountry? = null,
  @SerialName("TT")
  val tt: WatchProvidersCountry? = null,
  @SerialName("TW")
  val tw: WatchProvidersCountry? = null,
  @SerialName("TZ")
  val tz: WatchProvidersCountry? = null,
  @SerialName("UA")
  val ua: WatchProvidersCountry? = null,
  @SerialName("UG")
  val ug: WatchProvidersCountry? = null,
  @SerialName("US")
  val us: WatchProvidersCountry? = null,
  @SerialName("UY")
  val uy: WatchProvidersCountry? = null,
  @SerialName("VE")
  val ve: WatchProvidersCountry? = null,
  @SerialName("YE")
  val ye: WatchProvidersCountry? = null,
  @SerialName("ZA")
  val za: WatchProvidersCountry? = null,
  @SerialName("ZW")
  val zw: WatchProvidersCountry? = null,
)

@Serializable
data class WatchProvidersCountry(
  val link: String? = null,
  val rent: List<RentOrBuy>? = null,
  val buy: List<RentOrBuy>? = null,
  val flatrate: List<RentOrBuy>? = null
)


@Serializable
data class RentOrBuy(
  @SerialName("logo_path")
  val logoPath: String?,
  @SerialName("provider_id")
  val providerId: Long?,
  @SerialName("provider_name")
  val providerName: String?,
  @SerialName("display_priority")
  val displayPriority: Long?,
)

@Serializable
data class Videos(
  val results: List<VideosResult>?,
)

@Serializable
data class VideosResult(
  @SerialName("iso_639_1")
  val iso6391: String?,
  @SerialName("iso_3166_1")
  val iso31661: String?,
  val name: String?,
  val key: String?,
  @SerialName("published_at")
  val publishedAt: String?,
  val site: String?,
  val size: Long?,
  val type: String?,
  val official: Boolean?,
  val id: String?,
)

@Serializable
data class Reviews(
  val page: Long?,
  val results: List<ReviewsResult>?,
  @SerialName("total_pages")
  val totalPages: Long?,
  @SerialName("total_results")
  val totalResults: Long?,
)

@Serializable
data class ReviewsResult(
  val author: String?,
  @SerialName("author_details")
  val authorDetails: AuthorDetails?,
  val content: String?,
  @SerialName("created_at")
  val createdAt: String?,
  val id: String?,
  @SerialName("updated_at")
  val updatedAt: String?,
  val url: String?,
)

@Serializable
data class AuthorDetails(
  val name: String?,
  val username: String?,
  @SerialName("avatar_path")
  val avatarPath: String?,
  val rating: Float?,
)

@Serializable
data class Images(
  val backdrops: List<Backdrop>?,
  val id: Long? = null,
  val logos: List<Logo>?,
  val posters: List<Poster>?,
)

@Serializable
data class Backdrop(
  @SerialName("aspect_ratio")
  val aspectRatio: Double?,
  val height: Long?,
  @SerialName("iso_639_1")
  val iso6391: String?,
  @SerialName("file_path")
  val filePath: String?,
  @SerialName("vote_average")
  val voteAverage: Double?,
  @SerialName("vote_count")
  val voteCount: Long?,
  val width: Long?,
)

@Serializable
data class Logo(
  @SerialName("aspect_ratio")
  val aspectRatio: Double?,
  val height: Long?,
  @SerialName("iso_639_1")
  val iso6391: String?,
  @SerialName("file_path")
  val filePath: String?,
  @SerialName("vote_average")
  val voteAverage: Double?,
  @SerialName("vote_count")
  val voteCount: Long?,
  val width: Long?,
)

@Serializable
data class Poster(
  @SerialName("aspect_ratio")
  val aspectRatio: Double?,
  val height: Long?,
  @SerialName("iso_639_1")
  val iso6391: String?,
  @SerialName("file_path")
  val filePath: String?,
  @SerialName("vote_average")
  val voteAverage: Double?,
  @SerialName("vote_count")
  val voteCount: Long?,
  val width: Long?,
)

@Serializable
data class Recommendations(
  val page: Long?,
  val results: List<RecommendationsResult>?,
  @SerialName("total_pages")
  val totalPages: Long?,
  @SerialName("total_results")
  val totalResults: Long?,
)

@Serializable
data class RecommendationsResult(
  @SerialName("backdrop_path")
  val backdropPath: String? = null,
  val id: Long,
  val title: String?,
  @SerialName("original_title")
  val originalTitle: String? = null,
  val overview: String?,
  @SerialName("poster_path")
  val posterPath: String?,
  @SerialName("media_type")
  val mediaType: String?,
  val adult: Boolean?,
  @SerialName("original_language")
  val originalLanguage: String?,
  @SerialName("genre_ids")
  val genreIds: List<Long>?,
  val popularity: Double?,
  @SerialName("release_date")
  val releaseDate: String?,
  val video: Boolean?,
  @SerialName("vote_average")
  val voteAverage: Double?,
  @SerialName("vote_count")
  val voteCount: Long?,
)
