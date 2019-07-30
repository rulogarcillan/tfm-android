@file:Suppress("UNCHECKED_CAST")

package tuppersoft.com.data.repositories

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson

object SharedPreferencesRepository {

    /**
     * Guarda cualquier valor primitivo en el shared preferences
     */
    fun <T> savePreference(mContext: Context, key: String, value: T) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        val editor = sharedPreferences.edit()

        when (value) {
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            is String -> editor.putString(key, value as String)
            else -> return
        }
        editor.apply()
    }

    /**
     * Guarda un objeto
     */
    fun savePreferenceObject(mContext: Context, key: String, value: Any) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    /**
     * Carga cualquier valor primitivo del shared preferences
     */
    fun loadPreferenceObject(mContext: Context, key: String, defaultValue: Any): Any {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        val gson = Gson()
        val json = sharedPreferences.getString(key, "")
        return gson.fromJson(json, defaultValue::class.java)
    }

    /**
     * Carga cualquier valor primitivo del shared preferences
     */
    fun <T> loadPreference(mContext: Context, key: String, defaultValue: T): T {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            else -> defaultValue
        }
    }

    /**
     * Elimina la shared preference para la key dada
     */
    fun removePreference(context: Context, key: String) {
        val mySPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = mySPrefs.edit()
        if (mySPrefs.contains(key)) {
            editor.remove(key)
            editor.apply()
        }
    }
}