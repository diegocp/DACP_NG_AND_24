# NGtv

<img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/logo.webp">

## App Description

NGtv is a comprehensive movie and TV show discovery app that brings the vast world of cinema and television to your fingertips. With an intuitive and user-friendly interface, users can explore a wide range of content . Here are the key features:
This is a Kotlin Multiplatform project targeting Android, Desktop and iOS.

- **Discover Top Rated Movies and TV Shows**: Browse through lists of top-rated content, curated from global ratings.
- **Explore Popular Movies**: Dive into a collection of movies that are currently popular among viewers.
- **Find Upcoming Releases**: Stay updated with movies and TV shows that are about to hit the screens.
- **Latest TV Shows**: Catch up with the latest episodes from your favorite TV series.
- **Search Functionality**: Easily find movies and TV shows
- **Multi-language Support** Users can enjoy the app interface, movie descriptions, and other content in the following languages: Italian (IT), English (EN), Portuguese (PT), Spanish (ES)
- **Dark/Light Mode**: Choose between dark and light themes for a comfortable viewing experience.
- **MultiPlatform Support**: Enjoy the app on android and desktop (ios in progress)

### Data Source

The app's movie and TV show data are sourced from The Movie Database (TMDb), a popular, user-editable database for movies and TV shows. The integration with TMDb's API allows NGtv to provide up-to-date information, including ratings, descriptions, and multimedia elements like posters and trailers.

Endpoints used:
- /movie/top_rated endpoint to retrieve a list of top-rated movies.
- /movie/popular endpoint to get a list of popular movies.
- /movie/upcoming endpoint for a list of upcoming movies.
- /tv/latest endpoint to find the latest TV shows.
- /search/multi endpoint to perform searches across movies and TV shows
- /movie/{movie_id} and /tv/{tv_id} endpoints to get detailed information about a specific movie or TV show.
- /genre/movie/list 

## Tech Stack

The app is built using a stack:

- Kotlin Multiplatform.
- Jetpack Compose .
- Ktor for network requests.
- Coil for image loading.
- Koin for dependency injection.
- Molecule for state management.
- Maestro for UI testing.
- Material 3


## Screenshots and Videos
 
[Assets](https://github.com/diegocp/DIEGOCP_NG_AND_24/tree/master/assets) directory contains screenshots and videos of the app.

### Android 
<p align="center">
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/home_light.webp" width="25%" />
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/home_dark.webp" width="25%" />
</p>

### iOS
<p align="center">
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/ios-home.webp" width="25%" />
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/ios-detail.webp" width="25%" />
</p>

### Foldable / Desktop  
<p align="center">
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/foldable-light.png" width="25%" />
  <img src="https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/demo-desktop.webp" width="25%" />
</p>

## Building the Project 

To build the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio Koala or later.
3. Before building the project, you need to set your MovieDB API key. 
   Open the `local.properties` file in the root directory of the project and add the api key
   API_KEY=<MOVIEDB_KEY>
   GEMINI_API_KEY=none
4. Optional: To enable the Gemini search (What to Watch feature), you need to change `local.properties` 
    from: GEMINI_API_KEY=none
    to:  GEMINI_API_KEY=<YOUR_GEMINI_KEY>

- Maestro for UI testing.
    - Maestro/Flow.yaml contains the test plan for the app. [Demo available here](https://github.com/diegocp/DIEGOCP_NG_AND_24/blob/master/assets/maestro-demo.mov)
    - See more about Maestro and how to run tests in the [Maestro documentation](https://maestro.mobile.dev/).

* check support for KMP using kdoctor
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),

## Extra feature - What to watch? 
Experimental feature that uses Google Gemini api to recommend movies and tv shows based on user prompt 
To enable this feature, you need to set your Google Gemini API key in the `local.properties` file in the root directory of the project.



