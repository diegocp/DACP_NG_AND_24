package com.ngtv

import android.app.Application
import com.ngtv.di.initKoin

class MainApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    initKoin(this)
  }
}