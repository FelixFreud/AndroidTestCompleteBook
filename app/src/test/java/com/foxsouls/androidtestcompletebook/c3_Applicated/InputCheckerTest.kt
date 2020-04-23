package com.foxsouls.androidtestcompletebook.c3_Applicated

import android.app.Instrumentation
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.foxsouls.androidtestcompletebook.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.lang.IllegalArgumentException
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 25, maxSdk = 28)
@RunWith(RobolectricTestRunner::class)
class InputCheckerTest {
    @Test
    fun isValid_givenBlank_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { InputChecker().isValid("") }
            .withMessage("Cannot be blank")
    }
}

@RunWith(AndroidJUnit4ClassRunner::class)
class JetpackTest {
   @Test
    fun gettingContextTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val appName = context.getString(R.string.app_name)
        assertThat(appName).isEqualTo("AndroidTestCompleteBook")
    }
}