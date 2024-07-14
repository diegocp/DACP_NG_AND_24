package com.ngtv.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ngtv.data.domain.MovieDetails

@Composable
fun ReviewsSection(movieDetail: MovieDetails) {
  Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    movieDetail.reviews?.results?.forEach { review ->
      var expanded by remember { mutableStateOf(false) }
      val degrees by animateFloatAsState(if (expanded) -90f else 90f)
      Column {
        Row(
          modifier = Modifier
            .clickable { expanded = expanded.not() }.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          BasicText(
            "${review.content}" ?: "",
            maxLines = 2
          )
          Image(
            imageVector = rememberChevronRight(),
            contentDescription = null,
            modifier = Modifier.rotate(degrees),
            colorFilter = ColorFilter.tint(Color.LightGray)
          )
        }
        AnimatedVisibility(
          visible = expanded,
          enter = expandVertically(
            spring(
              stiffness = Spring.StiffnessMediumLow,
              visibilityThreshold = IntSize.VisibilityThreshold
            )
          ),
          exit = shrinkVertically()
        ) {
          Box(Modifier.fillMaxWidth().padding(16.dp)) {
            BasicText(review.content ?: "")
          }
        }
        Box(Modifier.fillMaxWidth().height(1.dp))
      }
    }
  }
}



@Composable
fun rememberChevronRight(): ImageVector {
  return remember {
    ImageVector.Builder(
      name = "ChevronRight",
      defaultWidth = 24.dp,
      defaultHeight = 24.dp,
      viewportWidth = 24f,
      viewportHeight = 24f
    ).apply {
      path(
        fill = null,
        fillAlpha = 1.0f,
        stroke = SolidColor(Color(0xFF000000)),
        strokeAlpha = 1.0f,
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        strokeLineMiter = 1.0f,
        pathFillType = PathFillType.NonZero
      ) {
        moveTo(9f, 18f)
        lineToRelative(6f, -6f)
        lineToRelative(-6f, -6f)
      }
    }.build()
  }
}