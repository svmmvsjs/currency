package com.example.currency.data.repository

import com.example.currency.data.gateway.CurrencyGateway
import com.example.currency.data.mapper.CurrencyApiMapper
import com.example.currency.data.proto.Currencies
import com.example.currency.data.proto.CurrencyInfo
import com.example.currency.domain.entity.Currency
import com.example.currency.domain.qualifiers.IoDispatcher
import com.example.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CurrencyRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val currencyGateway: CurrencyGateway,
    private val currencyApiMapper: CurrencyApiMapper,
) : CurrencyRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun monitorPersistedCurrencies(): Flow<List<Currency>> =
        currencyGateway.monitorCurrencies().mapLatest { currencyApiMapper(it.currenciesList) }

    override suspend fun persistCurrencies(currencies: List<Currency>) = withContext(ioDispatcher) {
        currencyGateway.putCurrencies(Currencies.newBuilder().addAllCurrencies(
            currencies.map {
                CurrencyInfo.newBuilder().apply {
                    id = it.id
                    name = it.name
                    symbol = it.symbol
                    code = it.code
                }.build()
            }
        ).build())
    }
}
