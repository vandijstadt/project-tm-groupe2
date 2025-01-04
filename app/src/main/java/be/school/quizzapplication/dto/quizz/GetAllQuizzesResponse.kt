package be.school.quizzapplication.DTO.quizz

import be.school.quizzapplication.DTO.question.QuestionResponse
import be.school.quizzapplication.DTO.theme.ThemeResponse
import be.school.quizzapplication.DTO.login.UserLoginResponse

data class GetAllQuizzesResponse(val id: Int, val title: String, val description: String, val theme: ThemeResponse, val questions: List<QuestionResponse>, val user: UserLoginResponse)