package com.example.currency.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.currency.presentation.core.ui.CurrencyTheme

@Composable
fun AppScaffold(
    snackBarHostState: SnackbarHostState,
    content: @Composable (PaddingValues) -> Unit
) {
    CurrencyTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            snackbarHost = {
                SnackbarHost(snackBarHostState) { data ->
                    Snackbar(snackbarData = data)
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                content(padding)
            }
        }
    }
}
