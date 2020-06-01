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

//        val recyclerView = RecyclerView(this)
//
////            findViewById<View>(R.id.recycler_view) as RecyclerView
//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//        val adapter = RecyclerView.Adapter（）
//        val adapter = FruitAdapter(fruitList)
//        recyclerView.adapter = adapter
////        setContentView(R.layout.activity_main)
////        setSupportActionBar(toolbar)
//
//        var lv = ListView(this)
//        lv.s
//        setContentView(lv)


    }

    fun gotoBaseWidget(view: View) {
        var i = Intent(this, DemoFormWidgetActivity::class.java)
        startActivity(i)
    }


}