## Some Base Widget in Android App
> Android应用中一些常用的控件

### 控件的几个基本属性

* id 控件ID
* layout_width 宽度: wrap_content或者match_parent
* layout_height 高度: wrap_content或者match_parent
* background 背景: 颜色/图片

### 1. 展示控件

#### TextView 文本

```xml
<TextView
    android:id="@+id/tv_user_label"
    android:layout_width="wrap_content"
    android:layout_height="50dp"
    android:text="用户名"
    android:gravity="center"
    android:textSize="24sp"
    android:textColor="#0000FF"
    android:textStyle="normal"
    android:singleLine="true"
    android:ellipsize="start"/>
```

主要属性:
  *  text 文本文字 (推荐，引用values下面的string.xml里面的元素)
  *  textSize 字体大小
  *  textColor 字体颜色
  *  textStyle 字体格式 ormal,bold,italic分别为正常，加粗以及斜体
  *  gravity 文本显示位置： top、bottom、left、right、center
  *  singleLine 是否只在一行内显示全部内容
  *  ellipsize 省略文字的表达形式： start,middle,end,marquee
  
#### ImageView 图片

主要属性:
  *  scaleType: 
    *   fitXY: 把原图按照指定的大小在View中显示，拉伸显示图片，不保持原比例，填满ImageButton.
    *   center: 在视图中心显示图片，并且不缩放图片
    *   centercrop: 按比例缩放图片，使得图片长 (宽)的大于等于视图的相应维度
    *   centerinside: 按比例缩放图片，使得图片长 (宽)的小于等于视图的相应维度
    *   fitcenter: 按比例缩放图片到视图的最小边，居中显示
    *   fitend: 按比例缩放图片到视图的最小边，显示在视图的下部分位置
    *   fitstart: 把图片按比例扩大/缩小到视图的最小边，显示在视图的上部分位置
    *   matrix: 用矩阵来绘制    
  *  src 图片来源: @drawable/beautiful,将图片放到res/drawable文件夹里面

_使用Glide加载网络图片_

```kotlin
Glide.with(getContext())
     .load("http://*.jpg")
     .into(imageDetail);
```

### 2. 表单控件

#### EditText

主要属性：
  *  text 文字内容:  文本或者引用values下面的string.xml里面的元素
  *  hint 文本提示内容
  *  password: 输入内容设置为password类型, true
  *  phoneNumber: 输入内容设置为phoneNumber类型, true
  *  cursorVisible 设定光标为显示/隐藏: 默认为true显示 
    
常用回调:
```kotlin
        val etName = findViewById<EditText>(R.id.et_username)
        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 输入后的监听
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //输入后的监听
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //输入文字产生变化的监听}
            }
        })

```    

* addTextChangedListener 监听输入框内容变化
* setOnFocusChangeListener 监听输入焦点变化
    
#### CheckBox

主要属性：
  *  isChecked 是否已选择
常用回调:   
  *  setOnCheckedChangeListener 监听状态变化
    
#### Radio

主要属性：
  *  isChecked 是否已选择
常用回调:   
  *  setOnCheckedChangeListener 监听状态变化  

#### SeekBar 滑动条

主要属性
  *  max 滑动条的最大值
  *  progress 滑动条的当前值
  *  thumb 滑片背景
  *  progressDrawable 引用背景

常用回调:   
  *  setOnSeekBarChangeListener 监听值的变化  

#### Switch

主要属性：
  *  isChecked 是否已选择

常用回调:   
  *  setOnCheckedChangeListener 监听状态变化

#### CalendarView

主要属性：
  *  maxDate 支持的最大日期: "07/27/2019 以mm/dd/yyyy格式设置
  *  minDate 支持的最小日期: "07/27/2019 以mm/dd/yyyy格式设置
  *  firstDayOfWeek 设置每周第一天(数字3对应周二、4对应周三、1对应周日)
常用回调:   
  *  setOnDateChangeListener 监听值的变化
    
#### Button

常用回调:   
  *  setOnClickListener 监听状态变化    
