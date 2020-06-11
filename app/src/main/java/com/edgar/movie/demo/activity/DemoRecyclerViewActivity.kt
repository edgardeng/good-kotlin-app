package com.edgar.movie.demo.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.edgar.movie.R


// 	demo for androidx.viewpager2.widget.ViewPager2

class DemoRecyclerViewActivity : AppCompatActivity() {

    private lateinit var currentLayoutManagerType: LayoutManagerType
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dataset: MutableList<String>
    private lateinit var recyclerViewAdapter: CustomRecyclerViewAdapter
    private var lastVisibleItem = 0


    enum class LayoutManagerType { GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER }

    private val TAG = "DemoRecyclerView"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity_recycler_view)
        initView(savedInstanceState)
    }

    fun initView(savedInstanceState: Bundle?) {
        dataset = MutableList( DATASET_COUNT, { i -> "Element # $i" })
        recyclerViewAdapter = CustomRecyclerViewAdapter(dataset)
        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        with(recyclerView) {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager
            // specify an viewAdapter (see also next example)
            adapter = recyclerViewAdapter
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.e(TAG, "onScrollStateChanged")
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Log.e(TAG, "onScrollStateChanged -- SCROLL_STATE_IDLE")
                        if (lastVisibleItem + 1 == recyclerViewAdapter.getItemCount()) {
                            Log.e(TAG, "onScrollStateChanged -- to Last Item")
                            Handler().postDelayed({
                                doWhenLoad()
                            }, 2000)
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (recyclerView.layoutManager is LinearLayoutManager) {
                        lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    } else if (recyclerView.layoutManager is GridLayoutManager) {
                        lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    }
                    Log.e(TAG, "onScrolled -- $lastVisibleItem")
                }
            })
        }


        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER

        findViewById<RadioButton>(R.id.linear_layout_rb).setOnClickListener {
            setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER)
        }
        findViewById<RadioButton>(R.id.grid_layout_rb).setOnClickListener {
            setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER)
        }
        if (savedInstanceState != null) {
            currentLayoutManagerType = savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER) as LayoutManagerType
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType)

        swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener { doWhenRefresh() }
        doWhenRefresh()
    }
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    fun doWhenRefresh() {
        Log.e(TAG, "doWhenRefresh")
        recyclerViewAdapter.resetDataset()
        val size = dataset.size
        recyclerViewAdapter.addDataSet(MutableList( DATASET_COUNT, { i -> "Element # ${size + i}" }), size >=60)
        swipeRefreshLayout.isRefreshing = false;
    }

    fun doWhenLoad() {
        Log.e(TAG, "doWhenLoad")
        val size = dataset.size
        recyclerViewAdapter.addDataSet(MutableList( DATASET_COUNT, { i -> "Element # ${size + i}" }), size >=60)
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    private fun setRecyclerViewLayoutManager(layoutManagerType: LayoutManagerType) {
        when (layoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
            }
            else -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }
        with(recyclerView) {
            scrollToPosition(0)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, this.currentLayoutManagerType)
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        private val TAG = "RecyclerViewFragment"
        private val KEY_LAYOUT_MANAGER = "layoutManager"
        private val SPAN_COUNT = 2
        private val DATASET_COUNT = 20
    }

    /**
     * Provide views to RecyclerView with data from dataSet.
     *
     */
    class CustomRecyclerViewAdapter(private val dataSet: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var more = false // 变量，是否有更多数据
        private var size = 0;

        class NoramalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView: TextView
            init {
                v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
                textView = v.findViewById(android.R.id.text1)
            }
        }

        class FootViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            if (viewType == normalType) {
                val v = LayoutInflater.from(viewGroup.context).inflate(android.R.layout.simple_list_item_1, viewGroup, false)
                return NoramalViewHolder(v)
            } else {
                val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.v_demo_load_more, viewGroup, false)
                return FootViewHolder(v)
            }
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            if (position < size) {
                (viewHolder as NoramalViewHolder).textView.text = dataSet[position]
            } else {
                // TODO 绑定底部加载视图
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = if (more) size + 1 else size

        // 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
        fun resetDataset() {
            dataSet.clear()
        }

        // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
        fun addDataSet( newDatas: List<String>?, m: Boolean ) {
            if (newDatas != null) {
                dataSet.addAll(newDatas)
            }
            size = dataSet.size
            more = m
            notifyDataSetChanged()
        }

        private val normalType = 0 // 第一种ViewType，正常的item
        private val footType = 1 // 第二种ViewType，底部的提示View

        // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
        override fun getItemViewType(position: Int): Int {
            return if (position < size) normalType else footType
        }
    }
}
