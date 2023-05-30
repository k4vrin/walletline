package com.walletline.di

import org.koin.core.context.stopKoin
import org.koin.test.check.checkModules
import kotlin.test.AfterTest
import kotlin.test.Test

class KoinTest {

    @Test
    fun checkAllModules() {
        initIosKoin().checkModules()
    }

    @AfterTest
    fun breakDown() {
        stopKoin()
    }
}