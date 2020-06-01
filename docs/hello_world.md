## Hello World in Kotlin App

### 在Android Studio中新建一个kotlin项目

1. Create New Project 创建一个新项目

2. Select a Project Template 选择一个模版 （此处选择 Base Activity）。点击Next

3. Configure Your Project
    - Name : app name (good-kotlin-app)
    - Package Name : (com.edgar.movie)
    - Save location: which fold save your projcet(~/StudioProject/good-kotlin-app)
    - Language: Kotlin
4. Finish . 等待AS 生产相应的目录和文件

### 目录介绍

```text
|_ app (app模块 也是应用的主要代码)
    |_ libs （本地依赖包）
    |_ src
        |_ androidTest （测试文件）
        |_ main
            |_ java                （代码文件）
            |_ res                 （资源文件）
            |_ AndroidManifest.xml (安卓清单文件)
        |_ test （测试文件）

    |_ build.gradle (模块的gradle脚本)
    |_ proguard-rules.pro (代码混淆规则)
|_ gradle (gradle的版本配置)
|_ build.gradle (gradle的脚本)
|_ gradle.properties (使用gradle时的配置)
|_ settings.gradle (gradle项目的配置)

```

### 重要代码介绍

在清单文件`AndroidManifest.xml`中,定义包名：com.edgar.movie，入口文件：MainActivity

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.edgar.movie">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

文件：`com.edgar.movie.MainActivity`，继承Actvity类，加载视图文件`activity_main`

```kotlin
package com.edgar.movie
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}
```

在视图文件`activity_main`, 定义了一个布局和文本控件

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
     <TextView
           android:id="@+id/textview_first"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@string/hello_world"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toTopOf="parent" />
</androidx.coordinatorlayout.widget.CoordinatorLayout>

```

### 其他一些说明

* _gradle在同步项目时，下载超时问题_

替换根目录下build.gradle中的

```
  repositories {
    jcenter()
  }
```   
为

```
  repositories {
    maven {url 'http://maven.aliyun.com/nexus/content/groups/public/'}
  }
```