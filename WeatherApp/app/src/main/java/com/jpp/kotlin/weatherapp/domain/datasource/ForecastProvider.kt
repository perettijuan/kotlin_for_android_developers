package com.jpp.kotlin.weatherapp.domain.datasource

import com.jpp.kotlin.weatherapp.data.db.ForecastDb
import com.jpp.kotlin.weatherapp.data.server.ForecastServer
import com.jpp.kotlin.weatherapp.domain.model.Forecast
import com.jpp.kotlin.weatherapp.domain.model.ForecastList
import com.jpp.kotlin.weatherapp.extentions.firstResult

class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }


    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    /**
     * Function generified using a non-nullable type.
     * Receive a function that uses a ForecastDataSource to return a nullable
     * object of the generic type (f: (ForecastDataSource) -> T?)
     */
    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}
