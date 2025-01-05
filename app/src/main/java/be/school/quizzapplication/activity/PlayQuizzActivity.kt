package be.school.quizzapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.question.QuestionResponse
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.MainActivity
import be.school.quizzapplication.databinding.ActivityPlayQuizzBinding
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class PlayQuizzActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayQuizzBinding
    private val quizzRepository = RetrofitFactory.instance
        .create(IQuizzRepository::class.java)

    private lateinit var quizz: GetAllQuizzesResponse
    private lateinit var questions: List<QuestionResponse>
    private var i = 0
    private var nbGoodAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizzBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPlayQuizzActivityResult.visibility = View.GONE
        binding.btnPlayQuizzActivityToMainActivity.visibility = View.GONE
        binding.btnPlayQuizzActivityToQuizzlist.visibility = View.GONE

        val quizzId = intent.getIntExtra("QUIZZ_ID", -1)

        if (quizzId != -1) {
            loadQuizzData(quizzId)
        } else {
            Log.e("Mickaël","Erreur lors de la récupération de l'id du quizz")
            finish()
        }


    }

    private fun loadQuestionOnUI() {
        var question = questions.get(i)
        binding.tvPlayQuizzActivityQuestion.text = question.questionText
        var answers = listOf(question.correctChoice,question.incorrectChoice1,question.incorrectChoice2,question.incorrectChoice3).shuffled()
        val buttons = listOf(
            binding.btnPlayQuizzActivityAnswer1,
            binding.btnPlayQuizzActivityAnswer2,
            binding.btnPlayQuizzActivityAnswer3,
            binding.btnPlayQuizzActivityAnswer4
        )

        buttons.forEachIndexed { index, button ->
            button.text = answers[index]
            button.setOnClickListener{
                answerQuestion(button.text.toString())
            }
        }
    }

    private fun answerQuestion(answer: String) {
        if(i>=0 && i<questions.size){
            var question = questions[i]
            if(answer===question.correctChoice){
                nbGoodAnswers++
            }
            i++
            if(i>=questions.size) showResult()
            else loadQuestionOnUI()
        }
    }

    private fun showResult() {
        binding.tableLayout.visibility = View.GONE
        binding.tvPlayQuizzActivityQuestion.visibility = View.GONE

        binding.tvPlayQuizzActivityResult.text = "Vous avez répondu correctement à ${nbGoodAnswers} questions sur ${questions.size}"
        binding.tvPlayQuizzActivityResult.visibility = View.VISIBLE

        binding.btnPlayQuizzActivityToMainActivity.setOnClickListener{
            Intent(this,MainActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.btnPlayQuizzActivityToMainActivity.visibility = View.VISIBLE

        binding.btnPlayQuizzActivityToQuizzlist.setOnClickListener{
            Intent(this,QuizzManagerActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.btnPlayQuizzActivityToQuizzlist.visibility = View.VISIBLE
    }

    private fun loadQuizzData(quizzId: Int) {
        lifecycleScope.launch {
            try {
                quizz = quizzRepository.getById(quizzId)
                questions = quizz.questions
                Log.i("Mickaël",quizz.toString())
                loadQuestionOnUI()
            }
            catch (e: Exception){
                Log.e("Mickaël","${e.message}")
            }
        }
    }
}