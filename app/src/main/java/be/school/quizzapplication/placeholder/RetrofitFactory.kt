package com.school.tmproject.placeholder

import be.school.quizzapplication.utils.AppCookieJar
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val URL:String = "http://10.0.2.2:5094/v1/" // Intern a android studio
//    private const val URL: String = "http://192.168.1.5:5094/v1/" // Secondaire

    private val cookieJar: AppCookieJar = AppCookieJar()

    private val client = OkHttpClient.Builder()
    .cookieJar(cookieJar)
        .build()

    var instance: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun addTokenToClient(token: String){
        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }

        val newClient = client.newBuilder()
            .addInterceptor(interceptor)
            .cookieJar(cookieJar)
            .build()

        instance = Retrofit.Builder()
            .baseUrl(URL)
            .client(newClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCookies(): List<Cookie> {
        return cookieJar.getCookies()
    }
}