package com.edgar.movie.ui.setting

import android.os.Bundle
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.edgar.movie.AppActivity
import com.edgar.movie.R
import com.edgar.movie.databinding.ActivitySettingThemeBinding
import com.edgar.movie.util.ThemeHelper

class SettingThemeActivity : AppActivity() {

    var darkMode: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySettingThemeBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting_theme)
        addToolBarBack()
        darkMode = PreferencesUtil.getDarkMode(this).toString()
        binding.rgDarkMode.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, viewId: Int) {
                 // Log.e("SettingThemeActivity", "onCheckedChanged ${group?.checkedRadioButtonId} - $viewId")

                when (viewId) {
                    R.id.rb_dark_mode_default -> changeDarkMode(ThemeHelper.DEFAULT)
                    R.id.rb_dark_mode_yes -> changeDarkMode(ThemeHelper.DARK)
                    R.id.rb_dark_mode_no -> changeDarkMode(ThemeHelper.LIGHT)
                }
//                recreate()
            }

        })
        when(darkMode) {
            ThemeHelper.DEFAULT -> binding.rbDarkModeDefault.isChecked = true
            ThemeHelper.DARK -> binding.rbDarkModeYes.isChecked = true
            ThemeHelper.LIGHT -> binding.rbDarkModeNo.isChecked = true
        }
    }

    fun changeDarkMode( mode:String) {
        // Log.e("SettingThemeActivity", "onCheckedChang to $mode")
        if (!darkMode.equals(mode)) {
            darkMode = mode
            PreferencesUtil.setDarkMode(mode, this@SettingThemeActivity)
            ThemeHelper.applyTheme(mode)
        }
    }
}