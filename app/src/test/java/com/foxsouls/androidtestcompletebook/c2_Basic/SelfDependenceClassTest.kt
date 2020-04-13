package com.foxsouls.androidtestcompletebook.c2_Basic

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

class SelfDependenceClassTest {

    @Test
    fun doSomething_returnsOTHER() {
        val targetClass: SelfDependenceClass = spyk(SelfDependenceClass())
        every { targetClass.fetchOtherThing() } returns "OTHER"

        targetClass.doSomething()
        verify { targetClass.doWithOtherTihing("OTHER") }
    }
}