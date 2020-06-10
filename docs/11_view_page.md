## 滑动视图之ViewPager
> 滑动视图允许您通过水平手指手势或滑动在同级屏幕（例如标签页）之间进行导航。此导航模式也称为“水平分页”。此处使用androidx.viewpager2.widget.ViewPager2

### 一个简答的案例

TabLayout + ViewPager2 布局如下：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/demo_view_pager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />
</LinearLayout>

```

创建activity

```kotlin
class DemoViewPagerActivity : AppCompatActivity() {
    private val TAG = "DemoViewPagerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_viewpager)

        var vpBase = findViewById<ViewPager2>(R.id.demo_view_pager)
        val baseAdapter = BaseAdapter(datas)
        with(vpBase) {
            adapter = baseAdapter
            isUserInputEnabled = true // 禁止滚动true为可以滑动false为禁止
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 设置垂直滚动ORIENTATION_VERTICAL
            setCurrentItem(1, true) //切换到指定页，是否展示过渡中间页
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
    }
}

``` 

创建适配器代码

```kotlin
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
```

创建适配器布局

```xml
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/demo_view_pager_item_text"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:textSize="40sp"
    android:textColor="@color/accent"/>

```

连接 tabLayou和 ViewPager

```kotlin
var datas:List<String> = listOf("第一页", "第二页", "第三页")
val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
TabLayoutMediator(tabLayout, vpBase) { tab, position ->
  tab.text = "PAGE ${(position + 1)}"
}.attach()
```

#### ViewPager2 主要方法：

* addItemDecoration(@NonNull decor: RecyclerView.ItemDecoration, index: Int)

* setAdapter(@Nullable adapter: RecyclerView.Adapter<RecyclerView.ViewHolder!>?)

* setUserInputEnabled(enabled: Boolean) 设置是否可以滚动

* setCurrentItem 设置指定页面

### Reference 参考

* [ViewPager2 Google官方示例](https://github.com/android/views-widgets-samples/tree/master/ViewPager2)

* [ViewPager2 Google文档](https://developer.android.google.cn/guide/navigation/navigation-swipe-view-2)

* [本文示例](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/demo/activity)

