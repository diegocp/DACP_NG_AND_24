package com.ngtv.ui.util

fun Float.calculateRating(maxRating: Int) : Float {
  return (this * 10 / 100) * maxRating
}