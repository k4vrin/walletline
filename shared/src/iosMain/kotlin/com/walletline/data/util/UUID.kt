package com.walletline.data.util

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()