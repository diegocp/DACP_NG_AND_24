package com.ngtv.di

import NGtv.composeApp.BuildConfig
import com.ngtv.data.MovieRepository
import com.ngtv.data.MovieRepositoryContract
import com.ngtv.data.remote.GeminiApi
import com.ngtv.data.remote.GeminiApiContract
import com.ngtv.data.remote.MovieApi
import com.ngtv.data.remote.MovieApiContract
import com.ngtv.usecases.MovieUseCase
import com.ngtv.usecases.MovieUseCaseContract
import com.ngtv.viewmodels.GenreViewModel
import com.ngtv.viewmodels.MovieDetailsViewModel
import com.ngtv.viewmodels.MovieViewModel
import com.ngtv.viewmodels.SearchViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
  return startKoin {
    appDeclaration()
    modules(
      ktorEngineModule(),
      ktorModuleAndroid,
      movieModule,
      viewModelsModule
    )
  }
}

fun initKoinDesktop(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
  return startKoin {
    appDeclaration()
    modules(
      ktorEngineModule(),
      ktorModuleDesktop,
      movieModule,
      viewModelsModule
    )
  }
}

expect val language: String

val movieModule = module {
  single<MovieApiContract> { MovieApi(get()) }
  single<GeminiApiContract> { GeminiApi() }
  single<MovieRepositoryContract> { MovieRepository(get()) }
  single<MovieUseCaseContract> { MovieUseCase(get()) }
}

val viewModelsModule = module {
  single { MovieViewModel() }
  factory { MovieDetailsViewModel() }
  single { SearchViewModel() }
  single { GenreViewModel() }
}

val ktorModuleAndroid = module {
  single { createJson() }
  single { createKtorClient(get(), get()) }
}

val ktorModuleDesktop = module {
  single { createJson() }
  single { createKtorClient(get()) }
}

@OptIn(ExperimentalSerializationApi::class)
fun createJson() = Json {
  isLenient = true
  ignoreUnknownKeys = true
  useAlternativeNames = true
  prettyPrint = true
  explicitNulls = false
  encodeDefaults = true
}

fun createKtorClient(json: Json, engine: HttpClientEngine? = null): HttpClient {
  return if (engine != null) {
    return HttpClient(engine) {
      install(ContentNegotiation) {
        json(json = json)
      }

      defaultRequest {
        url {
          protocol = URLProtocol.HTTPS
          headers {
            append(HttpHeaders.ContentType, "application/json")
            append(HttpHeaders.Accept, "application/json")
            append(
              "Authorization",
              "Bearer ${BuildConfig.API_KEY}"
            )
          }
        }
      }

      install(HttpTimeout) {
        this.requestTimeoutMillis = 60000
        this.connectTimeoutMillis = 60000
        this.socketTimeoutMillis = 60000
      }

      install(HttpCache)
    }

  } else {
    HttpClient {
      install(ContentNegotiation) {
        json(json = json)
      }

      defaultRequest {
        url {
          protocol = URLProtocol.HTTPS
          headers {
            append(HttpHeaders.ContentType, "application/json")
            append(HttpHeaders.Accept, "application/json")
            append(
              "Authorization",
              "Bearer ${BuildConfig.API_KEY}"
            )
          }
        }
      }

      install(HttpTimeout) {
        this.requestTimeoutMillis = 60000
        this.connectTimeoutMillis = 60000
        this.socketTimeoutMillis = 60000
      }

      install(HttpCache)

    }
  }

}

expect fun ktorEngineModule(): Module