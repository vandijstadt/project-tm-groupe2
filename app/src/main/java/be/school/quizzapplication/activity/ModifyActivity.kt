package be.school.quizzapplication.activity

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.DTO.quizz.Themes
import be.school.quizzapplication.DTO.quizz.UpdateQuizzesResponse
import be.school.quizzapplication.DTO.quizz.Users
import be.school.quizzapplication.DTO.theme.ThemeResponse
import be.school.quizzapplication.databinding.ActivityModifyBinding
import be.school.quizzapplication.repository.IQuizzRepository
import be.school.quizzapplication.repository.IThemeRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class ModifyActivity : AppCompatActivity() {
    private lateinit var themes: List<ThemeResponse>
    private lateinit var binding: ActivityModifyBinding
    private var user: Users = Users(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizId = intent.getIntExtra("id", -1)
        performGetQuiz(quizId)

        performGetAllTheme()

        setUpListener()

    }

    private fun setUpListener() {
        binding.buttonUpdate.setOnClickListener {
//            binding.editTextId.text.append(response.id.toString())
//            binding.editTextTitle.text.append(response.title)
//            binding.editTextDescription.text.append(response.description)
//            binding.multiAutoCompleteTextViewTheme.text.append(response.theme.title)
            val id: Int = binding.editTextId.text.toString().toIntOrNull()!!

//            binding.multiAutoCompleteTextViewTheme.text.toString()
            val theme: Themes = Themes(1)
            val quiz: UpdateQuizzesResponse = UpdateQuizzesResponse(
                id,
                binding.editTextTitle.text.toString(),
                binding.editTextDescription.text.toString(),
                theme,
                user
            )
            performUpdate(quiz)
        }
    }


    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performUpdate(quiz: UpdateQuizzesResponse) {
        lifecycleScope.launch {
            try {
                val response = apiService.update(quiz)
            } catch (e: Exception) {
                Log.e("Nicolas quiz", "quiz error: ${e.message}")
            }
            Log.i("Nicolas quiz", "Out quiz...")
        }
    }
    private fun performGetQuiz(quizId: Int) {
        lifecycleScope.launch {
            try {
                val response: GetAllQuizzesResponse = apiService.getById(quizId)
                Log.i("Nicolas quiz", response.toString())
                binding.editTextId.text.append(response.id.toString())
                binding.editTextTitle.text.append(response.title)
                binding.editTextDescription.text.append(response.description)
                binding.spinner.get(themes.indexOfFirst { it.title == response.theme.title })
                user = Users(response.user.id)
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
                themes = apiServiceTheme.getAll()

                val adapter = ArrayAdapter(
                    this@ModifyActivity,
                    android.R.layout.simple_spinner_item,  // Utiliser le spinner_item.xml si personnalisé
                    themes.map { it.title }  // Affiche uniquement le titre dans la liste
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter

            } catch (e: Exception) {
                Log.e("Nicolas quiz", "quiz error: ${e.message}")
                // Handle error, e.g., show an error message
            }
        }
    }
}