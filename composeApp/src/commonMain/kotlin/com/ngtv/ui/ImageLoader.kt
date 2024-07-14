package com.ngtv.ui


import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun getAsyncImageLoader(context: PlatformContext): ImageLoader =
  ImageLoader.Builder(context)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
      MemoryCache.Builder()
//        .maxSizePercent(context, 0.3)
        .strongReferencesEnabled(true)
        .build()
    }
    .diskCachePolicy(CachePolicy.ENABLED)
    .networkCachePolicy(CachePolicy.ENABLED)
    .diskCache { newDiskCache() }
    .crossfade(true)
    .logger(DebugLogger())
    .build()

fun newDiskCache(): DiskCache {
  return DiskCache.Builder()
    .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
    .maxSizeBytes(64L * 1024 * 1024) // 64MB
    .build()
}