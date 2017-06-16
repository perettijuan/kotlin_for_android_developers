package com.jpp.kotlin.weatherapp.domain.datasource

import com.jpp.kotlin.weatherapp.domain.model.Forecast
import com.jpp.kotlin.weatherapp.domain.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}