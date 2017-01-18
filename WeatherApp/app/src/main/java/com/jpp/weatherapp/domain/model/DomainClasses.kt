package com.jpp.weatherapp.domain.model

// Represents a list of forecast in the domain layer.
data class ForecastList(val city: String,
                        val country: String,
                        val dailyForecast: List<Forecast>)

// Represents a Forecast in the domain layer.
data class Forecast(val date: String,
                    val description: String,
                    val high: Int,
                    val low: Int)