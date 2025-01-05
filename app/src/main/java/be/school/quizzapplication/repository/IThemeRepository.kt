package be.school.quizzapplication.repository

import be.school.quizzapplication.DTO.theme.ThemeResponse
import retrofit2.http.GET

interface IThemeRepository {
    @GET("themes")
    suspend fun getAll(): List<ThemeResponse>

}