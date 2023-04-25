package com.walletline.data.mapper

import com.walletline.data.dto.response.RegisterError
import com.walletline.data.dto.response.RegisterSuccess
import com.walletline.domain.model.ApiResponse
import com.walletline.domain.model.RegisteredError
import com.walletline.domain.model.RegisteredSuccess

fun RegisterError.toRegisteredError(): RegisteredError = with(this) {
    RegisteredError(
        emailError = emailErrors,
        deviceError = deviceErrors
    )
}

fun RegisterSuccess.toRegisteredSuccess(): RegisteredSuccess = with(this) {
    RegisteredSuccess(
        devCode = developer_code,
        trackCode = tracking_code
    )
}

fun ApiResponse<RegisterSuccess, RegisterError>.toDomain(): ApiResponse<RegisteredSuccess, RegisteredError> = when (this) {
    is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(code = code, errorBody = errorBody?.toRegisteredError())
    ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
    ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
    is ApiResponse.Success -> ApiResponse.Success(body = body.toRegisteredSuccess())
}