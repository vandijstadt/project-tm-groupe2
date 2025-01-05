package be.school.quizzapplication.dto.login

data class UserLoginCommand(
    val username: String,
    val password: String,
)
