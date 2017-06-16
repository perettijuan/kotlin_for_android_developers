package com.jpp.kotlin.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.jpp.kotlin.weatherapp.ui.App
import org.jetbrains.anko.db.*

/**
 * Example of overloading a constructor:
 * WE can instantiate a instance of ForecastDbHelper by:
 * 1 - val dbHelper1 = ForecastDbHelper() //  will use App.instance
 * 2 - val dbHelper2 = ForecastDbHelper(anotherContext) // For tests, for example
 */
class ForecastDbHelper(ctx: Context = App.instance)
    : ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        // by lazy means that the object will not be created until it's used
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create a table only if db is != null
        // since db might be null (SQLiteDatabase?), we use it only if it's not null (db?)
        db?.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)
        db?.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}