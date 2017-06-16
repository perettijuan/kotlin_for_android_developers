package com.jpp.kotlin.weatherapp.ui

import android.app.Application
import com.jpp.kotlin.weatherapp.extentions.DelegatesExt

class App : Application() {
    companion object {
        // this means that we can only assign instance once. Otherwise, an exception will be thrown
        var instance: App by
                DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}