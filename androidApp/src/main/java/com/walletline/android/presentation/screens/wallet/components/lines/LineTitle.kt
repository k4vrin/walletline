package com.walletline.android.presentation.screens.wallet.components.lines

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.theme.customColor

@Composable
fun LineTitle(){
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            "Line:", style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
        )
        Text(
            "Saves", style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
        )

        Spacer(Modifier.weight(1f))

        Text(
            "$99", style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
        )
        Text(
            ".00", style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
        )

    }
}


@Preview(showBackground = true)
@Composable
fun LineTitlePreview() {
    LineTitle()
}
