package com.example.currency.domain.usecase

import com.example.currency.domain.entity.Currency
import com.example.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Monitor currency from local cache
 */
class MonitorCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) {
    operator fun invoke(): Flow<List<Currency>> = currencyRepository.monitorPersistedCurrencies()
}
