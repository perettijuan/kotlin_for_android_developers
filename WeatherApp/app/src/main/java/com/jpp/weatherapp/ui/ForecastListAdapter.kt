package com.jpp.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.jpp.weatherapp.domain.model.ForecastList

/**
 * Class that extends RecyclerView.Adapter and provides functionallity to show
 * the list of weather conditions.
 *
 * It receives a List of Strings as constructor parameters.
 *
 * Created by jpperetti on 13/1/17.
 */
class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    /**
     * Overrides the function onCreateViewHolder. Note that ViewGroup might be null (indicated
     * by the ? after the parameter type). So, check nullity in Kotlin is used inside the
     * method's definition (!!).
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent!!.context))
    }


    /**
     * Overrides the function onBindViewHolder. Note that the holder parameter might be null,
     * so I'm using !! again.
     */
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(weekForecast.dailyForecast[position]) {
            holder!!.textView.text = "$date - $description - $high/$low"
        }
    }


    /**
     * Overrides the function getItemCount. Note that I'm not using the brackets here, since
     * Kotlin allows the no-brackets syntax if the function can be calculated in a single line.
     */
    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    /**
     * Inner class definition. Note that it extends RecyclerView.ViewHolder and, since this base class
     * declare a single argument constructor, I'm indicating that textView (the parameter
     * in the constructor of this inner class) is the parameter passed to that constructor.
     */
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}