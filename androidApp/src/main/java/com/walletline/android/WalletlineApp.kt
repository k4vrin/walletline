package com.walletline.android

import android.app.Application
import com.walletline.android.di.viewModelModule
import com.walletline.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WalletlineApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@WalletlineApp)
            modules(viewModelModule)
        }
    }
}