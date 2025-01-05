package be.school.quizzapplication.repository

import be.school.quizzapplication.dto.theme.ThemeResponse
import retrofit2.http.GET

interface IThemeRepository {
    @GET("themes")
    suspend fun getAll(): List<ThemeResponse>

}