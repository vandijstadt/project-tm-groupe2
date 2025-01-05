package be.school.quizzapplication.dto.login

data class UserLoginResponse(
    val id:Int,
    val username:String,
    val email:String,
    val role:String,
)