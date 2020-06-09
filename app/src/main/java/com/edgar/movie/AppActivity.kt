package com.edgar.movie

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class AppActivity : AppCompatActivity() {
    private val TAG = "AppActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "${javaClass.name}...onCreate")
    }
    override fun onNightModeChanged(mode: Int) {
        Log.e(TAG, "${javaClass.name}--- onNightModeChanged: $mode")
        super.onNightModeChanged(mode)
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "${javaClass.name}...onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "${javaClass.name}...onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "${javaClass.name}...onStop")
    }

    override fun finish() {
        super.finish()
        Log.e(TAG, "${javaClass.name}...finish")
    }


    fun onBack(v: View) {
        finish()
    }

    fun addToolBarBack() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view -> onBack(view) }
    }

    fun setStatusBarTransparent() {
        val window: Window = window
        val decorView: View = window.decorView
        val option: Int = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        decorView.systemUiVisibility = option
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
