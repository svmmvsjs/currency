package com.example.currency.presentation.currency.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currency.R
import com.example.currency.domain.entity.Currency
import com.example.currency.presentation.core.ui.Pink40

@Composable
fun CurrencyListItem(
    currency: Currency,
    onClicked: (String) -> Unit = {},
) {
    Row(modifier = Modifier
        .clickable {
            onClicked(currency.id)
        }
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically)
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Inside,
            contentDescription = "",
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = currency.name,
            color = Pink40,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = currency.code ?: currency.symbol,
            color = Pink40,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
