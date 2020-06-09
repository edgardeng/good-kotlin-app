package com.edgar.movie.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.edgar.movie.AppActivity
import com.edgar.movie.R
import com.edgar.movie.databinding.ActivitySettingBinding
import com.edgar.movie.util.ThemeHelper

class SettingActivity : AppActivity() {
    //    private val viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        when(PreferencesUtil.getDarkMode(this)) {
            ThemeHelper.DEFAULT -> binding.darkMode = "跟随系统"
            ThemeHelper.DARK -> binding.darkMode = "开启"
            ThemeHelper.LIGHT -> binding.darkMode = "关闭"
        }
        addToolBarBack()
    }

    fun gotoSettingsTheme(v: View) {
        startActivity(Intent(this, SettingThemeActivity::class.java))
    }

}