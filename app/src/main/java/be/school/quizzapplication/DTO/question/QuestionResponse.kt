package be.school.quizzapplication.DTO.question

data class QuestionResponse(val id: Int, val questionText: String, val correctChoice: String, val incorrectChoice1: String, val incorrectChoice2: String, val incorrectChoice3: String)
