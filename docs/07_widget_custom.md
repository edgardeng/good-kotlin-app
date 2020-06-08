## Define a Custom Widget in Android App
> 在Android应用中定义个自定义控件

### 1. 使用XML自定义视图/控件

一个简单的例子，自定义单元条 CellBar

1、xml布局如下:

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/white"
    android:padding="10dp">
    <ImageView
        android:id="@+id/cell_bar_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"/>
    <TextView
        android:id="@+id/cell_bar_label"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="5dp"
        android:textColor="@color/black"
        android:text="android:"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toEndOf="@id/cell_bar_icon"/>
    <TextView
        android:id="@+id/cell_bar_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="android2:"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@id/cell_bar_go"/>

    <ImageView
        android:id="@+id/cell_bar_go"
        android:src="@drawable/ic_keyboard_arrow_right"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>


```

2、代码如下（继承ConstraintLayout）:

```kotlin
class CellBar : ConstraintLayout {

    private var iconView: ImageView? = null
    private var labelView: TextView? = null
    private var contentView: TextView? = null

    @DrawableRes
    var icon: Int = 0
        set(value) {
            field = value
            iconView?.setImageResource(value)
        }

    var label: CharSequence? = null
        set(value) {
            field = value
            labelView?.text = value
        }

    var text: CharSequence? = null
        set(value) {
            field = value
            contentView?.text = value
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
       init(context)
        attrs?.let { retrieveAttributes(attrs) }
    }

    fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_cell_bar,this)
        iconView = view.findViewById(R.id.cell_bar_icon)
        labelView = view.findViewById(R.id.cell_bar_label)
        contentView = view.findViewById(R.id.cell_bar_text)
    }

    private fun retrieveAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CellBar)
        val iconRes = typedArray.getResourceId(R.styleable.CellBar_icon, 0)
        if (iconRes != 0)
            icon = iconRes
        label = typedArray.getText(R.styleable.CellBar_label)
        text = typedArray.getText(R.styleable.CellBar_text)
        typedArray.recycle()
    }
}
```

> 上述代码 重写了构造器。在构造器中：1 加载xml布局文件，2 获取控件 ，3 获取属性 ，4 将属性赋值到控件上

3、添加自定义属性 `res/values/attrs.xml`

```xml
<resources>
    <declare-styleable name="CellBar">
        <attr name="icon" format="reference" />
        <attr name="label" format="string" />
        <attr name="text" format="string" />
    </declare-styleable>
</resources>
```

4、使用自定义控件

```xml
  <com.edgar.movie.widget.CellBar
    android:layout_marginTop="20dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:icon="@drawable/ic_sofa"
    app:label="看电影"/>
```

### 2. 使用onDraw自定义视图/控件

绘制一个渐变色背景的视图

```kotlin
class GradientCardView : CardView {

    private var mBackGroundRect: RectF? = null
    private var backGradient: LinearGradient? = null
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG) // 默认画笔
    private var colorS = 0
    private var colorE = 0
    private var round = 0f

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, com.google.android.material.R.attr.materialCardViewStyle) {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientCardView)
            colorS = typedArray.getColor( R.styleable.GradientCardView_colorStart,0xFFFFFF)
            colorE = typedArray.getColor( R.styleable.GradientCardView_colorEnd,0x010101)
            round = typedArray.getDimension( R.styleable.GradientCardView_round,20.0f)
            typedArray.recycle()
        }
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.shader = backGradient
        if (mBackGroundRect != null) {
            canvas.drawRoundRect(mBackGroundRect!!, round, round, mPaint)
        } // 绘制背景 圆角矩形
        super.onDraw(canvas)

    }

    @SuppressLint("RestrictedApi")
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBackGroundRect = RectF(0f, 0f, w.toFloat(), h.toFloat())
        backGradient = LinearGradient( 0f, 0f, w.toFloat(), 0f,
            intArrayOf(colorS, colorE),
            null,
            Shader.TileMode.CLAMP
        )
    }
}
```

重要的重构方法
  * onSizeChanged 当视图大小改变时触发（在onDraw之前）
  * onDraw 使用canvas绘制视图


### Reference 参考

* [本文示例 CellBar ](https://github.com/edgardeng/good-kotlin-app/blob/master/app/src/main/java/com/edgar/movie/widget/CellBar.kt)

* [本文示例 GradientCardView ](https://github.com/edgardeng/good-kotlin-app/tree/master/app/src/main/java/com/edgar/movie/widget/GradientCardView.kt)

* [Canvas 文档](https://developer.android.google.cn/reference/android/graphics/Canvas?hl=en)

* [Paint 文档](https://developer.android.google.cn/reference/android/graphics/Paint?hl=en)
