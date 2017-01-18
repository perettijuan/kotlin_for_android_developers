package com.jpp.weatherapp.data.server

import com.jpp.weatherapp.domain.model.ForecastList
import com.jpp.weatherapp.domain.model.Forecast as ModelForecast
import java.text.DateFormat
import java.util.*

/**
 * Mapper class that maps data layer classes to domain layer classes.
 */
public class ForecastDataMapper {


    /**
     * Converts a ForecastResult (data layer) to a ForecastList (domain layer)
     */
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }


    /**
     * Converts a list of server Forecast to a list of model Forecast
     */
    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }


    /**
     * Converts a Forecast server item to a Forecast item.
     */
    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }


    /**
     * Converts a Long that represents a date into a String representation.
     */
    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}