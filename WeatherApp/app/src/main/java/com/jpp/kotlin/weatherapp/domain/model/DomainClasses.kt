package com.jpp.kotlin.weatherapp.domain.model

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    val size: Int get() =  dailyForecast.size

    /**
     * This is an operator (particularly, an array-like operator). We can override operator functions
     * like this one to provide easy access to some properties that need calculation.
     * So, now we can access a Forecast in the list using ForecastList[i].
     * Check ForecastListAdapter to see the example in place.
     *
     * Note that we don't declare the returning type (: Forecast): we're letting the compiler
     * to infer the return type. This is valid for any function!
     */
    operator fun get(position: Int) = dailyForecast[position]
}
data class Forecast(val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)