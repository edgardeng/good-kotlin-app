package com.edgar.movie.demo.activity

import CustomDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.edgar.movie.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit


/*
 * 基本控件的学习 展示类
 */
class DemoDialogActivity : AppCompatActivity() {
    val TAG = "DemoDialogActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_dialog)
//        BottomSheetDialog
    }

    fun showAlertDialog(view: View) {
        with(AlertDialog.Builder(this)) {
            setTitle("尊敬的用户")
            setMessage("你真的要卸载我吗？")
            setNeutralButton("我要升级") { dialog, which -> show("click 我要升级 ") }
            setPositiveButton("残忍卸载") { dialog, which -> show("click 残忍卸载 ") }
            setNegativeButton("我再想想") { dialog, which -> show("click 我再想想 ") }
            create()
        }.show()
    }

    fun showSpinnerDialog(view: View) {
        val satellites = arrayOf("水星", "金星", "地球", "火星", "木星", "土星")
        val builder =
            AlertDialog.Builder(this)
        builder.setTitle("请选择行星")
        builder.setItems(
            satellites,
            DialogInterface.OnClickListener { dialog, which -> show("你选择的行星是" + satellites[which]) })
        builder.create().show()
    }

    fun showRadioDialog(view: View) {
        val gender = arrayOf("男", "女")
        with(AlertDialog.Builder(this)) {
            setTitle("选择性别")
            setSingleChoiceItems(
                gender,
                1,
                DialogInterface.OnClickListener { dialog, which -> show("你选择的是" + gender[which]) })
            create()
        }.show()
    }

    fun showCheckDialog(view: View) {
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
    }

    fun showInputDialog(view: View) {
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
    }

    fun showCustomDialog(view: View) {
        with(CustomDialog(this)) {
            setTitle("提示")
            setMessage("注意消息")
            setCancel("取消",object : CustomDialog.IOnCancelListener {
                override fun onCancel(dialog: CustomDialog) {
                    show("自定义对话框取消")
                }
            })
            setConfirm("确认", object : CustomDialog.IOnConfirmListener {
                override fun onConfirm(dialog: CustomDialog) {
                    show("自定义对话框确定")
                }
            })
            show()
        }
    }

    fun showBottomSheetDialog(view: View) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.v_demo_dialog_input, null);
        bottomSheetDialog!!.setContentView(view)
        val parent = view.parent as View
        val params = parent.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        parent.layoutParams = params
        bottomSheetDialog!!.show()
    }

    private var progressBar: ProgressBar? = null
    private var progressDialog: AlertDialog? = null
    fun showCircleProgressDialog(view: View) {
        progressBar = ProgressBar(this, null,0,R.style.Widget_AppCompat_ProgressBar_Horizontal)
        progressBar?.max = 100
        progressDialog = with(AlertDialog.Builder(this)) {
            setTitle("请稍候")
            setMessage("正在努力加载页面")
            setView(progressBar)
            create()
        }
        progressDialog!!.show()
        mExcutor = ScheduledThreadPoolExecutor(1)
        mExcutor!!.scheduleAtFixedRate(mRunnable(), 100, 100, TimeUnit.MILLISECONDS)
    }

    fun showPopUpMenu(view: View) {
        with(PopupMenu(this, view)) {
            inflate(R.menu.dialog_pop_memu)
            setOnMenuItemClickListener {item ->
                when (item.itemId) {
                    R.id.menu1 -> show("menu1 clicked")
                    R.id.menu2 -> show("menu2 clicked")
                    else -> {
                    }
                }
                false
            }
            show()
        }
    }

    fun showBarProgressDialog(view: View) {
        val dialog = ProgressDialog(this)
        dialog.setTitle("请稍候")
        dialog.setMessage("正在努力加载页面")
        dialog.max = 100
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog.show()
    }

    var mExcutor: ScheduledThreadPoolExecutor? = null
    var progressValue = 0
    var progressState = false

    inner class mRunnable : Runnable {
        override fun run() {
            proHandler.sendEmptyMessage(1)
        }
    }

    var proHandler = Handler {
        progressValue++
        if (progressValue < 101) {
            Log.e(TAG, "当前进度值：$progressValue")
            progressBar!!.progress = progressValue
        } else {
            progressDialog?.dismiss()
            mExcutor?.shutdownNow()
            progressState = false
            progressValue = 0
        }
        false
    }

    fun show(message: String) {
        Snackbar.make(findViewById(R.id.root_view), message, Snackbar.LENGTH_SHORT).show()
    }

}