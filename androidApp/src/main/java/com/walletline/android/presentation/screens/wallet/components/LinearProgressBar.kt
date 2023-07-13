package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.LinesInfo
import com.walletline.android.presentation.theme.customColor

@Composable
fun LinearProgressBar() {
    var progress = remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress.value,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value
    Column {
        Row {
                Text(
                    "$50", style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
                Text(
                    ".00", style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
                Text(
                    "paid", style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )


            Spacer(modifier = Modifier.weight(1f))


                Text(
                    "$99", style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
                Text(
                    ".00", style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
                Text(
                    "left", style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
            }

        LinearProgressIndicator(progress = animatedProgress, modifier = Modifier.padding(top = 8.dp, bottom = 11.dp))

        Row {

            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                "Last payment: ", style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
            )
            Text(
                "Feb 12", style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                "Due date: ", style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
            )
            Text(
                "Feb 12", style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinearProgressBarPreview() {
    LinearProgressBar()
}
