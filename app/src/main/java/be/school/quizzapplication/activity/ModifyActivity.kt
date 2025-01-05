package be.school.quizzapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.databinding.ActivityModifyBinding
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class ModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizId = intent.getIntExtra("id", -1)
        performGetQuiz(quizId)
    }


    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performGetQuiz(quizId: Int) {
        val intent = Intent(this, QuizzManagerActivity::class.java)
        lifecycleScope.launch {
            try {
                Log.i("Nicolas quiz", "Id : $quizId")
                val response: GetAllQuizzesResponse = apiService.getById(quizId)
                Log.i("Nicolas quiz", response.toString())
                binding.editTextId.text.append(response.id.toString())
                binding.editTextTitle.text.append(response.title)
            } catch (e: Exception) {
                Log.e("Nicolas quiz", "quiz error: ${e.message}")
            }
            Log.i("Nicolas quiz", "Out quiz...")
        }
    }
}