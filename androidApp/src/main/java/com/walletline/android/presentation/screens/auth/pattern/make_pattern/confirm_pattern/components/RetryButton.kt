package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.Constants

@Composable
fun RetryButton(
    text: String,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .width(width)
            .height(height),
        enabled = enabled,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        colors = ButtonDefaults.buttonColors(
            //containerColor = containerColor.copy(alpha = Constants.DisabledButtonAlpha),
            containerColor = Color.Transparent,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = Constants.DisabledButtonAlpha),
            disabledContentColor = contentColor
        ),
        // border stroke width is 1.5
        border = BorderStroke(
            width = 1.5.dp,
            //color = MaterialTheme.colorScheme.onBackground
            color = MaterialTheme.customColor.neutrals.main,

            ),
        interactionSource = remember { MutableInteractionSource() }
    ) {

        Text(
            modifier = Modifier,
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                lineHeight = Dimen.TextLineHeight20,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.main,
        )
    }


}