package com.example.currency.presentation.currency.model

import com.example.currency.domain.entity.Currency
import com.example.currency.domain.entity.CurrencyType

/**
 * UI State for currencies
 *
 **/
data class CurrencyUIState(
    val selectedCurrency: CurrencyType = CurrencyType.CRYPTO,
    val isLoading: Boolean = false,
    val cryptoList: List<Currency> = emptyList(),
    val fiatList: List<Currency> = emptyList(),
)
