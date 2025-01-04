package be.school.quizzapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import be.school.quizzapplication.activity.LoginActivity
import be.school.quizzapplication.activity.MyQuizzRecyclerViewAdapter
import be.school.quizzapplication.activity.QuizzManagerActivity
import be.school.quizzapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.buttonLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonListGestionQuizz.setOnClickListener{
            val intent = Intent(this, QuizzManagerActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogout.setOnClickListener {
            logout();
        }
    }


    private fun logout() {
        val jwtSharedPref = getSharedPreferences("JWT", MODE_PRIVATE)
        val editor = jwtSharedPref.edit()
        editor.remove("id");
        editor.remove("username");
        editor.remove("email");
        editor.remove("roles");
        editor.apply()

    }
}