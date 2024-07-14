package com.ngtv.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ngtv.data.domain.MovieDetails
import com.ngtv.ui.IMAGE_BASE_URL
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.more_info
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoreInfoDetail(movieDetail: MovieDetails) {
  Column(Modifier.padding(vertical = 4.dp)) {
    Text(
      text = stringResource(Res.string.more_info),
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(horizontal = 16.dp)
    )
    movieDetail.watchProviders?.results?.ie?.rent?.forEach {
      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
          AsyncImage(
            model = "$IMAGE_BASE_URL${it.logoPath}",
            contentDescription = null,
            modifier = Modifier
              .clip(MaterialTheme.shapes.medium).width(40.dp),
            contentScale = ContentScale.Crop
          )
          Text(
            modifier = Modifier.padding(8.dp),
            text = it.providerName ?: "",
          )
        }
      }
    }

    movieDetail.networks?.forEach {
      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
          AsyncImage(
            model = "$IMAGE_BASE_URL${it.logoPath}",
            contentDescription = null,
            modifier = Modifier
              .clip(MaterialTheme.shapes.medium).width(40.dp)
          )
          Text(
            modifier = Modifier.padding(8.dp),
            text = it.name ?: "",
          )
        }
      }
    }

    movieDetail.homepage?.let {
      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.clickable {
        /* TODO link to website */
        }.fillMaxWidth().padding(16.dp)
      ) {
        Icon(
          imageVector = Icons.Outlined.Home,
          contentDescription = "Website",
          tint = MaterialTheme.colorScheme.primary
        )
        Text(it)
      }
    }

    movieDetail.spokenLanguages?.map { it.name }?.joinToString(",")?.let {
      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
      ) {
        Icon(
          imageVector = Icons.Outlined.Info,
          contentDescription = "spoken languages",
          tint = MaterialTheme.colorScheme.primary
        )
        Text(it)
      }
    }
  }

}