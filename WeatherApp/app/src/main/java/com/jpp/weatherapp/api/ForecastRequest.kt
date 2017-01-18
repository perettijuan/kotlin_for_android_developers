package com.jpp.weatherapp.api

import com.google.gson.Gson
import com.jpp.weatherapp.data.ForecastResult
import java.net.URL

/**
 * Performs a request to the server to retrieve the forecast for an specified location.
 */
class ForecastRequest(val zipCode: String) {

    // Kotlin does not allow static members declaration. Companion objects are shared among all instances
    // of the class, allowing to declare static members to be used by the wrapping class.
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID"
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}
