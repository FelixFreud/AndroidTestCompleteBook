package com.foxsouls.androidtestcompletebook.c2_Basic

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.assertj.core.api.SoftAssertions
import java.lang.IllegalArgumentException

/*AssertJを使用するにはapp/build.gradleに追記が必要
 * dependencies {
 *   testImplementation 'org.assertj:assertj-core:3.10.0'
 * }
 */

class InputCheckerTestWithAssertJ {
    private lateinit var target: InputChecker

    @Before //共通する初期化処理を行う
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {
    }

    @Test //３文字以上で英数の組み合わせで真が返る
    fun isValid_givenAlphaNumeric_returnsTrue() {
        //val target = InputChecker()
        val actual = target.isValid("abc123")
        assertThat(actual).isTrue()
    }

    @Test //文字列のアサーション
    fun assert_Strings() {
        assertThat("TOKYO")
            .`as`("TEXT CHECK TOKYO")
            .isEqualTo("TOKYO")
            .isEqualToIgnoringCase("tokyo")
            .isNotEqualTo("KYOTO")
            .isNotBlank()
            .startsWith("TO")
            .endsWith("YO")
            .contains("OKY")
            .matches("[A-Z]{5}")
            .isInstanceOf(String::class.java)
    }

    @Test //メソッドチェーンの途中で偽となっても最後までテストケースを実行したい場合
    fun softAssert_Strings() {
        SoftAssertions().apply {
            assertThat("TOKYO")
                .`as`("TEXT CHECK TOKYO")
                .isEqualTo("OSAKA") //ここでテストケースは失敗
                .isEqualToIgnoringCase("tokyo")
                .isNotEqualTo("KYOTO")
                .isNotBlank()
                .startsWith("TO")
                .endsWith("YO")
                .contains("OKY")
                .matches("[A-Z]{4}") //ここでも失敗
                .isInstanceOf(String::class.java)
        }.assertAll()
    }

    @Test //数値のアサーション
    fun assert_NumericalValue() {
        assertThat(3.14159)
            .isNotZero()
            .isNotNegative()
            .isGreaterThan(3.0)
            .isLessThanOrEqualTo(4.0)
            .isBetween(3.0, 3.2)
            .isCloseTo(Math.PI, within(0.001))
    }

    @Test //IterableやCollectionのアサーション
    fun assert_IterableAndCollection(){

        val targetIterable = listOf("Giants", "Dodgers", "Athletics")

        assertThat(targetIterable)
            .hasSize(3)
            .contains("Dodgers")
            .containsOnly("Athletics", "Dodgers", "Giants")
            .containsExactly("Giants", "Dodgers", "Athletics")
            .doesNotContain("Padres")


        data class BallTeam(val name: String, val city: String, val stadium: String)
        val targetCollection = listOf(
            BallTeam("Giants","San Francisco","AT&T Park"),
            BallTeam("Dodgers","Los Angels","Dodger Stadium"),
            BallTeam("Angels","Los Angels","Angel Stadium"),
            BallTeam("Athletics","Oakland","Oakland Coliseum"),
            BallTeam("Padres","San Diego","Petco Park")
        )

        assertThat(targetCollection)
            .filteredOn { team -> team.city.startsWith("San") }
            .filteredOn { team -> team.city.endsWith("Francisco") }
            .extracting("name", String::class.java)
            .containsExactly("Giants")

        assertThat(targetCollection)
            .filteredOn { team -> team.city == "Los Angels" }
            .extracting("name", "stadium")
            .containsExactly(
                tuple("Dodgers", "Dodger Stadium"),
                tuple("Angels", "Angel Stadium")
            )
    }

    @Test //詳細な例外検証
    fun isValid_givenNull_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java) //キャッチしたい例外を指定
            .isThrownBy { target.isValid(null) } //例外を送出するメソッド
            .withMessage("Cannot be null")  //詳細メッセージの検証
            .withNoCause()  //他の例外経由で送出されていないことを確認
    }
}