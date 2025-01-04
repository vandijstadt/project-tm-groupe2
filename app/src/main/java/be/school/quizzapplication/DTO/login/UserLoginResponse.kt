package be.school.quizzapplication.DTO.login

data class UserLoginResponse(
    val id:Int,
    val username:String,
    val email:String,
    val role:String,
)