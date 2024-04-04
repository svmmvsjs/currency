package com.example.currency.data.mapper

import com.example.currency.data.proto.CurrencyInfo
import com.example.currency.domain.entity.Currency
import javax.inject.Inject

/**
 * Convert data for currencies
 */
internal class CurrencyApiMapper @Inject constructor() {
    /**
     * Invoke.
     *
     * @param apiModel currency api model
     */
    operator fun invoke(
        apiModel: List<CurrencyInfo>,
    ) = apiModel.map {
        Currency(
            id = it.id.uppercase(),
            name = it.name,
            symbol = it.symbol.uppercase(),
            code = it.code,
        )
    }
}
