package com.school.tmproject.repository

import com.school.tmproject.DTO.login.UserLoginCommand
import be.school.quizzapplication.dto.login.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ILoginRepository {
    @POST("users/login") // Update with your endpoint path
    @Headers("Accept: text/plain", "Content-Type: application/json")
    suspend fun login(@Body user: UserLoginCommand): retrofit2.Response<UserLoginResponse>
}