package com.edgar.movie.demo.activity

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.edgar.movie.R

// 主题切换demo

class DemoThemeActivity : AppCompatActivity() {

    val TAG = "DemoThemeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_theme)
        initSystemConfig() // 初始化系统配置数据
        initUI() // 初始化UI
        updateThemeInfo() // 展示主题相关配置信息
    }
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "...onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "...onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "...onStop")
    }

    override fun finish() {
        super.finish()
        Log.e(TAG, "...finish")
    }

    /**
     * Android系统设置中 "设置-显示-深色主题背景" 切换后，回调该方法
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged: $newConfig.uiMode")
        mSysThemeConfig = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (mSysThemeConfig) {
            // 亮色主题
            Configuration.UI_MODE_NIGHT_NO -> Log.e(TAG, "mSysThemeConfig changed to UI_MODE_NIGHT_NO : $mSysThemeConfig")
            // 深色主题
            Configuration.UI_MODE_NIGHT_YES -> Log.e(TAG, "mSysThemeConfig changed to UI_MODE_NIGHT_YES : $mSysThemeConfig")
            else ->  Log.e(TAG, "mSysThemeConfig changed to ? : $mSysThemeConfig")
        }
        Log.e(TAG, "- onConfigurationChanged end\r\n-\n-\n-\n")
    }

    // UI
    private var mThemeTv: TextView? = null
    private var mChangeTv: Button? = null

    /** 数据 */
    // 当前 Android系统主题 配置
    // (举例 Configuration.UI_MODE_NIGHT_NO Configuration.UI_MODE_NIGHT_YES)
    private var mSysThemeConfig = -1
    // 当前 APP主题 设置
    private var mAppThemeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    var selected = 0

    /**
     * 回调当前应用的使用主题
     */
    override fun onNightModeChanged(mode: Int) {
        Log.e(TAG, "--- onNightModeChanged: $mode")
        super.onNightModeChanged(mode)
        mAppThemeMode = mode
        when (mAppThemeMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> Log.e(TAG, "onNightModeChanged MODE_NIGHT_NO")
            AppCompatDelegate.MODE_NIGHT_YES -> Log.e(TAG, "onNightModeChanged MODE_NIGHT_YES")
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> Log.e( TAG, "onNightModeChanged MODE_NIGHT_AUTO_BATTERY")
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> Log.e(  TAG, " onNightModeChanged MODE_NIGHT_FOLLOW_SYSTEM")
        }
        Log.e(TAG, "-\r\n-\n-\n-\n")
        updateThemeInfo() // 更新主题配置信息
    }


    /**
     * 当前 Android系统主题 配置
     * (举例 Configuration.UI_MODE_NIGHT_NO Configuration.UI_MODE_NIGHT_YES)
     */
    fun initSystemConfig() {
        val configuration = resources.configuration
        mSysThemeConfig = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        Log.e(TAG, ">> mSysThemeConfig inited: $mSysThemeConfig")
    }


    /**
     * UI
     */
    fun initUI() {
        mThemeTv = findViewById(R.id.theme_tv)
        mChangeTv = findViewById(R.id.change_tv)
        mChangeTv?.setOnClickListener() {
//            val themes = resources.getStringArray(R.array.themeEntryArray)
//            with(AlertDialog.Builder(this)) {
//                setTitle("选择性别")
//                setSingleChoiceItems(
//                    themes,
//                    selected,
//                    DialogInterface.OnClickListener { dialog, which ->
//                        Log.e(TAG, "你选择的是:" + themes[which])
//                        ThemeHelper.applyTheme(themes[which])
//                        selected = which
//                        Log.e(TAG, "-\r\n- wait recreate- \n-\n-\n")
//                        recreate()
//                    })
//                create()
//            }.show()
            toggleTheme()
        }
    }

    /**
     * 展示当前主题配置信息
     */
    fun updateThemeInfo() {
        // 新建StringBuffer
        val sb = StringBuffer()
        when (mSysThemeConfig) {
            Configuration.UI_MODE_NIGHT_NO -> {
                sb.append("Android系统主题配置：")
                sb.append("亮色主题")
                sb.append("\n")
                sb.append("\n")
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                sb.append("Android系统主题配置：")
                sb.append("深色主题")
                sb.append("\n")
                sb.append("\n")
            }
        }
        when (mAppThemeMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                sb.append("当前APP主题设置：")
                sb.append("亮色主题")
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                sb.append("当前APP主题设置：")
                sb.append("深色主题")
            }
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> {
                sb.append("当前APP主题设置：")
                sb.append("\n")
                sb.append("省电模式时 深色模式")
                sb.append("\n")
                sb.append("非省点模式时 亮色模式")
            }
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                sb.append("当前APP主题设置：")
                sb.append("跟随系统")
                sb.append("\n")
                sb.append("系统深色模式，则深色模式；系统亮色模式，则亮色模式")
            }
        }
        // 显示当前主题信息
        if (mThemeTv != null) {
            mThemeTv!!.text = sb.toString()
        }
    }

    /**
     * 根据系统配置 切换主题
     */
//    fun toggleThemeBySystemConfig() {
//        when (mSysThemeConfig) {
//            Configuration.UI_MODE_NIGHT_NO -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
//            Configuration.UI_MODE_NIGHT_YES -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
//        }
//        Log.e(TAG, "-\r\n- wait recreate- \n-\n-\n")
//        recreate() // 应用主题
//    }


    /**
     * 手动 切换 并应用主题
     */
    fun toggleTheme() {
        // 当前App 亮色
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            Log.e(TAG, "toggleTheme When DefaultNightMode  = AppCompatDelegate.MODE_NIGHT_NO")
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // 亮色
            Log.e(TAG, "toggleTheme When DefaultNightMode  = AppCompatDelegate.MODE_NIGHT_YES")
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
             // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            when (mSysThemeConfig) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    Log.e(TAG, "toggleTheme When DefaultNightMode  = Configuration.UI_MODE_NIGHT_NO")

                    delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                }

                Configuration.UI_MODE_NIGHT_YES ->{
                    Log.e(TAG, "toggleTheme When DefaultNightMode  = Configuration.UI_MODE_NIGHT_NO")
                    delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                }
            }
        }
        // 应用主题
        Log.e(TAG, "-\r\n- wait recreate- \n-\n-\n")
        recreate()
    }



}