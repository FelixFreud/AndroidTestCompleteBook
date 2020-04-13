package com.foxsouls.androidtestcompletebook.c3_Applicated

import android.text.TextUtils
import java.lang.IllegalArgumentException

class InputChecker {
    fun isValid(text: String): Boolean {
        if (TextUtils.isEmpty(text)) return throw IllegalArgumentException("Cannot be null")
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))
        //引数が3文字以上の半角英数ならばtrueを返す
    }
}