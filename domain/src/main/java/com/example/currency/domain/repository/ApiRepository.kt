package com.example.currency.domain.repository

import com.example.currency.domain.entity.Currency

interface ApiRepository {
    /**
     * Get list of currency
     *
     */
    suspend fun getApiCurrencies(): List<Currency>
}
