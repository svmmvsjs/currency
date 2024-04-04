package com.example.currency.data.gateway

import com.example.currency.data.proto.Currencies
import com.example.currency.data.proto.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyGateway {

    /**
     * Monitor currencies
     * @return Flow of currencies inside the datastore
     */
    fun monitorCurrencies(): Flow<Currencies>

    /**
     * Put new currency
     *
     * @param currency
     */
    suspend fun putCurrency(currency: CurrencyInfo)

    /**
     * Put all new currencies
     *
     * @param currencies
     */
    suspend fun putCurrencies(currencies: Currencies)

    /**
     * Remove all currencies
     */
    suspend fun clearCurrencies()

    /**
     * Remove currency
     *
     * @param currency
     */
    suspend fun removeCurrency(currency: CurrencyInfo)
}
