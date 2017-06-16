package com.jpp.kotlin.weatherapp.data.server

import com.jpp.kotlin.weatherapp.data.db.ForecastDb
import com.jpp.kotlin.weatherapp.domain.datasource.ForecastDataSource
import com.jpp.kotlin.weatherapp.domain.model.Forecast
import com.jpp.kotlin.weatherapp.domain.model.ForecastList

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {


    /**
     * Executes the request to the server to retrieve the ForecastList and stores it in the database.
     * Then, it returns the recently data stored.
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertFromDataModel(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

}
