package be.school.quizzapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.ActivityModifyBinding
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class ModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modify)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val quizId = intent.getIntExtra("id", -1)
        performGetQuiz(quizId)
    }

    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performGetQuiz(id: Int) {
        val intent = Intent(this, QuizzManagerActivity::class.java)
        lifecycleScope.launch {
            try {
                val response: GetAllQuizzesResponse = apiService.getById(id)
//                binding.editTextId.text.append(response.id.toString())
            } catch (e: Exception) {
                Log.e("Nicolas Login", "Login error: ${e.message}")
            }
            Log.i("Nicolas Login", "Out login...")
        }
    }
}