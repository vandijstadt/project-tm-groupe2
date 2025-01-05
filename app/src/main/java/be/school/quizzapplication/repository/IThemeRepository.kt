package be.school.quizzapplication.repository

import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.DTO.theme.ThemeResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface IThemeRepository {
    @GET("themes")
    suspend fun getAll(): List<ThemeResponse>

}