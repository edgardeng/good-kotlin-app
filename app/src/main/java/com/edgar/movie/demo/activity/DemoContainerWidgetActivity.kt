package com.edgar.movie.demo.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.edgar.movie.R


class DemoContainerWidgetActivity : AppCompatActivity() {
    val TAG = "DemoContainerWidget"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_container_widget)
        aSpinner()
        aGridView()
        aTabHost()
        aListView()
    }

    fun aListView() {
        val listview = findViewById<ListView>(R.id.demo_listview)
        val fruits= listOf("第一个是列表", "Apple", "Orange", "Pear", "Peach")
        val adapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,fruits)
        listview.adapter = adapter;
        listview.setOnItemClickListener { adapterView, view, postion, var4 -> Log.e(TAG, "ListView OnItemClick: $postion") }
    }

    fun aTabHost() {
        val tab = findViewById<TabHost>(R.id.demo_tabhost)
        //初始化TabHost容器
        tab.setup()
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("tab1").setIndicator("未接来电", null).setContent(R.id.tab1))
        tab.addTab(tab.newTabSpec("tab2").setIndicator("已接来电", null).setContent(R.id.tab2))
        tab.addTab(tab.newTabSpec("tab3").setIndicator("呼出通话", null).setContent(R.id.tab3))
        tab.setOnTabChangedListener { var1 -> Log.e(TAG, "TabHost OnTabChanged: $var1") }
    }

    fun aSpinner() {
        val spinner = findViewById<Spinner>(R.id.demo_spinner)
        val languages= listOf("java", "c++", "kotlin", "swift")

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, languages) // 未展开菜单时Spinner的默认样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // 下拉菜单的样式
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, pos: Int, id: Long
            ) {
                val languages= listOf("java", "c++", "kotlin", "swift")
                Log.e(TAG, "onItemSelected 你点击的是:" + languages[pos])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e(TAG, "onNothingSelected")
            }
        }
        spinner.setSelection(2)
    }

    fun aGridView() {
        val grid_view = findViewById<GridView>(R.id.demo_gridview)
        val numbers= listOf("一", "二", "三", "四","五","六")
//        val adapter =
//            ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
        grid_view.adapter = SimpleGrdiViewAdapter(this, numbers)
        grid_view.numColumns = 3
        // 注册单元格的点击事件
        grid_view.setOnItemClickListener { parent, view, position, id ->
            Log.e(TAG, "GridView setOnItemClickListener 你点击的是:" + numbers[position])
        }
    }


    class SimpleGrdiViewAdapter(val context: Context, val objects: List<String>): BaseAdapter() {
        // 单元格的 View
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val gridView: View

            if (convertView == null) {
                gridView = inflater.inflate(android.R.layout.simple_spinner_item, null)
                val textView = gridView.findViewById<TextView>(android.R.id.text1)
                textView.maxWidth = 200
                textView.setText(objects[position])
                textView.setTextColor(when (position) {
                    0 -> Color.LTGRAY
                    1 -> Color.BLUE
                    2 -> Color.YELLOW
                    else -> Color.RED
                })
            } else {
                gridView = convertView
            }

            return gridView
        }

        // 要渲染的单元格数量
        override fun getCount(): Int {
            return objects.size
        }

        // 在这个示例中不用，Android 要求实现此方法
        override fun getItem(position: Int): Any? {
            return null
        }

        // 在这个示例中不用，Android 要求实现此方法
        override fun getItemId(position: Int): Long {
            return 0
        }
    }


}