package com.jpp.kotlin.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.jpp.kotlin.weatherapp.R
import com.jpp.kotlin.weatherapp.extentions.ctx
import com.jpp.kotlin.weatherapp.extentions.slideEnter
import com.jpp.kotlin.weatherapp.extentions.slideExit
import com.jpp.kotlin.weatherapp.ui.App
import org.jetbrains.anko.toast

interface ToolbarManager {

    /*
     * Interfaces in Kotlin can define properties, but since interfaces are stateless, no value
     * can be assigned to the property. This means, in the end, that the implementing class
     * will need to override this property.
     * But, and this is an IMPORTANT but, we can use this property in this interface!
     */

    val toolbar: Toolbar


    /*
     * We can also implement stateless properties: this means, properties that don't need the
     * backing field provided by the implementing classes.
     */
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    /*
     * This is MAGIC: interfaces in Kotlin can have functions!
     */

    /**
     * Initializes the toolbar
     */
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown")
            }
            true
        }
    }

    /**
     * Enables the navigation icon
     */
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up }
    }

    private fun createUpDrawable() =
            with(DrawerArrowDrawable(toolbar.ctx)) {
                progress = 1f
                this
            }


    /**
     * Attaches the toolbar to a scroll
     */
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if(dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}