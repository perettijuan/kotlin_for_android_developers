package com.jpp.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpp.weatherapp.R
import com.jpp.weatherapp.domain.model.Forecast
import com.jpp.weatherapp.domain.model.ForecastList
import com.jpp.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso


import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Class that extends RecyclerView.Adapter and provides functionallity to show
 * the list of weather conditions.
 *
 * It receives a ForecastList that contains the model to show
 * and a function as parameter that serves as click listener
 *
 * Created by jpperetti on 13/1/17.
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    /**
     * Overrides the function onCreateViewHolder. Note that ViewGroup might be null (indicated
     * by the ? after the parameter type). So, check nullity in Kotlin is used inside the
     * method's definition (!!).
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }


    /**
     * Overrides the function onBindViewHolder. Note that the holder parameter might be null,
     * so I'm using !! again.
     */
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindForecast(weekForecast[position])
    }


    /**
     * Overrides the function getItemCount. Note that I'm not using the brackets here, since
     * Kotlin allows the no-brackets syntax if the function can be calculated in a single line.
     */
    override fun getItemCount(): Int = weekForecast.size()

    /**
     * Inner class definition. Note that it extends RecyclerView.ViewHolder and, since this base class
     * declare a single argument constructor, I'm indicating that textView (the parameter
     * in the constructor of this inner class) is the parameter passed to that constructor.
     */
    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {


        fun bindForecast(forecast: Forecast) {
            with(forecast) {

                // Thanks to kotlin android extentions we don't need find!
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}"
                itemView.minTemperature.text = "${low}"

                // assign the item click listener -> operator overload invoke allows me to just call itemClick()
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}