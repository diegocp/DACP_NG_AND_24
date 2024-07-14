package com.ngtv.ui.home

import NGtv.composeApp.BuildConfig
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.ngtv.data.domain.Movie
import com.ngtv.ui.util.Headline
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.latest_tv
import ngtv.composeapp.generated.resources.popular
import ngtv.composeapp.generated.resources.top_rated
import ngtv.composeapp.generated.resources.upcoming
import ngtv.composeapp.generated.resources.whattowatch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  topRated: List<Movie>,
  popular: List<Movie>,
  latestTv: List<Movie>,
  upcoming: List<Movie>,
  onItemClicked: (String, String?) -> Unit,
  onSearchIconClicked: (() -> Unit)? = null,
  onWhatToWatch: (() -> Unit)? = null
) {
  val windowSizeClass = calculateWindowSizeClass()
  val columns = when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> 1
    WindowWidthSizeClass.Medium -> 2
    WindowWidthSizeClass.Expanded -> 4
    else -> 4
  }

  val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
    rememberTopAppBarState()
  )

  Scaffold(
    modifier = Modifier
      .fillMaxWidth()
      .nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      TopBarHome(
        scrollBehavior = scrollBehavior,
        onLogoClick = null,
        onIconClick = {
          onSearchIconClicked?.invoke()
        }
      )
    }
  ) {

    LazyVerticalGrid(
      modifier = Modifier.fillMaxWidth(),
      columns = GridCells.Fixed(columns),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      contentPadding = it
    ) {

      item(span = { GridItemSpan(columns) }) {
        Headline(title = stringResource(Res.string.top_rated)) {
          CoverList(
            modifier = Modifier.fillMaxWidth(),
            covers = topRated,
            onItemClicked = onItemClicked
          )
        }
      }

      if (BuildConfig.GEMINI_API_KEY != "none") {
        item(span = { GridItemSpan(columns) }) {
          Headline(title = stringResource(Res.string.whattowatch)) {
            OutlinedCard(modifier = Modifier.padding(16.dp)) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Click and find out how we can help you to find the best movie or tv show",
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
              ) {
                Button(
                  modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
                  onClick = {
                    onWhatToWatch?.invoke()
                  }
                ) {
                  Text("Discover here")
                }
              }
            }
          }
        }
      }

      item(span = { GridItemSpan(columns) }) {
        Headline(title = stringResource(Res.string.popular)) {
          BannerList(popular, onItemClicked)
        }
      }

      item(span = { GridItemSpan(columns) }) {
        Headline(title = stringResource(Res.string.latest_tv)) {
          CoverList(
            modifier = Modifier.fillMaxWidth(),
            covers = latestTv,
            defaultMediaType = "tv",
            onItemClicked = onItemClicked
          )
        }
      }

      item(span = { GridItemSpan(columns) }) {
        Headline(title = stringResource(Res.string.upcoming)) {}
      }

      items(upcoming) { item ->
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
          GridItem(item, onItemClicked)
        }
      }
    }
  }
}