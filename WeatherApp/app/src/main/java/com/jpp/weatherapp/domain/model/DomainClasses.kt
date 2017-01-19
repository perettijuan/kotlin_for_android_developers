package com.jpp.weatherapp.domain.model

// Represents a list of forecast in the domain layer.
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {

    /**
     * get is an operator (symbolic operator) defined by Kotlin.
     * We can overload an operator by specifying the "operator" modifier with the function name.
     */
    operator fun get(position: Int): Forecast = dailyForecast[position]


    /**
     * Returns the size of the forecast list.
     */
    fun size(): Int = dailyForecast.size

}

// Represents a Forecast in the domain layer.
data class Forecast(val date: String,
                    val description: String,
                    val high: Int,
                    val low: Int)