package com.jpp.weatherapp.ui

// synthetic import -> allows creating views without findView() methods!
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jpp.weatherapp.R
import com.jpp.weatherapp.domain.command.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)



        async() {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                // use of lambda expression
                forecastList.adapter = ForecastListAdapter(result) { toast(it.date) }
            }
        }
    }

}
