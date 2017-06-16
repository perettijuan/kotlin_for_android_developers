package com.jpp.kotlin.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.jpp.kotlin.weatherapp.R
import com.jpp.kotlin.weatherapp.domain.commands.RequestForecastCommand
import com.jpp.kotlin.weatherapp.extentions.DelegatesExt
import com.jpp.kotlin.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // interface method!
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)

        // interface method!
        attachToScroll(forecastList)


    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
            }
            forecastList.adapter = adapter
            title = "${result.city} (${result.country})"
        }
    }

}
