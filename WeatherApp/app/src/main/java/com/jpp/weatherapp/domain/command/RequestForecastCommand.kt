package com.jpp.weatherapp.domain.command

import com.jpp.weatherapp.data.server.ForecastDataMapper
import com.jpp.weatherapp.data.server.ForecastRequest
import com.jpp.weatherapp.domain.model.ForecastList

/**
 * Command implementation for requesting the forecast for an specific zipCode.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {


    @Override
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}