package com.walletline.android.presentation.util

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.walletline.domain.model.BiometricAuthResult
import com.walletline.domain.model.BiometricError
import com.walletline.domain.model.BiometricType
import kotlinx.coroutines.suspendCancellableCoroutine

class BiometricClient {
    suspend fun showBiometricPrompt(context: Context): BiometricAuthResult =
        suspendCancellableCoroutine { cont ->
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setSubtitle("Login with your biometric credential")
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                )
                .build()

            val biometricPrompt = BiometricPrompt(
                context as FragmentActivity,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        cont.resumeWith(
                            result = Result.success(
                                BiometricAuthResult(
                                    isSuccessful = false,
                                    biometricType = null,
                                    error = when (errorCode) {
                                        BiometricPrompt.ERROR_HW_UNAVAILABLE -> BiometricError.UnavailableHardware
                                        BiometricPrompt.ERROR_CANCELED -> BiometricError.UnavailableBiometricSensor
                                        BiometricPrompt.ERROR_HW_NOT_PRESENT -> BiometricError.MissingHardware
                                        BiometricPrompt.ERROR_LOCKOUT -> BiometricError.TooManyAttempts
                                        BiometricPrompt.ERROR_LOCKOUT_PERMANENT -> BiometricError.TooManyAttemptsPermanent
                                        BiometricPrompt.ERROR_NEGATIVE_BUTTON -> BiometricError.Cancelled
                                        BiometricPrompt.ERROR_NO_BIOMETRICS -> BiometricError.NoBiometric
                                        BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL -> BiometricError.NoCredential
                                        BiometricPrompt.ERROR_NO_SPACE -> BiometricError.NotEnoughStorage
                                        BiometricPrompt.ERROR_TIMEOUT -> BiometricError.Timeout
                                        BiometricPrompt.ERROR_UNABLE_TO_PROCESS -> BiometricError.SensorUnable
                                        BiometricPrompt.ERROR_USER_CANCELED -> BiometricError.Cancelled
                                        BiometricPrompt.ERROR_VENDOR -> BiometricError.Cancelled
                                        else -> null
                                    }
                                )
                            )
                        )
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        cont.resumeWith(
                            result = Result.success(
                                BiometricAuthResult(
                                    isSuccessful = true,
                                    biometricType = when (result.authenticationType) {
                                        BiometricPrompt.AUTHENTICATION_RESULT_TYPE_UNKNOWN -> BiometricType.Unknown
                                        BiometricPrompt.AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL -> BiometricType.DeviceCredential
                                        BiometricPrompt.AUTHENTICATION_RESULT_TYPE_BIOMETRIC -> BiometricType.Biometric
                                        else -> null
                                    },
                                    error = null
                                )
                            )
                        )
                    }

                    override fun onAuthenticationFailed() {
                        cont.resumeWith(
                            result = Result.success(
                                BiometricAuthResult(
                                    isSuccessful = false,
                                    biometricType = null,
                                    error = null
                                )
                            )
                        )
                    }
                }
            )

            biometricPrompt.authenticate(promptInfo)
        }
}


