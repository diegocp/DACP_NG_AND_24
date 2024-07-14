package com.ngtv.di

import android.content.Context
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale

fun initKoin(context: Context) = initKoin {
  androidContext(context)
}

actual fun ktorEngineModule() = module {
  single { Android.create() }
}

actual val language: String
  get() = Locale.getDefault().language