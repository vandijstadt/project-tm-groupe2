package com.school.tmproject.placeholder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
        private const val URL:String = "http://10.0.2.2:5094/v1/" // Intern a android studio
//    private const val URL: String = "http://192.168.1.5:5094/v1/" // Secondaire

    val instance: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}