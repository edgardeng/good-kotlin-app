package com.edgar.movie.widget;

import android.content.Context;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout
import com.edgar.movie.R
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

class CellBar : ConstraintLayout {

    var iconView: ImageView? = null
    var labelView: TextView? = null
    var contentView: TextView? = null

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
        //init(context)要在retrieveAttributes(attrs)前调用
        //因为属性赋值，会直接赋值到控件上去。如:
        //调用label = ""时，相当于调用了label的set方法。
        init(context)
        //retrieveAttributes(attrs: AttributeSet)方法只接受非空参数
        attrs?.let { retrieveAttributes(attrs) }
    }

    fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_cell_bar,this)
        iconView = view.findViewById(R.id.cell_bar_icon)
        labelView = view.findViewById(R.id.cell_bar_label)
        contentView = view.findViewById(R.id.cell_bar_text)
    }

    fun retrieveAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CellBar)
        val iconRes = typedArray.getResourceId(R.styleable.CellBar_icon, 0)
        if (iconRes != 0)
            icon = iconRes
        label = typedArray.getText(R.styleable.CellBar_label)
        text = typedArray.getText(R.styleable.CellBar_text)
//        labelTextSize = typedArray.getFloat(R.styleable.JSCItemLayout_label_text_size, 14f)
//        labelTextColor = typedArray.getColor(R.styleable.JSCItemLayout_label_text_color, 0xff333333.toInt())
//        val v2 = typedArray.getResourceId(R.styleable.JSCItemLayout_arrow_icon, 0)
//        if (v2 != 0)
//            arrowIcon = v2
//        typedArray.recycle()
    }

}