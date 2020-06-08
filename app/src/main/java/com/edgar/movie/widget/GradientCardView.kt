package com.edgar.movie.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.edgar.movie.R

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