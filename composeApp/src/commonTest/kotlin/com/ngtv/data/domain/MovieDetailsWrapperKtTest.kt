package com.ngtv.data.domain

import com.ngtv.data.remote.MovieDetailsResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieDetailsWrapperKtTest {

  @Test
  fun `mapToDomain maps properties correctly`() {
    val movieDetailsResponse = generateMockMovieDetailsResponse().mapToDomain()

    assertEquals(false, movieDetailsResponse.adult)
    assertEquals(150000000, movieDetailsResponse.budget)
    assertEquals(listOf(Genre(28, "Action"), Genre(12, "Adventure")), movieDetailsResponse.genres)
    assertEquals("https://mockmoviehomepage.com", movieDetailsResponse.homepage)
    assertEquals(550, movieDetailsResponse.id)
    assertEquals("en", movieDetailsResponse.originalLanguage)
    assertEquals("Mock Original Title", movieDetailsResponse.originalTitle)
    assertEquals("This is a mock overview of the movie.", movieDetailsResponse.overview)
    assertEquals(22.514, movieDetailsResponse.popularity)
    assertEquals("/mockPosterPath.jpg", movieDetailsResponse.posterPath)
    assertEquals("/mockBackdropPath.jpg", movieDetailsResponse.backdropPath)
    assertEquals(listOf(ProductionCompany(1, "/mockLogoPath.jpg", "Mock Production Company", "US")), movieDetailsResponse.productionCompanies)
    assertEquals("1999-10-15", movieDetailsResponse.releaseDate)
    assertEquals(1000000000, movieDetailsResponse.revenue)
    assertEquals("Mock tagline of the movie", movieDetailsResponse.tagline)
    assertEquals("Mock Movie Title", movieDetailsResponse.title)
    assertEquals(false, movieDetailsResponse.video)
    assertEquals(8.3, movieDetailsResponse.voteAverage)
    assertEquals(12999, movieDetailsResponse.voteCount)
    assertEquals("Mock Original Title", movieDetailsResponse.originalTitle)
//    assertEquals(listOf("US"), movieDetailsResponse.originCountry)
//    assertEquals(BelongsToCollection(1, "Mock Collection Name", "/mockPosterPath.jpg", "/mockBackdropPath.jpg"), movieDetailsResponse.belongsToCollection)
// For watchProviders, networks, videos, reviews, images, and recommendations, you might need to assert their nested properties individually due to their complex structures.
//    assertEquals(listOf(ProductionCountry("en", "en")), movieDetailsResponse.productionCountries)
//    assertEquals(listOf(SpokenLanguage("English", "EN", "en")), movieDetailsResponse.spokenLanguages)
    assertEquals("Released", movieDetailsResponse.status)
  }


  private fun generateMockMovieDetailsResponse(): MovieDetailsResponse {
    return MovieDetailsResponse(
      adult = false,
      budget = 150000000,
      imdbId = "imdbId",
      genres = listOf(
        com.ngtv.data.remote.Genre(28, "Action"),
        com.ngtv.data.remote.Genre(12, "Adventure")),
      homepage = "https://mockmoviehomepage.com",
      id = 550,
      originalLanguage = "en",
      originalTitle = "Mock Original Title",
      originalName = "Mock Original Name",
      overview = "This is a mock overview of the movie.",
      popularity = 22.514,
      posterPath = "/mockPosterPath.jpg",
      backdropPath = "/mockBackdropPath.jpg",
      productionCompanies = listOf(
        com.ngtv.data.remote.ProductionCompany(1, "/mockLogoPath.jpg", "Mock Production Company", "US")
      ),
      productionCountries = listOf(
        com.ngtv.data.remote.ProductionCountry("en", "en")
      ),
      releaseDate = "1999-10-15",
      revenue = 1000000000,
      spokenLanguages = listOf(
        com.ngtv.data.remote.SpokenLanguage("English", "EN", "en")
      ),
      status = "Released",
      tagline = "Mock tagline of the movie",
      title = "Mock Movie Title",
      video = false,
      voteAverage = 8.3,
      voteCount = 12999,
      watchProviders = com.ngtv.data.remote.WatchProviders(
        results = com.ngtv.data.remote.WatchProvidersResults(
          ie = com.ngtv.data.remote.WatchProvidersCountry(
            "link",
            listOf(com.ngtv.data.remote.RentOrBuy("/mockLogoPath.jpg", 1, "Mock Provider Name", 1)),
            listOf(com.ngtv.data.remote.RentOrBuy("/mockLogoPath.jpg", 1, "Mock Provider Name", 1))
          )
        )
      ),
      networks = listOf(com.ngtv.data.remote.Networks(1, "Mock Network Name", "/mockNetworkLogoPath.jpg","/mockNetworkLogoPath.jpg" )),
      videos = com.ngtv.data.remote.Videos(
        listOf(
          com.ngtv.data.remote.VideosResult(
            "ie",
            "ie",
            "YouTube",
            "Trailer",
            "2020-12-25",
            "www.com.ie",
            1,
            type = "movie",
            false,
            "id"
          )
        )
      ),
      reviews = com.ngtv.data.remote.Reviews(
        1,
        totalPages = 1,
        totalResults = 1,
        results = listOf(
          com.ngtv.data.remote.ReviewsResult(
            "Mock Author Name",
            com.ngtv.data.remote.AuthorDetails("Mock Review Content", "username", "avatar", 7.8922f),
            "2020-12-25",
            "2020-12-26",
            "id",
            "2024-12-25",
            "www.com.ie",
          )
        )
      ),
      images = com.ngtv.data.remote.Images(
        backdrops = listOf(com.ngtv.data.remote.Backdrop(1.5, 1080, "en", "/mockBackdropPath.jpg", 7.8, 200, 1920)),
        logos = listOf(com.ngtv.data.remote.Logo(1.5, 1080, "en", "/mockLogoPath.jpg", 7.8, 200, 1920)),
        posters = listOf(com.ngtv.data.remote.Poster(1.5, 1080, "en", "/mockPosterPath.jpg", 7.8, 200, 1920)),
        id = 550
      ),
      originCountry = listOf("US"),
      belongsToCollection = com.ngtv.data.remote.BelongsToCollection(1, "Mock Collection Name", "/mockPosterPath.jpg", "/mockBackdropPath.jpg"),
      runtime = 120,
      recommendations = com.ngtv.data.remote.Recommendations(
        results = listOf(
          com.ngtv.data.remote.RecommendationsResult(
            backdropPath = "/mockBackdropPath.jpg",
            id = 123,
            title = "Recommended Mock Title",
            originalTitle = "Original Recommended Mock Title",
            overview = "Overview",
            posterPath = "/mockPosterPath.jpg",
            mediaType = "movie",
            false,
            originalLanguage = "en",
            genreIds = listOf(1, 2),
            popularity = 8.5,
            releaseDate = "2020-12-25",
            video = false,
            voteAverage = 7.8,
            voteCount = 200
          )
        ),
        totalResults = 1,
        totalPages = 1,
        page = 1
      )
    )
  }
}
