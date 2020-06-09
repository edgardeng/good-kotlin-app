package com.edgar.movie

import android.app.Application
import android.util.Log
import com.edgar.movie.util.ThemeHelper

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("MainApplication", "...onCreate")
        val darkMode = PreferencesUtil.getDarkMode(applicationContext)
        ThemeHelper.applyTheme(darkMode)
        Log.e("MainApplication", "darkMode: $darkMode")
    }

    companion object {
         val instance = MainApplication()
    }
}