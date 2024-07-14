package com.ngtv.data.remote

import com.ngtv.di.createJson
import com.ngtv.di.createKtorClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MovieApiTest {

  @Test
  fun `fetch top rated movies returns expected data`() = runTest {
    val mockEngine = MockEngine { request ->
      assertEquals("https://api.themoviedb.org/3/movie/top_rated?language=en", request.url.toString())
      when (request.url.encodedPath) {
        "/3/movie/top_rated" -> respond(
          content = mockResponseMovie,
          headers = headersOf(
            HttpHeaders.ContentType,
            ContentType.Application.Json.toString()
          )
        )

        else -> {
          error("Unhandled ${request.url.encodedPath}")
        }
      }
    }

    val movieApi = MovieApi(createKtorClient(createJson(), mockEngine))
    val response = movieApi.fetchTopRated()

    val expectedMovieResponse = Json.decodeFromString(MovieResponse.serializer(), mockResponseMovie)
    assertEquals(expectedMovieResponse, response)
  }

  @Test
  fun `fetch movie details returns expected data`() = runTest {
    val mockEngine = MockEngine { request ->
      assertEquals("https://api.themoviedb.org/3/movie/238?language=en&append_to_response=watch%2Fproviders%2Cvideos%2Creviews%2Cimages%2Crecommendations", request.url.toString())
      when (request.url.encodedPath) {
        "/3/movie/238" -> respond(
          content = mockMovieDetail,
          headers = headersOf(
            HttpHeaders.ContentType,
            ContentType.Application.Json.toString()
          )
        )

        else -> {
          error("Unhandled ${request.url.encodedPath}")
        }
      }
    }

    val movieApi = MovieApi(createKtorClient(createJson(), mockEngine))
    val response = movieApi.fetchMovieDetails("238")

    val expectedMovieResponse = Json.decodeFromString(MovieDetailsResponse.serializer(), mockMovieDetail)
    assertEquals(expectedMovieResponse, response)
  }

  @Test
  fun `fetch tv show details returns expected data`() = runTest {
    val mockEngine = MockEngine { request ->
      assertEquals("https://api.themoviedb.org/3/tv/1399?language=en&append_to_response=watch%2Fproviders%2Cvideos%2Creviews%2Cimages%2Crecommendations", request.url.toString())
      when (request.url.encodedPath) {
        "/3/tv/1399" -> respond(
          content = mockTvShowDetail,
          headers = headersOf(
            HttpHeaders.ContentType,
            ContentType.Application.Json.toString()
          )
        )

        else -> {
          error("Unhandled ${request.url.encodedPath}")
        }
      }
    }

    val movieApi = MovieApi(createKtorClient(createJson(), mockEngine))
    val response = movieApi.fetchTvShowDetails("1399")

    val expectedMovieResponse = Json.decodeFromString(MovieDetailsResponse.serializer(), mockTvShowDetail)
    assertEquals(expectedMovieResponse, response)
  }



  @Test
  fun failure() = runTest {
    val mockEngine = MockEngine {
      respond(
        content = "",
        status = HttpStatusCode.NotFound,
        headers = headersOf(
          HttpHeaders.ContentType,
          ContentType.Application.Json.toString()
        )
      )
    }
    val client = HttpClient(mockEngine) {
      expectSuccess = true
      install(ContentNegotiation) {
        json()
      }
    }
    val moveApi = MovieApi(client)

    assertFailsWith<ClientRequestException> {
      moveApi.fetchTopRated()
    }
  }


}