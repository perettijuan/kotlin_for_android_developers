package com.jpp.kotlin.weatherapp.data.db

import com.jpp.kotlin.weatherapp.domain.datasource.ForecastDataSource
import com.jpp.kotlin.weatherapp.domain.model.ForecastList
import com.jpp.kotlin.weatherapp.extentions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    /**
     * use is a lambda of ManagedSQLiteOpenHelper, that basically allows to apply a lambda function
     * over the database
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"

        /*
         * select(DayForecastTable.NAME).whereSimple(dailyRequest, zipCode.toString(), date.toString())
         * returns a Cursor.
         * Then, our parseList extension function, iterates over the Cursor and gets the rows
         * from it until reaches the last one.
         * For each row, it creates a map with the columns as keys and assigns the value to the corresponding key.
         * The map is then returned by the parser, so, in the end, dailyForecast is a map!
         */
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        // use() is a lambda, and the last line in a lambda is what the lambda returns
        // so, requestForecastByZipCode returns a ForecastList
        if (city != null)
            dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id).
                parseOpt { DayForecast(HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }


    /**
     * Clears all the data from the DB, converts domain to database model and insert the data
     */
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            /*
             insert is an anko function that asks for the table name and a varargs of Pair<String, Any>
             we create an extension function on MutableMap to convert from a Map to a vararg array
             the * indicates that the array will be decomposes to a vararg parameter
             */
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }

}
