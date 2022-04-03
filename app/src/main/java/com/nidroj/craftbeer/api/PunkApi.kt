package com.nidroj.craftbeer.api

import com.nidroj.craftbeer.data.model.Beer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

interface PunkApi {

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/"
        private const val MAX_PER_PAGE = 80

        @Volatile
        private var INSTANCE: PunkApi? = null
        fun createApi(): PunkApi {

            INSTANCE?.let {
                Timber.d("Returning existing api instance")

                return it
            } ?: synchronized(this) {
                Timber.d("Returning new api instance")

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient().newBuilder()
                        .addInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BASIC
                            )
                        ).build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PunkApi::class.java)

                return INSTANCE!!

            }
        }
    }

    @GET("beers/")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = MAX_PER_PAGE
    ): List<Beer>
}