## 列表控件 RecyclerView
> RecyclerView 控件是 ListView 的更高级、灵活版本。

### 一个简单的案例

**将 RecyclerView 添加到您的布局中**
打开应用模块的 。

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

**在activity中获取控件**

```kotlin
    dataset = MutableList( DATASET_COUNT, { i -> "Element # $i" })
    recyclerViewAdapter = CustomRecyclerViewAdapter(dataset)
    layoutManager = LinearLayoutManager(this)
    recyclerView = findViewById(R.id.recyclerView)
    with(recyclerView) {
        setHasFixedSize(true)
        layoutManager
        adapter = recyclerViewAdapter
        itemAnimator = DefaultItemAnimator()
    }
```
**自定义适配器**

```kotlin
    class CustomRecyclerViewAdapter(private val dataSet: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var size = 0;
        class NoramalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView: TextView
            init {
              v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
              textView = v.findViewById(android.R.id.text1)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v = LayoutInflater.from(viewGroup.context).inflate(android.R.layout.simple_list_item_1, viewGroup, false)
            return NoramalViewHolder(v)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            (viewHolder as NoramalViewHolder).textView.text = dataSet[position]
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = size

        fun resetDataset() {
            dataSet.clear()
        }
        fun addDataSet( newDatas: List<String>?, m: Boolean ) {
            if (newDatas != null) {
                dataSet.addAll(newDatas)
            }
            size = dataSet.size
            more = m
            notifyDataSetChanged()
        }
    }
```

###  使用 SwipeRefreshLayout 下拉更新

在布局中使用SwipeRefreshLayout包裹RecyclerView

```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/swipe_refresh_layout">
    <androidx.recyclerview.widget.RecyclerView ...
</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

在代码中添加下拉监听器

```kotlin
var swipeRefreshLayout: SwipeRefreshLayout
swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout);
swipeRefreshLayout.setOnRefreshListener { doWhenRefresh() }
```

### 上拉加载

修改CustomRecyclerViewAdapter，监听RecyclerView的滚动 来实现上拉加载

**新建一个底部加载更多的视图**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:gravity="center"
    android:padding="10dp">
    <ProgressBar
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"/>
    <TextView
     android:text="@string/load_more"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:layout_marginStart="10dp"/>
</LinearLayout>
```

**在Adapter 添加ViewHolder,重写getItemViewType**

```kotlin

class CustomRecyclerViewAdapter(private val dataSet: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     private var more = false // 变量，是否有更多数据
     private var size = 0;

     class FootViewHolder(v: View) : RecyclerView.ViewHolder(v) {
     }

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
         }
     }

     // Return the size of your dataset (invoked by the layout manager)
     override fun getItemCount() = if (more) size + 1 else size
     private val normalType = 0 // 第一种ViewType，正常的item
     private val footType = 1 // 第二种ViewType，底部的提示View
     // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
     override fun getItemViewType(position: Int): Int {
         return if (position < size) normalType else footType
     }
}
```

**添加RecyclerView监听器**

```kotlin
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
             if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (lastVisibleItem + 1 == recyclerViewAdapter.getItemCount()) {
                    Handler().postDelayed({
                        doWhenLoad()
                    }, 1000)
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
        }
    })
```

### Reference 参考

* [RecyclerView 官方文档](https://developer.android.google.cn/guide/topics/ui/layout/recyclerview?hl=zh_cn)

* [RecyclerView 官方示例](https://github.com/android/views-widgets-samples/tree/master/RecyclerViewKotlin)

* [本文示例](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/demo/activity)
