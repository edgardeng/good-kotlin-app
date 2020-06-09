import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.edgar.movie.R


class CustomDialog : Dialog, View.OnClickListener {

    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    private var mTvTitle: TextView? = null
    private var mTvMessage: TextView? = null
    private var mTvCancel: TextView? = null
    private var mTvConfirm: TextView? = null
    private var title: String? = null
    private var message: String? = null
    private var cancel: String? = null
    private var confirm: String? = null
    private var cancelListener: IOnCancelListener? = null
    private var confirmListener: IOnConfirmListener? = null;
    private var height = 0

    fun setTitle(t: String) {
        this.title = t
    }
    fun setMessage(t: String) {
        this.message = t
    }

    fun setCancel(cancel: String, listener: IOnCancelListener) {
        this.cancel = cancel;
        this.cancelListener = listener;
    }

    fun setConfirm( confirm: String, listener: IOnConfirmListener) {
        this.confirm = confirm;
        this.confirmListener = listener;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("CustomDialog","onCreate")
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_demo_dialog_custom)

        mTvTitle = findViewById<TextView>(R.id.tv_dialog_custom_title);
        mTvMessage = findViewById<TextView>(R.id.tv_dialog_custom_message);
        mTvCancel = findViewById<TextView>(R.id.tv_dialog_custom_cancel);
        mTvConfirm = findViewById<TextView>(R.id.tv_dialog_custom_confirm);
        if (!TextUtils.isEmpty(title)) {
            mTvTitle?.setText(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mTvMessage?.setText(message);
        }
        if (!TextUtils.isEmpty(cancel)) {
            mTvCancel?.setText(cancel);
        }
        if (!TextUtils.isEmpty(confirm)) {
            mTvConfirm?.setText(confirm);
        }

        mTvCancel?.setOnClickListener(this);
        mTvConfirm?.setOnClickListener(this);
    }

    override fun show() {
        window!!.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        if (height == 0){
            window!!.attributes.height = 320
        } else {
            window!!.attributes.height = height
        }
        window!!.attributes.width = 320
        window!!.setGravity(Gravity.CENTER)
        super.show()
    }
    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.tv_dialog_custom_cancel -> {
                cancelListener?.onCancel(this);
                dismiss();
            }
            R.id.tv_dialog_custom_confirm -> {
                confirmListener?.onConfirm(this);
                dismiss();
            }
        }
    }

    interface IOnCancelListener {
        fun onCancel(dialog: CustomDialog);
    }

    interface IOnConfirmListener {
        fun onConfirm(dialog: CustomDialog);
    }
}