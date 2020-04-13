package com.foxsouls.androidtestcompletebook.c2_Basic

class SelfDependenceClass {
    fun doSomething() {
        val text = fetchOtherThing()
        doWithOtherTihing(text)
    }

    fun fetchOtherThing(): String {
        return "" //仮実装
    }

    fun doWithOtherTihing(text: String) {
        //仮実装
    }
}