package com.walletline.di.util

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatchers(
    val database: CoroutineDispatcher,
    val disk: CoroutineDispatcher,
    val network: CoroutineDispatcher,
    val ui: CoroutineDispatcher
)