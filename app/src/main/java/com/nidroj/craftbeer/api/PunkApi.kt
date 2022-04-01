package com.nidroj.craftbeer.api

import com.nidroj.craftbeer.data.model.BeersResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber

interface PunkApi {

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/"

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
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PunkApi::class.java)

                return INSTANCE!!

            }
        }
    }

    @GET("beers")
    suspend fun getBeers(): Response<BeersResponse>//todo pagination (empty if no beers), logging?
}