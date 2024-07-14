package com.ngtv.ui.search

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.back
import ngtv.composeapp.generated.resources.clear
import ngtv.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
fun SearchBarLayout(
  onQueryChange: (String) -> Unit,
  isSearchActive: Boolean,
  onActiveChanged: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  onSearch: ((String) -> Unit)? = null,
  onBackPressed: () -> Unit
) {
  var searchQuery by rememberSaveable { mutableStateOf("") }
  val activeChanged: (Boolean) -> Unit = { active ->
    searchQuery = ""
    onQueryChange("")
    onActiveChanged(active)
  }
  SearchBar(
    query = searchQuery,
    onQueryChange = { query ->
      searchQuery = query
      onQueryChange(query)
    },
    onSearch = onSearch ?: { activeChanged(false) },
    active = isSearchActive,
    onActiveChange = activeChanged,
    modifier = if (isSearchActive) {
      modifier
        .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
    } else {
      modifier
        .padding(start = 8.dp, top = 2.dp, end = 12.dp, bottom = 12.dp)
        .fillMaxWidth()
        .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
    },
    placeholder = { Text(stringResource(Res.string.search)) },
    leadingIcon = {
      if (isSearchActive) {
        IconButton(
          onClick = {
            onBackPressed.invoke()
          },
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(Res.string.back),
            tint = MaterialTheme.colorScheme.primary,
          )
        }
      } else {
        Icon(
          imageVector = Icons.Rounded.Search,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    },
    trailingIcon = if (isSearchActive && searchQuery.isNotEmpty()) {
      {
        IconButton(
          onClick = {
            searchQuery = ""
            onQueryChange("")
          },
        ) {
          Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = stringResource(Res.string.clear),
            tint = MaterialTheme.colorScheme.primary,
          )
        }
      }
    } else {
      null
    },
    colors = SearchBarDefaults.colors(
      containerColor = if (isSearchActive) {
        MaterialTheme.colorScheme.background
      } else {
        MaterialTheme.colorScheme.surfaceContainerLow
      },
    ),
    tonalElevation = 0.dp,
    windowInsets = if (isSearchActive) {
      SearchBarDefaults.windowInsets
    } else {
      WindowInsets(0.dp)
    }
  ) {
  }
}
