package com.edgar.movie.demo.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.edgar.movie.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// 	demo for androidx.viewpager2.widget.ViewPager2

class DemoViewPagerActivity : AppCompatActivity() {
    private val TAG = "DemoViewPagerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_viewpager)
        aViewPage()
    }

    fun aViewPage() {
        var vpBase = findViewById<ViewPager2>(R.id.demo_view_pager)
        var datas:List<String> = listOf("第一页", "第二页", "第三页")
        val baseAdapter = BaseAdapter(datas)
        with(vpBase) {
            adapter = baseAdapter
            isUserInputEnabled = true // 禁止滚动true为可以滑动false为禁止
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 设置垂直滚动ORIENTATION_VERTICAL
            setCurrentItem(1, true) //切换到指定页，是否展示过渡中间页
            setPageTransformer(mAnimator)

//            setIndicatorStyle(Int)
            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    Log.e(TAG, "onPageScrolled: $position--->$positionOffset--->$positionOffsetPixels"
                    )
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e(TAG, "onPageSelected: $position")
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e(TAG, "onPageScrollStateChanged: $state")
                }
            }) // 监听滑动
        }

        // 使用 ttabLayoutab
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, vpBase) { tab, position ->
            tab.text = "PAGE ${(position + 1)}"
        }.attach()

    }

    var mAnimator =
        ViewPager2.PageTransformer { page, position ->
            val absPos = Math.abs(position)
            val scaleX: Float
            val scaleY: Float
            if (absPos > 1) {
                scaleX = 0f
                scaleY = 0f
            } else {
                scaleX = 1 - absPos
                scaleY = 1 - absPos
            }
            page.scaleX = scaleX
            page.scaleY = scaleY
        }
    /**
     * 基本使用 - ViewPage 适配器
     * Adapter:注意RecyclerView.Adapter替换了原来的 PagerAdapter
     *
     * */
    internal class BaseAdapter(datas: List<String>) :
        RecyclerView.Adapter<BaseAdapter.BaseViewHolder?>() {
        var datas: List<String>
        override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): BaseViewHolder {
            val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.v_demo_view_page_item_text, parent, false)
            return BaseViewHolder(itemView)
        }

        override fun onBindViewHolder( holder: BaseViewHolder, position: Int) {
            holder.textView?.setText(datas.get(position))
        }

        override fun getItemCount(): Int {
            return datas!!.size
        }

        inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView?

            init {
                textView = itemView.findViewById(R.id.demo_view_pager_item_text)
            }
        }

        init {
            this.datas = datas
        }
    }
}





