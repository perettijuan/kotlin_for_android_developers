package com.jpp.kotlin.weatherapp.ui.utils

import android.content.Context
import android.view.View

// extension function for View classes: return the Context instance
val View.ctx: Context
    get() = context