package be.school.quizzapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.GetAllQuizz.QuizzGetAllResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.ActivityPlayQuizzBinding
import be.school.quizzapplication.dto.question.QuestionResponse
import be.school.quizzapplication.dto.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class PlayQuizzActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayQuizzBinding
    private val quizzRepository = RetrofitFactory.instance
        .create(IQuizzRepository::class.java)

    private lateinit var quizz: GetAllQuizzesResponse
    private lateinit var questions: List<QuestionResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizzBinding.inflate(layoutInflater)

        val quizzId = intent.getIntExtra("QUIZZ_ID", -1)

        if (quizzId != -1) {
            loadQuizzData(quizzId)
        } else {
            Log.e("Mickaël","Erreur lors de la récupération de l'id du quizz")
            finish()
        }

        questions = quizz.questions
    }

    private fun loadQuizzData(quizzId: Int) {
        lifecycleScope.launch {
            try {
                quizz = quizzRepository.getById(quizzId)
            }
            catch (e: Exception){
                Log.e("Mickaël","${e.message}")
            }
        }
    }
}