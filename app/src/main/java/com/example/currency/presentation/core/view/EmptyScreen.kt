package com.example.currency.presentation.core.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currency.R
import com.example.currency.presentation.core.ui.Pink40

@Composable
internal fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Inside,
            contentDescription = "",
        )

        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            text = "No results",
            color = Pink40,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
