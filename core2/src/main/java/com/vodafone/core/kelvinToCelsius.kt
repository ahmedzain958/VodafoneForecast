package com.vodafone.core

import java.util.Locale

fun kelvinToCelsius(kelvin: Double): Double {
    val celsius = kelvin - 273.15
    return String.format(Locale.getDefault(), "%.2f", celsius).toDouble()
}

