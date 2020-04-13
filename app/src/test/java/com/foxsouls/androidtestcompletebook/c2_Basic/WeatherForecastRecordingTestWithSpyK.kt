package com.foxsouls.androidtestcompletebook.c2_Basic

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class WeatherForecastRecordingTestWithSpyK {
    lateinit var weatherForecast: WeatherForecast
    lateinit var formatter: WeatherFormatter

    @Before
    fun setUp() {
        formatter = spyk(WeatherFormatter())
        val satellite = Satellite()
        val recorder = WeatherRecorder()
        weatherForecast = WeatherForecast(satellite,recorder, formatter)
    }

    @Test
    fun recordCurrentWeather_assertRecorderCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        verify(exactly = 1){ formatter.format(any()) }
    }

    @Test
    fun stubbingSpy() {
        val list: List<String> = spyk(arrayListOf())
        every { list[any()] } returns "HELLO"
        assert(list[0] == "HELLO")
    }
}