package be.school.quizzapplication.dto.quizz

import be.school.quizzapplication.dto.question.QuestionResponse
import be.school.quizzapplication.dto.theme.ThemeResponse
import com.school.tmproject.DTO.login.UserLoginResponse

data class GetAllQuizzesResponse(val id: Int, val title: String, val description: String, val theme: ThemeResponse, val questions: List<QuestionResponse>, val user: UserLoginResponse,
    val themeFirstLetter: Char = theme.title.replaceFirstChar {
        it.uppercase()
    }.first())