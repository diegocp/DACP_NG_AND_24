package com.ngtv.di

import org.koin.core.module.Module
import java.util.Locale

actual fun ktorEngineModule(): Module {
  return org.koin.dsl.module {

  }
}

actual val language: String
  get() = Locale.getDefault().language