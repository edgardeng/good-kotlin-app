package com.edgar.movie.demo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DemoActivity : AppCompatActivity() {
    val TAG = "DemoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.edgar.movie.R.layout.activity_demo_main)
        Log.e(TAG, "...onCreate")
    }
    override fun onNightModeChanged(mode: Int) {
        Log.e(TAG, "--- onNightModeChanged: $mode")
        super.onNightModeChanged(mode)
    }

    fun gotoBaseWidget(view: View) {
        goTo(DemoFormWidgetActivity::class.java)
    }
    fun gotoContainerWidget(view: View) {
        goTo(DemoContainerWidgetActivity::class.java)
    }

    fun gotoDialog(view: View) {
        goTo( DemoDialogActivity::class.java)
    }

    fun gotoThemeSwitch(view: View) {
        goTo(DemoThemeActivity::class.java)
    }

    fun gotoViewPager(view: View) {
        goTo(DemoViewPagerActivity::class.java)
    }

    fun goTo(cls: Class<*>) {
        Log.e(TAG, "--- goTo: ${cls.name}")
        startActivity(Intent(this, cls))
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

}