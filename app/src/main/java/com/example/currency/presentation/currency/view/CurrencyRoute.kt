package com.example.currency.presentation.currency.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.currency.domain.entity.Currency
import com.example.currency.domain.entity.CurrencyType
import com.example.currency.presentation.core.ui.CurrencyTheme
import com.example.currency.presentation.core.ui.Pink80
import com.example.currency.presentation.core.view.EmptyScreen
import com.example.currency.presentation.core.view.LoadingScreen
import com.example.currency.presentation.currency.CurrencyViewModel

@Composable
internal fun CurrencyRoute(
    viewModel: CurrencyViewModel = hiltViewModel(),
    onBackIconPressed: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        LoadingScreen()
    } else {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(onClick = { viewModel.fetchCurrencies() }) {
                    Text(text = "Refresh")
                }

                Button(onClick = { viewModel.setCurrencyType(CurrencyType.CRYPTO) }) {
                    Text(text = "Crypto")
                }

                Button(onClick = { viewModel.setCurrencyType(CurrencyType.FIAT) }) {
                    Text(text = "Fiat")
                }
            }

            CurrencyListFragment(
                cryptoList = uiState.cryptoList,
                fiatList = uiState.fiatList,
                selectedCurrency = uiState.selectedCurrency,
                onBackIconPressed = onBackIconPressed,
            )
        }
    }
}

@Composable
internal fun CurrencyListFragment(
    modifier: Modifier = Modifier,
    cryptoList: List<Currency>,
    fiatList: List<Currency>,
    selectedCurrency: CurrencyType,
    onBackIconPressed: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        CurrencyList(
            currencyList = when (selectedCurrency) {
                CurrencyType.CRYPTO -> cryptoList
                CurrencyType.FIAT -> fiatList
            }
        )
    }
}

@Composable
private fun CurrencyList(
    modifier: Modifier = Modifier,
    currencyList: List<Currency>,
) {
    if (currencyList.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            item {
                SectionTitle {
                    Text(
                        text = "Currency List",
                        color = Pink80,
                    )
                }
            }

            items(currencyList) {
                CurrencyListItem(it)
                HorizontalDivider(
                    thickness = 1.dp,
                )
            }
        }
    } else {
        EmptyScreen()
    }
}

@Composable
private fun SectionTitle(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

/**
 * Preview
 */
@Preview
@Composable
fun CurrencyPreview() {
    CurrencyTheme {
        CurrencyListFragment(
            cryptoList = listOf(
                Currency(id = "crypto", name = "crypto.com", symbol = "mco"),
                Currency(id = "bitcoin", name = "bitcoin", symbol = "btc"),
                Currency(id = "ethereum", name = "ethereum", symbol = "eth"),
            ),
            fiatList = listOf(
                Currency(id = "singapore dollar", name = "singapore dollar", code = "sgd"),
                Currency(id = "euro", name = "euro", code = "eur"),
                Currency(id = "hong kong dollar", name = "hong kong dollar", code = "hkd"),
            ),
            selectedCurrency = CurrencyType.CRYPTO,
            onBackIconPressed = {},
        )
    }
}
