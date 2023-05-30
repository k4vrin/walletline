package com.walletline.di

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.robolectric.annotation.Config
import kotlin.test.AfterTest
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
@Category(CheckModuleTest::class)
@Config(sdk = [33])
class KoinTest {

    @Test
    fun checkAllModules() {
        Firebase.initialize(context = ApplicationProvider.getApplicationContext<Application>())
        initKoin().checkModules() {
            withInstance(ApplicationProvider.getApplicationContext<Application>())
        }
    }

    @AfterTest
    fun breakdown() {
        stopKoin()
    }
}