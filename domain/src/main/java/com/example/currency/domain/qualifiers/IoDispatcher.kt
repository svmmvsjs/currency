package com.example.currency.domain.qualifiers

import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * Io dispatcher
 * refer to [Dispatchers.IO]
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher