package com.jpp.kotlin.weatherapp.domain.commands

import com.jpp.kotlin.weatherapp.domain.datasource.ForecastProvider
import com.jpp.kotlin.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}
