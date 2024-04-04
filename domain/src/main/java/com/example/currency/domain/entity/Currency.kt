package com.example.currency.domain.entity

/**
 * Currency
 *
 * @property id
 * @property name
 * @property symbol
 * @property code
 *
 */
data class Currency(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val code: String? = null,
)
