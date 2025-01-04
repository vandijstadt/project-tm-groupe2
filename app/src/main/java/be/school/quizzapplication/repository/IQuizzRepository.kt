package be.school.quizzapplication.repository

import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import retrofit2.http.GET

interface IQuizzRepository {
    @GET("quizzes")
    suspend fun getAll(): List<GetAllQuizzesResponse>
}