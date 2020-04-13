package com.foxsouls.androidtestcompletebook.c2_Basic

/*テスト対象*/
class WeatherForecast(val satellite: Satellite,
                      val recorder: WeatherRecorder,
                      val formatter: WeatherFormatter
) { //テストダブルで差し替えられるように引数をとる
    //val satellite = Satellite()

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }
    fun shouldBringUmbrella(latitude: Double, longitude: Double): Boolean {
        val weather = satellite.getWeather(latitude, longitude)
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

        fun recordRawCurrentWeather() {
        val weather = satellite.getWeather()
        recorder.record(weather)
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
    fun recordCurrentWeather(latitude: Double, longitude: Double) {
        val weather = satellite.getWeather(latitude, longitude)
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
    fun recordCurrentWeather(latitude: Double, longitude: Double, useRecord: Boolean) {
        when (useRecord) {
            true -> {
                val weather = satellite.getWeather(latitude, longitude)
                val formatted = formatter.format(weather)
                recorder.record(Record(formatted))
            }
            false -> {
                recordCurrentWeather(latitude, longitude)
            }
        }
    }
}

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class Record(val description: String)


/*元々の実装*/
open class Satellite {  //openにする
    open fun getWeather(): Weather {    //openにする
        return Weather.RAINY //仮実装
    }
    open fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.RAINY //仮実装
    }
}
open class WeatherRecorder {    //openにする
    open fun record(weather: Weather) { //openにする
        //仮実装
    }
    open fun record(weather: String) { //openにする
        //仮実装
    }
    open fun record(record: Record) {
        //仮実装
    }
}
open class WeatherFormatter {   //openにする
    open fun format(weather: Weather): String = "Weather is $weather"   //openにする
}
