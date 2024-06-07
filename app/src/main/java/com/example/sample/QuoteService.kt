package com.example.sample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class Quote(
    val id: String,
    val content: String,
    val author: String
)
interface QuoteService {
    @GET("/random")
    suspend fun getRandomQuote(): Quote

    companion object {
        private const val BASE_URL = "https://api.quotable.io"

        fun create(): QuoteService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuoteService::class.java)
        }
    }

}