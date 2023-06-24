package com.walletline.domain.model.auth

enum class UserCondition {
    FirstTime,
    OnBoarded,
    SignedInWithPattern,
    SignedInWithFinger,
    SignedIn,
}