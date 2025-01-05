package be.school.quizzapplication.dto.quizz

data class UpdateQuizzesResponse(
    val id: Int,
    val title: String,
    val description: String,
    val theme: Themes,
    val user: Users
)

data class Themes(val id: Int)
data class Users(val id: Int)