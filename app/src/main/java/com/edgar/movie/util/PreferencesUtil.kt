import android.content.Context
import android.content.SharedPreferences
import com.edgar.movie.MainApplication

object PreferencesUtil {
    private val name = "APP_Config"

    private val prefs: SharedPreferences by lazy {
        MainApplication.instance.applicationContext.getSharedPreferences(
            name,
            Context.MODE_PRIVATE
        )
    }

//    /**
//     * 获取存放数据
//     * @return 值
//     */
//    @Suppress("UNCHECKED_CAST")
//    fun getValue(key: String, default: Any): Any = with(prefs) {
//        return when (default) {
//            is Int -> getInt(key, default)
//            is String -> getString(key, default)
//            is Long -> getLong(key, default)
//            is Float -> getFloat(key, default)
//            is Boolean -> getBoolean(key, default)
//            else -> throw IllegalArgumentException("SharedPreferences 类型错误")
//        }
//    }
//
//    fun getString(key: String, default: String = ""): String {
//        return getValue(key, default) as String
//    }
//

    fun getDarkMode(context: Context): String? {
        val prefs: SharedPreferences = context.getSharedPreferences(
                "settings",
                Context.MODE_PRIVATE
            )
        return prefs.getString("dark_mode", "default")
    }

    fun setDarkMode(string: String, context: Context)  {
        val prefs: SharedPreferences = context.getSharedPreferences(
            "settings",
            Context.MODE_PRIVATE
        )
        prefs.edit().putString("dark_mode", string).apply()
    }

//
//    fun getLong(key: String, default: Long = 0): Long {
//        return getValue(key, default) as Long
//    }
//
//    fun getBoolean(key: String, default: Boolean = false): Boolean {
//        return getValue(key, default) as Boolean
//    }
//
//    fun getFloat(key: String, default: Float = 0f): Float {
//        return getValue(key, default) as Float
//    }
}