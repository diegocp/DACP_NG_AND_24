package com.ngtv.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


@Composable
fun RatingBar(rating: Float, maxRating: Int) {
  val firstHalf = object : Shape {
    override fun createOutline(
      size: Size,
      layoutDirection: LayoutDirection,
      density: Density
    ): Outline {
      return Outline.Rectangle(Rect(0f, 0f, size.width / 2, size.height))
    }
  }

  Row {
    repeat(maxRating) { i ->
      Box(Modifier.size(18.dp)) {
        val lastFullIndex = (rating - 1).toInt()
        Icon(
          imageVector = Icons.Rounded.Star,
          contentDescription = null,
          tint = emptyColor,
          modifier = Modifier.matchParentSize()
        )
        when {
          i <= lastFullIndex -> {
            Icon(
              imageVector = Icons.Rounded.Star,
              contentDescription = null,
              tint = fillColor,
              modifier = Modifier.matchParentSize()
            )
          }

          i == lastFullIndex + 1 -> {
            Icon(
              imageVector = Icons.Rounded.Star,
              contentDescription = null,
              tint = fillColor,
              modifier = Modifier.matchParentSize().clip(firstHalf)
            )
          }
        }
      }
    }
  }
}