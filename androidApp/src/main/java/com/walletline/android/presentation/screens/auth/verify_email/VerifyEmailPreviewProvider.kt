package com.walletline.android.presentation.screens.auth.verify_email

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.walletline.domain.model.auth.OTPValidationMessage
import com.walletline.presentation.screens.auth.verify_email.VerifyEmailState

class VerifyEmailPreviewProvider : PreviewParameterProvider<VerifyEmailState> {

    override val values: Sequence<VerifyEmailState> = sequenceOf(
        VerifyEmailState(
            otp = "",
            otpError = null,
            email = "test@test.com",
            isTimerActive = true,
            isLoading = false,
            isActionsEnabled = false

        ),
        VerifyEmailState(
            otp = "1234",
            otpError = null,
            email = "test@test.com",
            isTimerActive = false,
            isLoading = false,
            isActionsEnabled = false
        ),
        VerifyEmailState(
            otp = "",
            otpError = OTPValidationMessage.NotEmpty,
            email = "test@test.com",
            isTimerActive = false,
            isLoading = true,
            isActionsEnabled = false
        ),
    )
}