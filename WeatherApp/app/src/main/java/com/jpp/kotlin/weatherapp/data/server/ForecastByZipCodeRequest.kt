package com.jpp.kotlin.weatherapp.data.server

class ForecastByZipCodeRequest(val zipCode: Long) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }


    fun execute(): ForecastResult {
        val forecastJsonStr = java.net.URL(COMPLETE_URL + zipCode).readText()
        android.util.Log.d(javaClass.simpleName, forecastJsonStr)
        return com.google.gson.Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }

}