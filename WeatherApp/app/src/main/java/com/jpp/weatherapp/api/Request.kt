package com.jpp.weatherapp.api

import android.util.Log
import java.net.URL

/**
 * Created by jpperetti on 18/1/17.
 */

class Request {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID"
    }

    fun run() {
        Log.d("URl:", COMPLETE_URL)
        val forecastJsonStr = URL(COMPLETE_URL).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}
