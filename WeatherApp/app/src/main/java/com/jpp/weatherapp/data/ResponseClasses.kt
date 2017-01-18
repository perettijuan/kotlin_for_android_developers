package com.jpp.weatherapp.data

/**
 * Data classes for the API responses.
 *
 * In short: data classes are the replacement of POJO model objects. They provide
 * some very useful properties, like for instance:
 *
 *   - Immutability.
 *   - equals() and hashcode() already implemented.
 *   - copy() implemented.
 *   - multi-declaration (componentX function) -> allow mapping objects into variables
 *
 */


// Represents the result of an API call to retrieved the forecast of a given location.
data class ForecastResult(val city: City,
                          val cod: String,
                          val message: Float,
                          val cnt: Int,
                          val list: List<Forecast>)

// Represents a City retrieved from the API.
data class City(val id: Long,
                val name: String,
                val coord: Coordinates,
                val country: String,
                val population: Int)

// Represents the coordinates of a City retrieved from the API.
data class Coordinates(val lon: Float,
                       val lat: Float)

// Represents a Forecast with data retrieved from the API.
data class Forecast(val dt: Long,
                    val temp: Temperature,
                    val pressure: Float,
                    val humidity: Float,
                    val weather: List<Weather>,
                    val speed: Float,
                    val deg: Int,
                    val clouds: Int,
                    val rain: Float)

// Represents the Temperature for a given forecast retrieved from the API.
data class Temperature(val day: Float,
                       val min: Float,
                       val max: Float,
                       val night: Float,
                       val eve: Float,
                       val morn: Float)

// Represents a Weather retrieved from the API.
data class Weather(val id: Long,
                   val main: String,
                   val description:
                   String, val icon: String)