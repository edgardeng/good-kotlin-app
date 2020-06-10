
## 架构组件之导航Navigation
> Navigation 组件是一个库，可管理应用中多个屏幕之间的复杂导航、过渡动画、深层链接以及编译时间检查参数传递

使用Navigation,需要在 build.gradle 中添加依赖：

```
  implementation 'androidx.navigation:navigation-fragment:2.2.2'
  implementation 'androidx.navigation:navigation-ui:2.2.2'
```

### 简单的导航组件使用

#### 1、 创建一个activity

布局如下

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/primary"
        android:theme="@style/ThemeOverlay.MaterialComponents.Dark.ActionBar" />

    <fragment
        android:id="@+id/my_nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/demo_navigation" />
</LinearLayout>
```

fragment三个属性需要注意。
  * android:name 指定 Fragment的类型为 NavHostFragment
  * app:navGraph 指定 Navigation 文件
  * app:defaultNavHost="true" 的作用是，让 Navigation 处理返回事件，点返回按钮时并不是返回上一个 Activity，而是返回上一个「页面」，上一个「页面」有可能是 Activity，也可能是 Fragment。

activity代码如下：

```kotlin
class DemoNavigationActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity_navigation)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        // 导航监听器
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }
            Toast.makeText(this@DemoNavigationActivity, "Navigated to $dest",Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }
}
```

重点: 获取NavController，和 appBarConfiguration


#### 2. 创建若干Fragment

一个简单的Fragment代码如下

```kotlin
class HomeFragment : Fragment() {
    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.demo_fragment_navigation_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            findViewById<Button>(R.id.navigate_destination_button).setOnClickListener {
                getView()?.let {
                    it -> Navigation.findNavController(it).navigate(R.id.flow_step_one_dest, null)
                }
        }
    }
}
```

重点: 设置按钮的点击事件为跳转

在 Navigation 里，页面的跳转是交给 NavController 来处理的

**获取NavController的方法**：

 * NavHostFragment.findNavController(Fragment)
 * Navigation.findNavController(Activity, @IdRes int viewId)
 * Navigation.findNavController(View)
 
**通过NavController的navigate来跳转**：

 * navigate(@IdRes int resId) 通过 destinationId 或者 actionId 跳转
 * navigate(@IdRes int resId, @Nullable Bundle args) 通过id + 参数
 * navigate(@IdRes int resId, @Nullable Bundle args, @Nullable NavOptions navOptions) 通过id + 参数 + 转场动画
 
#### 3. 创建导航视图


![](https://developer.android.google.cn/images/topic/libraries/architecture/navigation-design-graph-top-level.png?hl=zh_cn)
>导航图是一种资源文件，其中包含您应用的所有目的地和逻辑连接（后者也称为“操作”，用户可以执行以从一个目的地导航到另一个目的地）。您可以使用 Android Studio 中的 Navigation Editor 管理应用的导航图。
 
1. 创建navigation目录
2. 选中项目资源文件夹 res 右击 >> New >> New Resource Directory
3. 选中navigation 点击创建

一个简单的navigation的xml代码

```xml

<?xml version="1.0" encoding="utf-8"?>

<navigation 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    app:startDestination="@+id/home_dest">

    <fragment
        android:id="@+id/home_dest"
        android:name="com.edgar.movie.demo.activity.navigation.HomeFragment"
        android:label="home"
        tools:layout="@layout/demo_fragment_navigation_main">
        <!--<action-->
            <!--android:id="@+id/next_action"-->
            <!--app:destination="@+id/flow_step_one_dest"-->
            <!--app:enterAnim="@anim/slide_in_right"-->
            <!--app:exitAnim="@anim/slide_out_left"-->
            <!--app:popEnterAnim="@anim/slide_in_left"-->
            <!--app:popExitAnim="@anim/slide_out_right" />-->
    </fragment>

    <fragment
        android:id="@+id/flow_step_one_dest"
        android:name="com.edgar.movie.demo.activity.navigation.FlowStepFragment"
        tools:layout="@layout/demo_fragment_flow_step_one">
        <argument
            android:name="flowStepNumber"
            app:argType="integer"
            android:defaultValue="1"/>
        <action
            android:id="@+id/next_action"
            app:destination="@+id/flow_step_two_dest">
        </action>
    </fragment>

    <fragment
        android:id="@+id/flow_step_two_dest"
        android:name="com.edgar.movie.demo.activity.navigation.FlowStepFragment"
        tools:layout="@layout/demo_fragment_flow_step_two">

        <argument
            android:name="flowStepNumber"
            app:argType="integer"
            android:defaultValue="2"/>
        <action
            android:id="@+id/next_action"
            app:popUpTo="@id/home_dest">
        </action>
    </fragment>
</navigation>
```

**navigation里的属性:**
  1. android:id: xml文件navigation的id,很重要,我们需要在activity的xml布局里引用,记得写上不要忘记
  2. app:startDestination: 次加载的第一个页面,很重要,一般就是第一个fragment

**fragment 里的属性:**

  1. android:id="@+id/one"  每一个fragment节点都需要有自己的id, 目标ID

  2.android:name 对应的Fragment

  3. android:label 标签信息

  4. tools:layout="@layout/fragment_blank" 布局

**action (负责编写跳转动作)里的属性:**

  1. android:id 跳转动作的id

  2. app:destination 跳转的目标fragment
   
### Reference 参考

* [Android官方示例 基本导航示例](https://github.com/android/architecture-components-samples/tree/master/NavigationBasicSample)

* [Android官方示例 高级导航示例](https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample)

* [NavController 文档](https://developer.android.google.cn/reference/androidx/navigation/NavController)

* [Google 迁移到 Navigation 组件](https://developer.android.google.cn/guide/navigation/navigation-migrate?hl=zh_cn)
