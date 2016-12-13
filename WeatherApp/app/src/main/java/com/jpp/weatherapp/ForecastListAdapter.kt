package com.jpp.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by jpperetti on 13/12/16.
 */

class ForecastListAdapter(val items: List<String>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    // Single return type!!!
    override fun getItemCount(): Int = items.size

    /**
     * Define an inner class for the view holder
     */
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
