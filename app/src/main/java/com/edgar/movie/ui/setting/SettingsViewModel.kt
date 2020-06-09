package com.edgar.movie.ui.setting

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgar.movie.util.ThemeHelper

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "跟随系统"
    }
    val darkMode: LiveData<String> = _text

    fun setDarkMode (context: Context) {
        when(PreferencesUtil.getDarkMode(context)) {
            ThemeHelper.DEFAULT -> _text.value = "跟随系统"
            ThemeHelper.DARK -> _text.value = "开启"
            ThemeHelper.LIGHT -> _text.value = "关闭"
        }
    }

}