package com.jpp.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Aaaaaaaaaaaa",
            "Bbbbbbbbbbbb",
            "Cccccccccccc",
            "Dddddddddddd",
            "Eeeeeeeeeeee",
            "Ffffffffffff",
            "Gggggggggggg"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)
    }
}
