package com.walletline.android.presentation.screens.wallet

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor

@Composable
fun LinesInfo() {

    Column {
        Row(verticalAlignment = Alignment.Bottom) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.customColor.neutrals.main)
                    .border(
                        width = 1.dp, color = MaterialTheme.customColor.neutrals.two,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(end = 16.dp)
            )
            Text(
                "Needs", style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.six,
            )
            Spacer(Modifier.weight(1f))
            Text(
                "50%", style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.six,
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp, start = 32.dp, end = 36.dp, bottom = 24.dp)){
            Column {
                Row(modifier = Modifier.padding(top = 24.dp)) {
                    Text(
                        "$560", style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.customColor.neutrals.six,
                    )
                    Text(
                        ".00", style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.customColor.neutrals.six,
                    )
                }
                Text(
                    "United States Dollar", style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.neutrals.three,
                )
            }
//            Image(painter = painterResource(id = R.drawable.ic_united_states), contentDescription = null)
        }
        Row{
            Text(
                "Debit", style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
            )
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.customColor.neutrals.six,
                        shape = RoundedCornerShape(12.dp)
                    )) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = Color.Unspecified

                )
            }
        }



    }
}


@Preview(showBackground = true)
@Composable
fun LinesInfoPreview() {
    LinesInfo()
}
