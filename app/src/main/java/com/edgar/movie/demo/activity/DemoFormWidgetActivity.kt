package com.edgar.movie.demo.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.edgar.movie.R
import java.util.*


/*
 * 基本控件的学习 展示类
 */
class DemoFormWidgetActivity : AppCompatActivity() {
    val TAG = "DemoFormWidgetActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_base_widget)
        aEditText()
        aCheckBox()
        aRadioGroup()
        aSwitch()
        aSeekBar()
        aCalendarView()
        aButton()
    }

    fun aTextView() {
        var tvLabel = findViewById<TextView>(R.id.tv_user_label)
//        tvLabel.sette

    }

    fun aEditText() {
        val etName = findViewById<EditText>(R.id.et_username)
        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 输入后的监听
                Log.e("TAG", "afterTextChanged:" + s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e("TAG", "beforeTextChanged:" + s.toString() + ", start:" + start+ ", count"+count+ ", after:"+after)
                //输入后的监听
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("TAG", "onTextChanged:" + s.toString() + ", start:" + start+ ", count"+count)
                //输入文字产生变化的监听
            }
        })
    }

    fun aCheckBox() {
        var checkBox = findViewById<CheckBox>(R.id.cb_first_use)
        checkBox.isChecked = true
        checkBox.setOnCheckedChangeListener(object : OnCheckedChangeListener {
             override fun onCheckedChanged( buttonView: CompoundButton, isChecked:Boolean) {
                Log.e("TAG", "CheckBox onCheckedChanged:$isChecked")
            }
        });
    }
    fun aRadioGroup() {
        var radioGroup = findViewById<RadioGroup>(R.id.rg_gender)
        radioGroup.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, viewId: Int) {
                 when (viewId) {
                     R.id.rb_m -> Log.e("TAG", "radioGroup onCheckedChanged: Male")
                     R.id.rb_f -> {
                         Log.e("TAG", "radioGroup onCheckedChanged: Female")
                     }
                 }
            }

        })
    }
    fun aSwitch() {
        var switch = findViewById<Switch>(R.id.s_save_password)
        switch.isChecked = true
        switch.setOnCheckedChangeListener { buttonView, isChecked -> Log.e("TAG", "Switch onCheckedChanged:$isChecked") }
    }

    fun aSeekBar() {
        var seekBar = findViewById<SeekBar>(R.id.sb_height)
//        seekBar.min = 0
        seekBar.max = 200
        seekBar.progress = 90

        seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("TAG", "SeekBar onProgressChanged: $progress v2: $fromUser")
                Log.e("TAG", "SeekBar progress: ${sb?.progress}")
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {
                Log.e("TAG", "SeekBar onStartTrackingTouch")
                Log.e("TAG", "SeekBar progress: ${sb?.progress}")
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
                Log.e("TAG", "SeekBar onStopTrackingTouch")
                Log.e("TAG", "SeekBar progress: ${sb?.progress}")
            }
        });
    }

    private fun aCalendarView () {
        val calendarView = findViewById<CalendarView>(R.id.cv_birthday)
        val c: Calendar = Calendar.getInstance()
        calendarView.setOnDateChangeListener { v, year, month, dayOfMonth ->
            Log.e("TAG", "CalendarView setOnDateChangeListener $year-$month-$dayOfMonth")
        }
    }
    fun aButton () {
        val btn = findViewById<Button>(R.id.btn_form)
        btn.setOnClickListener { v ->
            Log.e("TAG", "Button OnClick")
        }
    }

}