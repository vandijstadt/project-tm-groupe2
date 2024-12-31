package be.school.quizzapplication.repository

import be.school.quizzapplication.dto.quizz.GetAllQuizzesResponse
import retrofit2.http.GET

interface IQuizzRepository {
    @GET("quizzes")
    suspend fun getAll(): List<GetAllQuizzesResponse>
}