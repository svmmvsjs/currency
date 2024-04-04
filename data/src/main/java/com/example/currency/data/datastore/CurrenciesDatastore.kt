package com.example.currency.data.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.currency.data.gateway.CurrencyGateway
import com.example.currency.data.proto.Currencies
import com.example.currency.data.proto.CurrencyInfo
import com.example.currency.domain.qualifiers.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

private const val CURRENCIES_DATASTORE_NAME = "currencies_datastore"

private val Context.currenciesDatastore: DataStore<Currencies> by dataStore(
    fileName = CURRENCIES_DATASTORE_NAME,
    serializer = CurrenciesSerializer,
)

class CurrenciesDatastore @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CurrencyGateway {

    override fun monitorCurrencies(): Flow<Currencies> = context.currenciesDatastore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.d("Error reading currencies.", exception)
                emit(Currencies.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun putCurrency(currency: CurrencyInfo) {
        withContext(ioDispatcher) {
            context.currenciesDatastore.updateData { currencies ->
                if (currencies.toBuilder().currenciesList.map { it.id }.contains(currency.id)) {
                    currencies
                } else {
                    currencies.toBuilder().addCurrencies(currency).build()
                }
            }
        }
    }

    override suspend fun putCurrencies(currencies: Currencies) {
        withContext(ioDispatcher) {
            context.currenciesDatastore.updateData {
                it.toBuilder().clearCurrencies().addAllCurrencies(currencies.currenciesList).build()
            }
        }
    }

    override suspend fun clearCurrencies() {
        withContext(ioDispatcher) {
            context.currenciesDatastore.updateData { currencies ->
                currencies.toBuilder().clearCurrencies().build()
            }
        }
    }

    override suspend fun removeCurrency(currency: CurrencyInfo) {
        withContext(ioDispatcher) {
            context.currenciesDatastore.updateData { currencies ->
                val existing = currencies.toBuilder().currenciesList
                if (existing.contains(currency)) {
                    val updated = existing.filter { it.id != currency.id }
                    currencies.toBuilder().clearCurrencies().addAllCurrencies(updated).build()
                } else {
                    currencies
                }
            }
        }
    }
}

/**
 * Serializer for object defined in currencies.proto
 */
object CurrenciesSerializer : Serializer<Currencies> {
    override val defaultValue: Currencies = Currencies.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Currencies {
        try {
            return Currencies.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Currencies, output: OutputStream) = t.writeTo(output)
}
