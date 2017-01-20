package com.jpp.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jpp.weatherapp.R
import com.jpp.weatherapp.domain.model.Forecast
import com.jpp.weatherapp.domain.model.ForecastList
import com.jpp.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

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
    class ViewHolder(val view: View, val itemClick: (Forecast) -> Unit ) : RecyclerView.ViewHolder(view) {

        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "${high}"
                minTemperatureView.text = "${low}"

                // assign the item click listener -> operator overload invoke allows me to just call itemClick()
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}