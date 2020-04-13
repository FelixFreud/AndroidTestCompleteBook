package com.foxsouls.androidtestcompletebook.c2_Basic

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class WeatherForecastRecordingTestWithMockK {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: WeatherRecorder

    @Before
    fun setUp() {
        recorder = mockk(name = "MockRecorder", relaxUnitFun = true)
        val satellite = Satellite()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun recordCurrentWeather_assertRecorderCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        verify(exactly = 1){ recorder.record(any<String>()) }
    }

    @Test
    fun recordCurrentWeather_assertReturnValueMatched() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        verify(exactly = 1){ recorder.record("Weather is RAINY") }
    }

    @Test
    fun recordCurrentWeather_assertArgumentCapture() {
        val slot = slot<Record>()
        weatherForecast.recordCurrentWeather(37.580006, -122.345106, true)
        verify(exactly = 1){ recorder.record(capture(slot)) }
        assert(slot.captured.description == "Weather is RAINY")
    }
}