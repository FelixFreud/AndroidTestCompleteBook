package com.foxsouls.androidtestcompletebook.c2_Basic

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import org.junit.Before

class DeclarativeMockingExample {
    @Before
    fun setUp() {
        val satellite = mockk<Satellite>(name = "MockSatellite") {
            every { getWeather(any(), any()) } returns Weather.CLOUDY
            every { getWeather(37.580006,-122.345106) } returns Weather.SUNNY
            every { getWeather(37.792872, -122.396915) } returns Weather.RAINY
        }
    }
}

class UseAnnotationExample() {
    @MockK
    lateinit var satellite: Satellite
    @MockK
    lateinit var recorder: WeatherRecorder
    @SpyK
    val formatter: WeatherFormatter = WeatherFormatter()

    lateinit var  weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { satellite.getWeather(any(), any()) } returns Weather.SUNNY
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }
}
