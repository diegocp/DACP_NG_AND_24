package com.ngtv.di

import org.koin.dsl.module

fun initIosCore() = initKoin {
  println("-- initIosCore")
  modules (
    viewModelsModule,
    module {
      // add your iOS specific dependencies here
    }
  )
}