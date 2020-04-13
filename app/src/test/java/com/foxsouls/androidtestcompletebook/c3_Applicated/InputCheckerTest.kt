package com.foxsouls.androidtestcompletebook.c3_Applicated

import org.junit.Test

import org.junit.Assert.*
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.lang.IllegalArgumentException
import org.assertj.core.api.Assertions.*

class InputCheckerTest {

    @Test
    fun isValid_givenBlank_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { InputChecker().isValid("") }
            .withMessage("Cannot be blank")
    }
}