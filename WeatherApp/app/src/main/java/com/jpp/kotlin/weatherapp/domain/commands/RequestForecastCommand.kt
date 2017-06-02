package com.jpp.kotlin.weatherapp.domain.commands

import com.jpp.kotlin.weatherapp.data.server.ForecastRequest
import com.jpp.kotlin.weatherapp.domain.mapper.ForecastDataMapper
import com.jpp.kotlin.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.run())
    }

}
