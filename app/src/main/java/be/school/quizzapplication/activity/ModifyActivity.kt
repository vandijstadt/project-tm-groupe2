package be.school.quizzapplication.activity

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.ActivityModifyBinding
import be.school.quizzapplication.repository.IQuizzRepository
import be.school.quizzapplication.repository.IThemeRepository
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

        performGetAllTheme()


    }


    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performGetQuiz(quizId: Int) {
        lifecycleScope.launch {
            try {
                val response: GetAllQuizzesResponse = apiService.getById(quizId)
                Log.i("Nicolas quiz", response.toString())
                binding.editTextId.text.append(response.id.toString())
                binding.editTextTitle.text.append(response.title)
                binding.editTextDescription.text.append(response.description)
                binding.multiAutoCompleteTextViewTheme.text.append(response.theme.title)
            } catch (e: Exception) {
                Log.e("Nicolas quiz", "quiz error: ${e.message}")
            }
            Log.i("Nicolas quiz", "Out quiz...")
        }
    }

    private val apiServiceTheme = RetrofitFactory.instance.create(IThemeRepository::class.java)
    private fun performGetAllTheme() {
        lifecycleScope.launch {
            try {
                val themes = apiServiceTheme.getAll()
                val themeTitles = themes.map { it.title } // Extract theme titles
                val adapter = ArrayAdapter(
                    this@ModifyActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    themeTitles
                )
                binding.multiAutoCompleteTextViewTheme.setAdapter(adapter)
                binding.multiAutoCompleteTextViewTheme.threshold = 1
            } catch (e: Exception) {
                Log.e("Nicolas quiz", "quiz error: ${e.message}")
                // Handle error, e.g., show an error message
            }
        }
    }
}