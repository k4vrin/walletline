package com.walletline.android.presentation.screens.wallet.components.lines

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.DashedBorderedButton
import com.walletline.android.presentation.theme.padding

@Composable
fun LinesList(
    onCreateLineClicked: () -> Unit,
    modifier: Modifier = Modifier,
    linesItems: List<com.walletline.domain.model.LineData>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(1000.dp),
        userScrollEnabled = false

    ) {
        items(linesItems.size) { lineIndex ->
            LineItem(
                modifier = Modifier.padding(
                    top = MaterialTheme.padding.small,
                )
            )
        }
        item {
            DashedBorderedButton(
                text = R.string.create_line,
                modifier = Modifier.padding(
                    top = MaterialTheme.padding.medium,
                    bottom = MaterialTheme.padding.extraLarge
                )
            ) {
                onCreateLineClicked()
            }
        }
    }
}