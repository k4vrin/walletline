package com.walletline.di

import com.walletline.data.remote.firebase.auth.FirebaseAuthClientImpl
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.common.CommonUseCase
import com.walletline.domain.use_case.validator.ValidateUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
     val authUseCase: AuthUseCase by inject()
     val commonUseCase: CommonUseCase by inject()
     val validateUseCase: ValidateUseCase by inject()
     val firebaseAuthClientImpl by lazy { FirebaseAuthClientImpl() }
}

fun initIosKoin() = initKoin()