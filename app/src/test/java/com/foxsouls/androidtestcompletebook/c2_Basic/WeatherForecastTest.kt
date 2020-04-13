package com.foxsouls.androidtestcompletebook.c2_Basic

import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.Before

class WeatherForecastTest {
    lateinit var satellite: Satellite
    lateinit var recorder: WeatherRecorder
    lateinit var formatter: WeatherFormatter
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setup() {
        satellite = Satellite()
        recorder = WeatherRecorder()
        formatter = WeatherFormatter()
    }


    /*スタブ：事前に定義した任意の値をテスト対象に与える*/
    class StubSatellite(val anyWeather: Weather): Satellite() { //Satelliteを継承
        override fun getWeather(): Weather {
            return anyWeather   //引数を返すようオーバーライド
        }
    }
    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    /*モック：テスト対象からの依存コンポーネントに対する出力を検証する*/
    class MockWeatherRecorder: WeatherRecorder() { //WeatherRecorderを継承
        var weather: Weather? = null    //記録用のプロパティ
        var isCalled = false            //呼び出されたかどうかのフラグ

        override fun record(weather: Weather) { //検証用の内容にオーバーライド
            this.weather = weather  //値の記録
            isCalled = true         //呼び出されたことを示すフラグを立てる
        }
    }
    @Test
    fun recordRawCurrentWeather_assertCalled() {
        val recorder = MockWeatherRecorder()
        weatherForecast = WeatherForecast(satellite,recorder,formatter)
        weatherForecast.recordRawCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather).isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }

    /*スパイ：依存コンポーネントに出力された値を記録しつつテスト対象に値を与える*/
    class SpyWeatherFormatter: WeatherFormatter() {
        var weather: Weather? = null
        var isCalled = false

        override fun format(weather: Weather): String {
            this.weather = weather
            isCalled = true
            return super.format(weather)    //実際の挙動は本来の依存コンポーネントを用いる
        }
    }
    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        val formatter = SpyWeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
        weatherForecast.recordCurrentWeather()

        val isCalled: Boolean = formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather).isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }

    /*フェイク：実際のコンポーネントと同等または極めて近い挙動をもつ実装オブジェクト*/
    /*ダミー：テスト対象とは依存関係がないが、コンパイルなどの都合上用意する必要があるオブジェクト*/
}