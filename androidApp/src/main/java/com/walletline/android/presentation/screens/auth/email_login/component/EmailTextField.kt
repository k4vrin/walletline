package com.walletline.android.presentation.screens.auth.email_login.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.sdp

@Composable
fun EmailTextField(
    text: String,
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
                label = {
                    Text(
                        text = stringResource(R.string.email_address),
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.customColor.neutrals.six,
                    focusedTextColor = MaterialTheme.customColor.neutrals.six,
                    disabledTextColor = MaterialTheme.customColor.neutrals.six.copy(alpha = Constants.DisabledAlpha),
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedLabelColor = MaterialTheme.customColor.neutrals.three,
                    unfocusedLabelColor = MaterialTheme.customColor.neutrals.four,
                    errorLabelColor = MaterialTheme.customColor.error.main,
                    focusedLeadingIconColor = MaterialTheme.customColor.neutrals.five,
                    unfocusedLeadingIconColor = MaterialTheme.customColor.neutrals.five,
                    errorLeadingIconColor = MaterialTheme.customColor.error.main
                ),
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
                                else MaterialTheme.customColor.neutrals.two,
                                shape = RoundedCornerShape(Dimen.DefaultBorderRadius)
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        VerticalDivider(
                            isError = !errorMessage.isNullOrBlank(),
                            modifier = Modifier
                                .padding(
                                    top = MaterialTheme.padding.medium,
                                    bottom = MaterialTheme.padding.medium,
                                    start = Dimen.PhoneTextFieldDividerMarginStart
                                ),
                        )

                    }
                },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .height(Dimen.PhoneTextFieldHeight)
                            .width(Dimen.PhoneTextFieldDividerMarginStart),
                        contentAlignment = Alignment.Center,
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = stringResource(R.string.desc_email_icon)
                        )

                    }

                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier
                            .height(Dimen.PhoneTextFieldHeight)
                            .width(Dimen.PhoneTextFieldDividerMarginStart)
                            .alpha(
                                alpha = if (text.isNotBlank()) 1f else 0f
                            ),
                        onClick = {
                            onTextChange("")
                        }
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = stringResource(R.string.desc_delete_icon)
                        )

                    }
                }
            )
        }
    )
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.sdp,
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
    errorColor: Color = MaterialTheme.colorScheme.error,
    isError: Boolean = false,
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    val mColor = remember(isError) { if (isError) errorColor else color }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(targetThickness)
            .background(color = mColor, shape = RoundedCornerShape(50f))
    )
}


@Preview
@Composable
fun PhoneTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    var error: String? by remember {
        mutableStateOf(null)
    }
    WalletLineTheme {
        WalletLineBackground {
            AuthCard(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = MaterialTheme.padding.medium)
            ) {
                EmailTextField(
                    modifier = Modifier
                        .padding(
                            vertical = MaterialTheme.padding.large,
                            horizontal = MaterialTheme.padding.medium
                        ),
                    errorMessage = error,
                    text = text,
                    onTextChange = {
                        text = it; error = if (text.length > 5) "Dummy error" else null
                    },
                )

            }
        }
    }
}