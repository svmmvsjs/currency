package com.example.currency.domain.usecase

import com.example.currency.domain.entity.Currency
import com.example.currency.domain.repository.ApiRepository
import com.example.currency.domain.repository.CurrencyRepository
import javax.inject.Inject

/**
 * Get currencies
 */
class FetchCurrenciesUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val currencyRepository: CurrencyRepository,
) {
    /**
     * Get and persist currencies
     *
     */
    suspend operator fun invoke(): List<Currency> = apiRepository.getApiCurrencies().also {
        currencyRepository.persistCurrencies(it)
    }
}
