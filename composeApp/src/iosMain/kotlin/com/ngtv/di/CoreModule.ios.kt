package com.ngtv.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun ktorEngineModule(): Module = module {
  single { Darwin.create() }
}

actual val language: String
  get() = NSLocale.currentLocale.languageCode