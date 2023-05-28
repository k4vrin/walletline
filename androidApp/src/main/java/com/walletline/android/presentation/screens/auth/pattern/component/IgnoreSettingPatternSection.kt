package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor

@Composable
fun IgnoreSettingPatternSection(
    modifier: Modifier = Modifier,
    onIgnoreClick: () -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.preferLessSccure),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.customColor.neutrals.two
        )
        TextButton(
            onClick = onIgnoreClick,
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.customColor.neutrals.main,
            )
        }

    }
}