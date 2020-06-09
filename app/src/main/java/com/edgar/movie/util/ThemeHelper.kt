package com.edgar.movie.util

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat


class ThemeHelper {

    fun getDefaultNightMode(): Int {
        val model = AppCompatDelegate.getDefaultNightMode()
        Log.e("ThemeHelper: ", "getDefaultNightMode: $model")
        return model
    }

    companion object {
        const val DEFAULT = "default"
        const val  LIGHT = "light"
        const val  DARK = "dark"

        fun applyTheme(theme: String?) {
            Log.e("ThemeHelper: ", "--- applyTheme ---")
            when (theme) {
                LIGHT -> {
                    Log.e("ThemeHelper: ", "set MODE_NIGHT_NO")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                DARK -> {
                    Log.e("ThemeHelper: ", "set MODE_NIGHT_YES")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else -> {
                    // 跟随 系统设置（系统深色模式，则深色模式；系统浅色模式，则浅色模式）
                    if (BuildCompat.isAtLeastQ()) {
                        Log.e("ThemeHelper: ", "set MODE_NIGHT_FOLLOW_SYSTEM")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    } else {
                        Log.e("ThemeHelper: ", "set MODE_NIGHT_AUTO_BATTERY")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                    }
                }
            }
            Log.e("ThemeHelper: ", "--- \r\n--- \n")
        }
    }

}