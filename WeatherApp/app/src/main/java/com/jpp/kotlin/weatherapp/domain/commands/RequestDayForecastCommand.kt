package com.jpp.kotlin.weatherapp.domain.commands

import com.jpp.kotlin.weatherapp.domain.datasource.ForecastProvider
import com.jpp.kotlin.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<Forecast> {


    override fun execute() = forecastProvider.requestForecast(id)
}
