package com.walletline.android.presentation.screens.wallet.edit_wallet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun EditWalletDescriptionTextField(
    text: String,
    placeholderTextSrc: Int,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    enabled: Boolean = true,
) {

    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        textStyle = MaterialTheme.typography.bodyLarge,
        onValueChange = onTextChange,
        cursorBrush = if (!errorMessage.isNullOrBlank()) SolidColor(MaterialTheme.customColor.error.main)
        else SolidColor(MaterialTheme.customColor.main.main),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onNext = { focusManager.clearFocus() }),
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                isError = !errorMessage.isNullOrBlank(),
                contentPadding = TextFieldDefaults.contentPaddingWithLabel(start = MaterialTheme.padding.extraMedium),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.customColor.neutrals.three,
                    unfocusedTextColor = MaterialTheme.customColor.neutrals.three,
                    disabledTextColor = MaterialTheme.customColor.neutrals.three,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorLabelColor = MaterialTheme.customColor.error.main,
                    focusedLeadingIconColor = MaterialTheme.customColor.neutrals.five,
                    unfocusedLeadingIconColor = MaterialTheme.customColor.neutrals.five,
                    errorLeadingIconColor = MaterialTheme.customColor.error.main
                ),
                placeholder = {
                    Text(
                        text = stringResource(placeholderTextSrc),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.customColor.neutrals.three,
                        )
                },
                supportingText = {
                    AnimatedVisibility(
                        visible = !errorMessage.isNullOrBlank(),
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Text(
                            text = errorMessage ?: "",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                container = {
                    Box(
                        modifier = Modifier
                            .requiredHeight(Dimen.PhoneTextFieldHeight)
                            .border(
                                width = Dimen.PhoneTextFieldBorderWidth,
                                color = if (!errorMessage.isNullOrBlank()) MaterialTheme.colorScheme.error
                                else Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(Dimen.DefaultBorderRadius)
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                    }
                },

            )
        }
    )
}

