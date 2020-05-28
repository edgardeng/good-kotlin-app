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