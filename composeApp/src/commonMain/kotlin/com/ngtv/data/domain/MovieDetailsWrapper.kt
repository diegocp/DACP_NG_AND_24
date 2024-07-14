package com.ngtv.data.domain

import com.ngtv.data.remote.MovieDetailsResponse


fun MovieDetailsResponse.mapToDomain(): MovieDetails {
  return MovieDetails(
    adult = this.adult,
    budget = this.budget,
    genres = this.genres?.map { Genre(it.id, it.name) } ?: emptyList(),
    homepage = this.homepage,
    id = this.id,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle ?: this.originalName,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    backdropPath = this.backdropPath,
    productionCompanies = this.productionCompanies?.map {
      ProductionCompany(
        it.id,
        it.logoPath,
        it.name,
        it.originCountry
      )
    },
    productionCountries = this.productionCountries?.map { ProductionCountry(it.name) },
    releaseDate = this.releaseDate,
    revenue = this.revenue,
    spokenLanguages = this.spokenLanguages?.map { SpokenLanguage(it.englishName, it.name) }
      ?: emptyList(),
    status = this.status,
    tagline = this.tagline,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    watchProviders = WatchProviders(
      results = WatchProvidersResults(
        br = WatchProvidersCountry(
          link = this.watchProviders?.results?.br?.link,
          rent = this.watchProviders?.results?.br?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.br?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        de = WatchProvidersCountry(
          link = this.watchProviders?.results?.de?.link,
          rent = this.watchProviders?.results?.de?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.de?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        gb = WatchProvidersCountry(
          link = this.watchProviders?.results?.gb?.link,
          rent = this.watchProviders?.results?.gb?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.gb?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        es = WatchProvidersCountry(
          link = this.watchProviders?.results?.es?.link,
          rent = this.watchProviders?.results?.es?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.es?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        ie = WatchProvidersCountry(
          link = this.watchProviders?.results?.ie?.link,
          rent = this.watchProviders?.results?.ie?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.ie?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        it = WatchProvidersCountry(
          link = this.watchProviders?.results?.it?.link,
          rent = this.watchProviders?.results?.it?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.it?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        nl = WatchProvidersCountry(
          link = this.watchProviders?.results?.nl?.link,
          rent = this.watchProviders?.results?.nl?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.nl?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        ),
        pt = WatchProvidersCountry(
          link = this.watchProviders?.results?.pt?.link,
          rent = this.watchProviders?.results?.pt?.rent?.map {
            Rent(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          },
          buy = this.watchProviders?.results?.pt?.buy?.map {
            Buy(
              it.logoPath,
              it.providerId,
              it.providerName,
              it.displayPriority
            )
          }
        )
      )
    ),
    networks = this.networks?.map {
      Networks(
        id = it.id,
        name = it.name,
        logoPath = it.logoPath
      )
    },
    videos = Videos(this.videos?.results?.map {
      VideosResult(
        it.key,
        it.publishedAt,
        it.site,
        it.type,
        it.official,
        it.id
      )
    }),
    reviews = Reviews(this.reviews?.results?.map {
      ReviewsResult(
        it.author,
        it.content,
        it.createdAt,
        it.updatedAt
      )
    }),
    images = Images(
      backdrops = this.images?.backdrops
        ?.map {
          Backdrop(
            aspectRatio = it.aspectRatio,
            height = it.height,
            iso6391 = it.iso6391,
            filePath = it.filePath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            width = it.width,
          )
        },
      logos = this.images?.logos
        ?.map {
          Logo(
            aspectRatio = it.aspectRatio,
            height = it.height,
            iso6391 = it.iso6391,
            filePath = it.filePath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            width = it.width,
          )

        },
      posters = this.images?.posters
        ?.map {
          Poster(
            aspectRatio = it.aspectRatio,
            height = it.height,
            iso6391 = it.iso6391,
            filePath = it.filePath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            width = it.width,
          )
        },
      id = this.images?.id
    ),
    recommendations = Recommendations(this.recommendations?.results?.map {
      RecommendationsResult(
        backdropPath = it.backdropPath,
        id = it.id,
        title = it.title,
        originalTitle = it.originalTitle,
        overview = it.overview,
        posterPath = it.posterPath,
        originalLanguage = it.originalLanguage,
        genreIds = it.genreIds,
        popularity = it.popularity,
        releaseDate = it.releaseDate,
        video = it.video,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
      )
    })
  )
}