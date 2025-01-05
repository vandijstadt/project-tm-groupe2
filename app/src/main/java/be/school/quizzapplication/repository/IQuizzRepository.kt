package be.school.quizzapplication.repository

import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.DTO.quizz.UpdateQuizzesResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path

interface IQuizzRepository {
    @GET("quizzes")
    suspend fun getAll(): List<GetAllQuizzesResponse>

    @GET("quizzes/{id}")
    suspend fun getById(@Path("id") quizzId: Int): GetAllQuizzesResponse

    @DELETE("quizzes/{id}")
    suspend fun delete(@Path("id") quizzId: Int): retrofit2.Response<Unit>

    @PUT("quizzes")
    suspend fun update(@Body quizz: UpdateQuizzesResponse): retrofit2.Response<Unit>
}