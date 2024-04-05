package com.example.currency.data.di

import com.example.currency.data.repository.ApiRepositoryImpl
import com.example.currency.data.repository.CurrencyRepositoryImpl
import com.example.currency.domain.repository.ApiRepository
import com.example.currency.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Singleton
    @Binds
    internal abstract fun bindCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository

    @Singleton
    @Binds
    internal abstract fun bindApiRepository(repository: ApiRepositoryImpl): ApiRepository
}
