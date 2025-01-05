package be.school.quizzapplication.DTO.quizz

data class UpdateQuizzesResponse(
    val id: Int,
    val title: String,
    val description: String,
    val theme: Themes,
    val users: Users?
)

data class Themes(val id: Int)
data class Users(val id: Int)