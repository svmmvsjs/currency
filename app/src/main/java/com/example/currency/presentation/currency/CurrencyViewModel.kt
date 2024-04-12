package com.example.currency.presentation.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency.domain.usecase.FetchCurrenciesUseCase
import com.example.currency.domain.usecase.MonitorCurrenciesUseCase
import com.example.currency.presentation.currency.model.CurrencyUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * View Model for Currency
 */
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val monitorCurrenciesUseCase: MonitorCurrenciesUseCase,
    private val fetchCurrenciesUseCase: FetchCurrenciesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CurrencyUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        fetchCurrencies()
        monitorCurrencyList()
    }

    /**
     * Fetch remote currencies
     */
    private fun fetchCurrencies() = viewModelScope.launch {
        runCatching {
            fetchCurrenciesUseCase()
        }.onFailure {
            Timber.e(it, "Fetch Currencies Fail")
        }.also {
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    /**
     * Monitor cached currency
     */
    private fun monitorCurrencyList() = viewModelScope.launch {
        monitorCurrenciesUseCase()
            .catch {
                Timber.e(it, "Monitor Currencies Use Case Fail")
            }
            .distinctUntilChanged()
            .collectLatest { currencies ->
                _uiState.update {
                    it.copy(
                        cryptoList = currencies,
                        fiatList = currencies,
                    )
                }
            }
    }
}
