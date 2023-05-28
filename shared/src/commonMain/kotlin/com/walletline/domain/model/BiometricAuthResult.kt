package com.walletline.domain.model

data class BiometricAuthResult(
    val isSuccessful: Boolean,
    val biometricType: BiometricType?,
    val error: BiometricError?,
)

enum class BiometricType {
    Unknown,
    DeviceCredential,
    Biometric
}

enum class BiometricError {
    UnavailableHardware,
    UnavailableBiometricSensor,
    MissingHardware,
    TooManyAttempts,
    TooManyAttemptsPermanent,
    Cancelled,
    NoBiometric,
    NoCredential,
    NotEnoughStorage,
    Timeout,
    SensorUnable
}