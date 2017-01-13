package com.jpp.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*;

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
        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)
    }
}
