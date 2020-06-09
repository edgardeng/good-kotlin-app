import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 修改状态栏为全透明
 *
 * @param activity
 */
@TargetApi(19)
fun transparencyBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.TRANSPARENT)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val window: Window = activity.window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }
}

/**
 * 状态栏亮色模式，设置状态栏黑色文字、图标，
 * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
 *
 * @param activity
 * @return 1:MIUUI 2:Flyme 3:android6.0
 */
fun StatusBarLightMode(activity: Activity): Int {
    var result = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        if (MIUISetStatusBarLightMode(activity, true)) {
            //小米
            result = 1
        } else if (FlymeSetStatusBarLightMode(activity.window, true)) {
            //魅族
            result = 2
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.0以上
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            result = 3
        } else {
            //其他的都设置状态栏成半透明的,以下设置半透明是调用第三方ImmersionBar库的，根据个人需求更改，
//            ImmersionBar.with(activity).statusBarDarkFont(true, 0.5f).init()
        }
    }
    return result
}

/**
 * 设置状态栏图标为深色和魅族特定的文字风格
 * 可以用来判断是否为Flyme用户
 *
 * @param window 需要设置的窗口
 * @param dark   是否把状态栏文字及图标颜色设置为深色
 * @return boolean 成功执行返回true
 */
fun FlymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
    var result = false
    if (window != null) {
        try {
            val lp: WindowManager.LayoutParams = window.getAttributes()
            val darkFlag: Field = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags: Field = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.setAccessible(true)
            meizuFlags.setAccessible(true)
            val bit: Int = darkFlag.getInt(null)
            var value: Int = meizuFlags.getInt(lp)
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            window.setAttributes(lp)
            result = true
        } catch (e: Exception) {
        }
    }
    return result
}

/**
 * 需要MIUIV6以上
 *
 * @param activity
 * @param dark     是否把状态栏文字及图标颜色设置为深色
 * @return boolean 成功执行返回true
 */
fun MIUISetStatusBarLightMode(activity: Activity, dark: Boolean): Boolean {
    var result = false
    val window: Window? = activity.window
    if (window != null) {
        val clazz: Class<*> = window::class.java
        try {
            var darkModeFlag = 0
            val layoutParams =
                Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field: Field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField: Method = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            if (dark) {
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
            }
            result = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                if (dark) {
                    activity.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
            }
        } catch (e: Exception) {
        }
    }
    return result
}