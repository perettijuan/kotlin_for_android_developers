package com.jpp.weatherapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jpp.weatherapp.R
import com.jpp.weatherapp.api.Request
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

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


        async() {
            Request().run()
            uiThread { longToast("Request Performed") }
        }
    }
}
