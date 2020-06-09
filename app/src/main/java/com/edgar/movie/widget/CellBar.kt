package com.edgar.movie.widget;

import android.content.Context;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout
import com.edgar.movie.R
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

class CellBar : ConstraintLayout {

    private var iconView: ImageView? = null
    private var labelView: TextView? = null
    private var contentView: TextView? = null
    private var arrowView: ImageView? = null
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
    var clickable: Boolean? = true
        set(value) {
            field = value
            arrowView?.visibility = if (value!!) View.VISIBLE else View.INVISIBLE
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
        arrowView = view.findViewById(R.id.cell_bar_go)
    }

    private fun retrieveAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CellBar)
        val iconRes = typedArray.getResourceId(R.styleable.CellBar_icon, 0)
        if (iconRes != 0)
            icon = iconRes
        label = typedArray.getText(R.styleable.CellBar_label)
        text = typedArray.getText(R.styleable.CellBar_text)
        clickable = typedArray.getBoolean(R.styleable.CellBar_clickable, true)
        typedArray.recycle()
    }

}