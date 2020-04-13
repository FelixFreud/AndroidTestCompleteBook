package com.foxsouls.androidtestcompletebook.c2_Basic

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

class WeatherForecastTestWithMockK {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mockk(name = "MockSatellite")
        every { satellite.getWeather() } returns Weather.SUNNY
//        every { satellite.getWeather(any(), any()) } returns Weather.CLOUDY
                                            //ワイルドカードでデフォルトの戻り値を指定
        every { satellite.getWeather(any(), any()) } answers {
             if (firstArg() as Double in 20.424086..45.550999 && //第1引数を呼び出すfirstArg()
                secondArg() as Double in 122.933872..153.980789) { //第2引数を呼び出すsecondArg()
                 Weather.SUNNY //戻り値
             } else {
                 Weather.RAINY //戻り値
             }
        }
        every {
            satellite.getWeather(eq(37.580006), eq(-122.345106))
        } returns Weather.SUNNY     //eq()で具体的な値をマッチング
        every {
            satellite.getWeather(latitude = 37.792872, longitude = -122.396915)
        } returns Weather.RAINY     //引数名を指定して具体的な値をマッチング
        /*ワイルドカードの引数マッチャーを用いると、それ以前で指定した全ての引数マッチャーが上書きされるので呼び出す順番に注意*/

        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite,recorder, formatter)
    }

    @Test
    fun shouldBringUmbrella() {
        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenInJapan_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(35.669784, 139.817728)
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenBurlingame_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(37.580006, -122.3817728)
        assertThat(actual).isTrue()
    }

    @Test
    fun shouldBringUmbrella_throwsRuntimeException() {
        every { satellite.getWeather(any(), any()) } throws RuntimeException("ERROR")
        weatherForecast = WeatherForecast(satellite, WeatherRecorder(), WeatherFormatter())

        assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
            weatherForecast.shouldBringUmbrella(37.580005, -122.345106)
        }.withMessage("ERROR").withNoCause()
    }
}