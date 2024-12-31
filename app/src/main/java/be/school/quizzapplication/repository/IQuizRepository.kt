package com.school.tmproject.repository

import be.school.quizzapplication.DTO.GetAllQuizz.QuizzGetAllResponse
import com.school.tmproject.DTO.login.UserLoginCommand
import com.school.tmproject.DTO.login.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IQuizRepository {
    // LOGIN
    @POST("users/login")
    @Headers("Accept: text/plain", "Content-Type: application/json")
    suspend fun login(@Body user: UserLoginCommand): retrofit2.Response<UserLoginResponse>

    // Create
    @GET("quizzes")
    @Headers("Accept: text/plain", "Content-Type: application/json")
    suspend fun GetAllQuizz(): retrofit2.Response<QuizzGetAllResponse>
}