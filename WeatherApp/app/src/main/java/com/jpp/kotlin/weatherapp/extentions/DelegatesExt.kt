package com.jpp.kotlin.weatherapp.extentions

import android.content.Context
import kotlin.reflect.KProperty

/**
 * This is an object that uses our delegated property
 */
object DelegatesExt {
    fun <T> notNullSingleValue() = com.jpp.kotlin.weatherapp.extentions.NotNullSingleValueVar<T>()

    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}

/**
 * Custom property delegated example. Not sure why this is useful.
 *
 * IN this case, this custom property delegate only let asing a value to a variable
 * once. If re-assignment is attempted, an exception is thrown.
 */
class NotNullSingleValueVar<T> {

    private var value: T? = null


    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }


    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: T) {
        this.value = if(this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}


class LongPreference(val context: Context, val name: String, val default: Long) {
    val prefs by lazy {context.getSharedPreferences("default", Context.MODE_PRIVATE)}

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : Long {
        return prefs.getLong(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(name, value).apply()
    }
}

class Preference<T>(val context: Context, val name: String, val default: T) {

    val prefs by lazy {context.getSharedPreferences("default", Context.MODE_PRIVATE)}

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : T {
        return findPreference(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }


    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when(default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }
        res as T
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when(value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}