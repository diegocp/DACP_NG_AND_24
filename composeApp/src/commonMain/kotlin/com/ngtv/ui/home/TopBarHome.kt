package com.ngtv.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.ic_search
import ngtv.composeapp.generated.resources.ic_search_night
import ngtv.composeapp.generated.resources.ngtv
import ngtv.composeapp.generated.resources.ngtv_night
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
  scrollBehavior: TopAppBarScrollBehavior,
  onLogoClick: (() -> Unit)? = null,
  onIconClick: (() -> Unit)? = null,
) {

  TopAppBar(
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      scrolledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
      titleContentColor = MaterialTheme.colorScheme.secondary,
    ),
    windowInsets = WindowInsets(
      top = 8.dp,
      bottom = 0.dp,
      left = 0.dp,
      right = 0.dp
    ),
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
      ) {
        val clickToTop = remember {
          Modifier.clickable { onLogoClick?.invoke() }
        }
        Image(
          modifier = Modifier.then(clickToTop),
          painter =
          if (isSystemInDarkTheme())
            painterResource(Res.drawable.ngtv_night)
          else
            painterResource(Res.drawable.ngtv),
          contentDescription = "Search"
        )
      }
    },
    actions = {
      IconButton(
        onClick = { onIconClick?.invoke() },

        ) {
        Image(
          painter =
          if (isSystemInDarkTheme())
            painterResource(Res.drawable.ic_search_night)
          else
            painterResource(Res.drawable.ic_search),
          contentDescription = "Search"
        )
      }
    },
    scrollBehavior = scrollBehavior
  )
}