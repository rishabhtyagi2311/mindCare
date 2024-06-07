package com.example.sample

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class QuoteViewModel : ViewModel() {
    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val quoteService = QuoteService.create()
    init {
        fetchRandomQuote()
    }

    fun fetchRandomQuote() {
        viewModelScope.launch {
            try {
                _quote.value = quoteService.getRandomQuote()
                _errorMessage.value = null
            } catch (e: IOException) {
                _errorMessage.value = "Network error, please try again"
                _quote.value = null
            } catch (e: HttpException) {
                _errorMessage.value = "Service error, please try again"
                _quote.value = null
            }
        }
    }
}