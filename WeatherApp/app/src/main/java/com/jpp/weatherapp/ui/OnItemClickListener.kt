package com.jpp.weatherapp.ui

import com.jpp.weatherapp.domain.model.Forecast

interface OnItemClickListener {

    // override the operator invoke
    operator fun invoke(forecast: Forecast)
}