package com.example.currency.presentation.currency

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.currency.presentation.currency.view.CurrencyRoute

internal const val currencyRoute = "currency"

fun NavGraphBuilder.currencyScreen(
    onBackIconPressed: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable(currencyRoute) {
        CurrencyRoute(
            onBackIconPressed = onBackIconPressed,
            onNavigateBack = onNavigateBack
        )
    }
}

/**
 * Navigation for Currency Route
 */
fun NavController.navigateToCurrency(navOptions: NavOptions? = null) {
    this.navigate(navOptions = navOptions, route = currencyRoute)
}
