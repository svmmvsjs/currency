package com.example.currency.data.di

import com.example.currency.data.datastore.CurrenciesDatastore
import com.example.currency.data.gateway.CurrencyGateway
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {
    @Binds
    abstract fun bindCurrencyGateway(implementation: CurrenciesDatastore): CurrencyGateway
}
