package be.school.quizzapplication.DTO.GetAllQuizz

data class QuizzGetAllResponse(
    val quizz: List<Quizz>,
)

data class Quizz(
    val id: Int,
    val title: String,
    val description: String,
    val themes: Themes,
    val user: Users,
    val questions: List<Question>,
)

data class Themes(
    val id: Int,
    val title: String
)

data class Users(
    val id: Int,
    val username: String,
    val email: String,
    val role: String,
)

data class Question(
    val id: Int,
    val questionText: String,
    val correctChoice: String,
    val incorrectChoice1: String,
    val incorrectChoice2: String,
    val incorrectChoice3: String,
)