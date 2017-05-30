package com.jpp.kotlin.weatherapp.domain.commands

import com.jpp.kotlin.weatherapp.data.ForecastRequest
import com.jpp.kotlin.weatherapp.domain.mapper.ForecastDataMapper
import com.jpp.kotlin.weatherapp.domain.model.ForecastList

public class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.run())
    }

}
