## Dialog in Android App
> 常用的对话框
 
### 提醒对话框

```kotlin
 with(AlertDialog.Builder(this)) {
   setTitle("尊敬的用户")
   setMessage("你真的要卸载我吗？")
   setNeutralButton("我要升级") { dialog, which -> show("click 我要升级 ") }
   setPositiveButton("残忍卸载") { dialog, which -> show("click 残忍卸载 ") }
   setNegativeButton("我再想想") { dialog, which -> show("click 我再想想 ") }
   create()
 }.show()
```

### 提醒对话框-单选

```kotlin
  val satellites = arrayOf("水星", "金星", "地球", "火星", "木星", "土星")
  val builder = AlertDialog.Builder(this)
  builder.setTitle("请选择行星")
  builder.setItems(
    satellites,
    DialogInterface.OnClickListener { dialog, which -> show("你选择的行星是" + satellites[which]) })
  builder.create().show()
```

### 提醒对话框-单选

```kotlin

  val gender = arrayOf("男", "女")
  with(AlertDialog.Builder(this)) {
  setTitle("选择性别")
  setSingleChoiceItems(
    gender,
    1,
    DialogInterface.OnClickListener { dialog, which -> show("你选择的是" + gender[which]) })
  create()
  }.show()

```

### 提醒对话框-复选框
```kotlin
  val favourate = arrayOf("唱歌", "跳舞", "写作业")
    val isSelected = booleanArrayOf(false, false, true)
    with(AlertDialog.Builder(this)) {
      setTitle("选择兴趣")
      setMultiChoiceItems(
        favourate,
        isSelected,
        DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked -> show("你选择的是" + favourate[which] + ":" + isChecked) })
      setPositiveButton("取消") { dialog, which -> show("已取消") }
      setNegativeButton("确定") { dialog, which -> show("已确定") }
      create()
    }.show()
```

### 提醒对话框-输入框

```kotlin
  val view = LayoutInflater.from(this).inflate(R.layout.v_demo_dialog_input, null);
  val euserName: EditText = view.findViewById(R.id.et_dialog_input_name);
  val epassWord: EditText = view.findViewById(R.id.et_dialog_input_password);
  val btnLogin = view.findViewById<Button>(R.id.btn_dialog_input_login)
  btnLogin?.setOnClickListener() {
  show("Submit, Name:" + euserName.text.toString() + ",Password:" + epassWord.text.toString())
  }
  with(AlertDialog.Builder(this)) {
  setTitle("请先登陆")
  setView(view)
  create()
  }.show()
```
其中xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:orientation="vertical"
  android:id="@+id/root_view">

  <EditText
    android:id="@+id/et_dialog_input_name"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/username"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"/>

  <EditText
    android:id="@+id/et_dialog_input_password"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/password"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/et_dialog_input_name"
    />
  <Button
    android:id="@+id/btn_dialog_input_login"
    android:text="登录"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/et_dialog_input_password"/>
</androidx.constraintlayout.widget.ConstraintLayout>

```

### 进度加载对话框

API level 26 之前

```kotlin
  val dialog = ProgressDialog(this)
  dialog.setTitle("请稍候")
  dialog.setMessage("正在努力加载页面")
  dialog.max = 100
  dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
  dialog.show()
``` 

API level 26 之后

```kotlin
  val progressBar = ProgressBar(this, null,0,R.style.Widget_AppCompat_ProgressBar_Horizontal)
  progressBar?.max = 100
  val progressDialog = with(AlertDialog.Builder(this)) {
    setTitle("请稍候")
    setMessage("正在努力加载页面")
    setView(progressBar)
    create()
  }
  progressDialog!!.show()
```

* [Android ProgressBar自定义样式](https://www.jianshu.com/p/6661503a0712)

* [Popup Menu ](https://www.jianshu.com/p/9abbf0fe4846)

* [自定义对话框](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/demo/widget/CustomDialog.kt)

* [本文示例](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/demo/activity/DemoDialogActivity.kt)
