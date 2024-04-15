package com.example.currency.data.repository

import com.example.currency.domain.entity.Currency
import com.example.currency.domain.qualifiers.IoDispatcher
import com.example.currency.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ApiRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ApiRepository {
    override suspend fun getApiCurrencies(): List<Currency> = withContext(ioDispatcher) {
        // Fake data instead of API gateway
        listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC", code = "BTC"),
            Currency(id = "ETH", name = "Ethereum", symbol = "ETH", code = "ETH"),
            Currency(id = "EUR", name = "Euro", symbol = "E", code = "EUR"),
            Currency(id = "USD", name = "US Dollar", symbol = "$", code = "USD"),
        )
    }
}
