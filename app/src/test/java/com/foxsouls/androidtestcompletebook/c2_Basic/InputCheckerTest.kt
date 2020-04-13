package com.foxsouls.androidtestcompletebook.c2_Basic

import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(JUnit4::class) //デフォルトでこのテストランナーが適用される
class InputCheckerTest {
    private lateinit var target: InputChecker

    @Before //共通する初期化処理を行う
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {
    }

    @Test //Target#isVaild("foo")の検証
    fun isValid() {
        //val target = InputChecker()
        val actual = target.isValid("foo")
        assertThat(actual, `is`(true))
    }

    @Test //３文字未満で偽が返る
    fun isValid_givenLessThan3_returnsFalse() {
        //val target = InputChecker()
        val actual = target.isValid("ab")
        assertThat(actual, `is`(false))
    }

    @Test //３文字の英数のみで真が返る
    fun isValid_givenAlphabetic_returnsTrue() {
        val actual = target.isValid("abc")
    }

    @Test //３文字の数字のみで真が返る
    fun isValid_givenNumeric_returnsTrue() {
        val actual = target.isValid("123")
        assertThat(actual, `is`(true))
    }

    @Test //３文字以上で英数の組み合わせで真が返る
    fun isValid_givenAlphaNumeric_returnsTrue() {
        //val target = InputChecker()
        val actual = target.isValid("abc123")
        assertThat(actual, `is`(true))
    }

    @Test //３文字以上だが半角英数以外の文字が含まれると偽が返る
    fun isValid_givenInvalidCharacter_returnFalse() {
        val actual = target.isValid("abc@123")
        assertThat(actual, `is`(false))
    }

    @Test(expected = IllegalArgumentException::class) //例外の検証
    fun isValid_givenNull_throwsIllegalArgumentException() {
        target.isValid(null)
    }

    @Ignore("テスト対象が仮実装なので一時的にスキップ") //テストのスキップ
    @Test
    fun temporallySkipThisTest() {
        /* 略 */
    }
}