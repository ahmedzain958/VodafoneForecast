fun kelvinToCelsius(kelvin: Double): Double {
    val celsius = kelvin - 273.15
    return String.format("%.2f", celsius).toDouble()
}
