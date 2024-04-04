package com.example.currency.domain.repository

import com.example.currency.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun monitorPersistedCurrencies(): Flow<List<Currency>>

    suspend fun persistCurrencies(currencies: List<Currency>)
}
