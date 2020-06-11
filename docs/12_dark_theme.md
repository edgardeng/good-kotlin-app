## Dark theme 暗色主题

### 样式和主题

样式是一个属性集合，用于指定单个 View 的外观。 样式可以指定字体颜色、字号、背景颜色等属性。

主题背景是一种应用于整个应用、Activity 或视图层次结构的样式，而不仅仅应用于单个视图。

在项目的 res/values/styles.xml 文件，创建需要的样式

```xml
<resources>
        <style name="GreenText" parent="TextAppearance.AppCompat">
            <item name="android:textColor">#00FF00</item>
        </style>
    </resources>
```
> 使用 parent 属性指定要扩展的样式

创建主题背景, 像创建样式一样

```xml
   <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```

将深色”主题背景应用于整个应用：
对 AndroidManifest.xml 文件中的 <application> 标签或 <activity> 标签应用具有 android:theme 属性的主题背景
```xml
 <manifest ... >
   <application android:theme="@style/Theme.AppCompat" ... >
   </application>
 </manifest>
```

**样式层次结构**

如果您在多个位置指定了相同的属性，下面的列表决定了最终将应用哪些属性。该列表从最高优先级到最低优先级排序：

1. 通过文本 span 将字符或段落级样式应用到 TextView 派生类
2. 以编程方式应用属性
3. 将单独的属性直接应用到 View
4. 将样式应用到 View
5. 默认样式
6. 将主题背景应用于 View、Activity 或您的整个应用
7. 应用某些特定于 View 的样式，例如在 TextView 上设置 TextAppearance

### 深色主题背景
> Android 10 (API 级别 29) 及更高版本中提供深色主题背景

#### 更改应用内主题背景

当应用在搭载 Android 9 或更低版本的设备上运行时，推荐的主题背景选项是：
  
  * 浅色
  * 深色
  * 由省电模式设置（推荐的默认选项）

在 Android 10 (API 级别 29) 及更高版本上运行时，推荐的选项有所不同，目的是允许用户替换系统默认设置：
     
  * 浅色
  * 深色
  * 系统默认（推荐的默认选项）
  
> 请注意，如果用户选择“Light”，省电模式不会更改该设置。
     
每个选项直接映射到以下某个 AppCompat.DayNight 模式：
     
  * 浅色 - MODE_NIGHT_NO
  * 深色 - MODE_NIGHT_YES
  * 由省电模式设置 - MODE_NIGHT_AUTO_BATTERY
  * 系统默认 - MODE_NIGHT_FOLLOW_SYSTEM
  * 如要切换主题背景，请调用 AppCompatDelegate.setDefaultNightMode()。
      
#### 响应主题背景的更改

每个 Activity 都可以处理 uiMode 配置变更，以自行处理深色主题背景
```xml
<activity
    android:name=".MyActivity"
    android:configChanges="uiMode" />
```

系统会在出现主题背景变更时调用该 Activity 的 onConfigurationChanged() 方法

```kotlin
override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig);
    val currentNightMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    when (currentNightMode) {
        Configuration.UI_MODE_NIGHT_NO -> {} // Night mode is not active, we're using the light theme
        Configuration.UI_MODE_NIGHT_YES -> {} // Night mode is active, we're using dark theme
    }
}
```

### Reference 参考

* [Material Design color theming system](https://material.io/develop/android/theming/color/)

* [Darktheme Google示例](https://github.com/android/user-interface-samples/tree/master/DarkTheme)

* [Darktheme Google文档](https://developer.android.google.cn/guide/topics/ui/look-and-feel/darktheme)

* [本文示例](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/demo/activity)
