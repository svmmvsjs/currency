package com.example.currency.di

import com.example.currency.domain.qualifiers.ApplicationScope
import com.example.currency.domain.qualifiers.DefaultDispatcher
import com.example.currency.domain.qualifiers.IoDispatcher
import com.example.currency.domain.qualifiers.MainDispatcher
import com.example.currency.domain.qualifiers.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @Singleton
    @ApplicationScope
    @Provides
    fun provideCoroutineScope(@DefaultDispatcher defaultDispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(SupervisorJob() + defaultDispatcher)
}
