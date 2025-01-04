package com.school.tmproject.placeholder

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val URL:String = "http://10.0.2.2:5094/v1/" // Intern a android studio
//    private const val URL: String = "http://192.168.1.5:5094/v1/" // Secondaire
    private val client = OkHttpClient.Builder().build()

    val instance: Retrofit
        get() = Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun addTokenToClient(token: String){
        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${token}")
                .build()
            chain.proceed(newRequest)
        }

        val newClient = client.newBuilder()
            .addInterceptor(interceptor)
            .build()

        instance.newBuilder().client(newClient).build()
    }
}