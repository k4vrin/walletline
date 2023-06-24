package com.walletline.data.util

import java.util.UUID

actual fun randomUUID(): String = UUID.randomUUID().toString()