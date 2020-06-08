package com.edgar.movie.demo.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.edgar.movie.R.layout.activity_demo_main)

    }

    fun gotoBaseWidget(view: View) {
        var i = Intent(this, DemoFormWidgetActivity::class.java)
        startActivity(i)
    }
    fun gotoContainerWidget(view: View) {
        var i = Intent(this, DemoContainerWidgetActivity::class.java)
        startActivity(i)
    }

    fun gotoDialog(view: View) {
        var i = Intent(this, DemoDialogActivity::class.java)
        startActivity(i)
    }


}