package com.jpp.kotlin.weatherapp.extentions

import kotlin.reflect.KProperty

/**
 * This is an object that uses our delegated property
 */
object DelegatesExt {
    fun <T> notNullSingleValue() = com.jpp.kotlin.weatherapp.extentions.NotNullSingleValueVar<T>()
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