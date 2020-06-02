## Some Container Widget in Android App
> Android应用中一些常用的容器控件

### 1. Android中常见的容器控件

#### Spinner 下拉控件

主要的属性：

  * entries: 在xml布局文件中绑定数据源(`  * entries="@array/languages"`)（可以不设置，即可以在Activity中动态绑定）
  * spinnerMode: Spinner的显示形式 （dropdown 下拉菜单， dialog 弹出框）
  * prompt：在Spinner弹出选择对话框的时候对话框的标题：
  *   * dropDownHorizontalOffset: 下拉的项目选择窗口在水平方向相对于Spinner窗口的偏移量 px(像素)、dp(密度无关的像素)、sp(基于引用字体的尺寸来缩放的像素)、in(英寸)、mm(毫米)
  * dropDownSelector: dropdown时列表选择器的显示效果。
  * dropDownVerticalOffset : dropdown时，下拉的项目选择窗口在垂直方向相对于Spinner窗口的偏移量
  * dropDownWidth : 设定下拉框的宽度
  * popupBackground : dropdown”时，使用这个属性来设置下拉列表的背景

```kotlin
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
                Log.e(TAG, "onItemSelected 你点击的是:" + languages[pos]) }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e(TAG, "onNothingSelected")
            }
        }
        spinner.setSelection(2)
```

#### GridView

主要的属性：
  * numColumns=”auto_fit” //GridView的列数设置为自动
  * columnWidth=”90dp " //每列的宽度，也就是Item的宽度
  * stretchMode=”columnWidth" //缩放与列宽大小同步
  * verticalSpacing=”10dp” //两行之间的边距
  * horizontalSpacing=”10dp” //两列之间的边距
  * cacheColorHint="#00000000" //去除拖动时默认的黑色背景
  * listSelector="#00000000" //去除选中时的yello底色
  * scrollbars="none" //隐藏GridView的滚动条
  * fadeScrollbars="true" //设置为true就可以实现滚动条的自动隐藏和显示
  * fastScrollEnabled="true" //GridView出现快速滚动的按钮(至少滚动4页才会显示)
  * fadingEdge="none" //GridView衰落(褪去)边缘颜色为空，缺省值是vertical。(可以理解为上下边缘的提示色)
  * fadingEdgeLength="10dip" //定义的衰落(褪去)边缘的长度
  * stackFromBottom="true" //设置为true时，你做好的列表就会显示你列表的最下面
  * transcriptMode="alwaysScroll" //当你动态添加数据时，列表将自动往下滚动最新的条目可以自动滚动到可视范围内
  * drawSelectorOnTop="false" //点击某条记录不放，颜色会在记录的后面成为背景色,内容的文字可见(缺省为false)

```kotlin
    val grid_view = findViewById<GridView>(R.id.demo_gridview)
    val numbers= listOf("一", "二", "三", "四","五","六")
    grid_view.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
    grid_view.numColumns = 3
    // 注册单元格的点击事件
    grid_view.setOnItemClickListener { parent, view, position, id ->
         Log.e(TAG, "GridView setOnItemClickListener 你点击的是:" + numbers[position])
    }
```

#### ListView

```kotlin
    val listview = findViewById<ListView>(R.id.demo_listview)
    val fruits= listOf("第一个是列表", "Apple", "Orange", "Pear", "Peach")
    listview.adapter = ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,fruits)
    listview.setOnItemClickListener { adapterView, view, postion, var4 -> Log.e(TAG, "ListView OnItemClick: $postion") }
```
### TabHost
> 标签页 ，常常结合TabWidget + FrameLayout使用

```xml
    <TabHost
        android:id="@+id/demo_tabhost"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:background="@android:color/darker_gray"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@id/demo_horizontalscrollview">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">
            <TabWidget
                android:id="@android:id/tabs"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
            <!-- FrameLayout布局，id值不可变-->
            <FrameLayout
                android:id="@android:id/tabcontent"
                android:layout_width="fill_parent"
                android:layout_height="fill_parent"
                android:layout_above="@android:id/tabs">
                <!-- 第一个tab的布局 -->
                <LinearLayout
                    android:id="@+id/tab1"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="第一个tab的布局" />
                </LinearLayout>
                <LinearLayout
                    android:id="@+id/tab2"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="第2个tab的布局" />
                </LinearLayout>
                <LinearLayout
                    android:id="@+id/tab3"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="第3个tab的布局" />
                </LinearLayout>
            </FrameLayout>
        </LinearLayout>
    </TabHost>
```

__简单的配置__

```kotlin
    val tab = findViewById<TabHost>(R.id.demo_tabhost)
    tab.setup() // 初始化TabHost容器
    tab.addTab(tab.newTabSpec("tab1").setIndicator("未接来电", null).setContent(R.id.tab1)) // 在TabHost创建标签，然后设置：标题／图标／标签页布局
    tab.addTab(tab.newTabSpec("tab2").setIndicator("已接来电", null).setContent(R.id.tab2))
    tab.addTab(tab.newTabSpec("tab3").setIndicator("呼出通话", null).setContent(R.id.tab3))
    tab.setOnTabChangedListener { var1 -> Log.e(TAG, "TabHost OnTabChanged: $var1") }
```

### ScrollView 滚动视图

### HorizontalScrollView 横向滚动视图

### 2. AndroidX中推荐的容器控件

### RecyclerView
> androidx.recyclerview.widget.RecyclerView
#### ToolBar


### 3. Material Design下的控件

#### CardView 卡片视图
#### AppBarLayout 标题栏
#### BottomAppBar 底部栏
#### NavigationView 导航栏
#### BottomNavigationView 底部导航栏
#### TabLayout
#### TabItem

### 4. 一些特殊的容器控件

#### ViewStub
     
#### include
     
#### fragment
     
#### View
     
#### requestFocus
