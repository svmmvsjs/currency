package com.example.currency.presentation.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.currency.presentation.currency.currencyRoute
import com.example.currency.presentation.currency.currencyScreen

private const val appGraph = "appGraph"

/**
 * Main NavGraph
 * @param navHostController
 */
@Composable
fun MainNavGraph(
    navHostController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    AppScaffold(snackBarHostState = snackbarHostState) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navHostController,
            route = appGraph,
            startDestination = currencyRoute,
        ) {
            currencyScreen(
                onBackIconPressed = navHostController::popBackStack,
                onNavigateBack = {
                    navHostController.popBackStack(
                        route = currencyRoute,
                        inclusive = false
                    )
                }
            )
        }
    }
}
