package com.jpp.kotlin.weatherapp.domain.model

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {

    val size: Int get() =  dailyForecast.size

    /**
     * This is an operator (particularly, an array-like operator). We can override operator functions
     * like this one to provide easy access to some properties that need calculation.
     * So, now we can access a Forecast in the list using ForecastList[i].
     * Check ForecastListAdapter to see the example in place.
     */
    operator fun get(position: Int): Forecast = dailyForecast[position]
}
data class Forecast(val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)