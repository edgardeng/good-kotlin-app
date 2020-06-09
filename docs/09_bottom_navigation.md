## Use BottomNavigationView and Fragment to make home page
> 使用BottomNavigationView底部导航栏 和fragment 碎片创建首页

### 一个简单的例子

#### 创建activity

```kotlin
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie,
                R.id.navigation_rank,
                R.id.navigation_cinema,
                R.id.navigation_my
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
```

在activity代码中，配置AppBarConfiguration和NavController

布局文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="?attr/actionBarSize">

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/nav_view"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/bottom_nav_menu" />
    
    <fragment
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/mobile_navigation" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

菜单文件 `res/menu/bottom_nav_menu.xml`

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/navigation_movie"
        android:icon="@drawable/ic_movie"
        android:title="@string/movie" />
    ···
</menu>
```

导航文件 `res/menu/mobile_navigation.xml`

```xml
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mobile_navigation"
    app:startDestination="@+id/navigation_movie">
    <fragment
        android:id="@+id/navigation_movie"
        android:name="com.edgar.movie.ui.movie.MovieFragment"
        android:label="@string/movie"
        tools:layout="@layout/fragment_movie" />
    ...
</navigation>
```

#### 创建对应的Fragment

```kotlin
class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        val textView: TextView = root.findViewById(R.id.text)
        movieViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
```

Fragment的布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.movie.MovieFragment">
    <TextView
        android:id="@+id/text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>

```

### BottomNavigationView
> com.google.android.material.bottomnavigation.BottomNavigationView

主要的xml属性:

  * itemBackground	  底部导航栏的背景颜色,默认是主题的颜色
  * itemIconSize	  图标大小
  * itemIconTint	  图标颜色
  * itemRippleColor	  图标颜色  
  * itemTextColor	  文字颜色 
  * labelVisibilityMode	标签文本显示的方式
    * labeled 显示文本
    * selected 只显示选中的文本
    * unlabeled 不显示文本
    * auto 自动

重要的方法:
  * void removeBadge (int menuItemId) 移除角标
  * BadgeDrawable getOrCreateBadge(int menuItemId) 获取某个item的角标
  * void setOnNavigationItemReselectedListener 设置item被重选的监听器
  * void setOnNavigationItemSelectedListener 设置item被选中的监听器

> 注意事项:
  底部导航栏默认高度是56dp,
  菜单只能是3-5个

### Reference 参考

* [BadgeDrawable](https://developer.android.google.cn/reference/com/google/android/material/badge/BadgeDrawable)

* [BottomNavigationView](https://developer.android.google.cn/reference/com/google/android/material/bottomnavigation/BottomNavigationView)

* [本文示例](https://github.com/edgardeng/good-kotlin-app)