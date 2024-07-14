package com.ngtv.ui.util

import kotlin.test.Test
import kotlin.test.assertTrue

class ExtensionsUtilKtTest {

  @Test
  fun calculateRatingShouldReturnCorrect() {
    assertTrue(7.841f.calculateRating(5) == 3.9205003f)
  }

  @Test
  fun calculateRatingShouldReturnZeroWhenParamZero() {
    assertTrue(0f.calculateRating(5) == 0f)
  }

}