package be.school.quizzapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.MainActivity
import be.school.quizzapplication.databinding.ActivityLoginBinding
import be.school.quizzapplication.fragments.QuizzManagerFragment
import com.school.tmproject.DTO.login.UserLoginCommand
import com.school.tmproject.DTO.login.UserLoginResponse
import com.school.tmproject.placeholder.RetrofitFactory
import com.school.tmproject.repository.ILoginRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun login(userLoginResponse: UserLoginResponse) {
        val jwtSharedPref = getSharedPreferences("JWT", MODE_PRIVATE)
        val editor = jwtSharedPref.edit()
        editor.putInt("id", userLoginResponse.id)
        editor.putString("username", userLoginResponse.username)
        editor.putString("email", userLoginResponse.email)
        editor.putString("roles", userLoginResponse.role)
        editor.apply()

        checkLogin()

    }


    fun checkLogin(): UserLoginResponse {
        val jwtSharedPref = getSharedPreferences("JWT", MODE_PRIVATE)
        val id = jwtSharedPref.getInt("id", 0);
        val username = jwtSharedPref.getString("username", null);
        val email = jwtSharedPref.getString("email", null);
        val roles = jwtSharedPref.getString("roles", null);
        val user =
            UserLoginResponse(id, username ?: "", email ?: "", roles ?: "")
        return user
    }

    override fun onStart() {
        super.onStart()
        val user = checkLogin()
        binding.editTextLogin.text.append(user.username)
        binding.editTextPassword.text.append("")
    }

    private fun setUpListeners() {
        binding.buttonLogin.setOnClickListener {
            Log.i("Nicolas Login","In login...")
            val user = UserLoginCommand(
                binding.editTextLogin.text.toString(),
                binding.editTextPassword.text.toString()
            )
            Log.i("Nicolas Login",user.toString())
            performLogin(user)
        }
    }

    private val apiService = RetrofitFactory.instance.create(ILoginRepository::class.java)
    private fun performLogin(user: UserLoginCommand) {
        val intent = Intent(this, QuizzManagerActivity::class.java)
        lifecycleScope.launch {
            try {
                val response: retrofit2.Response<UserLoginResponse> = apiService.login(user)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val userLoginResponse = UserLoginResponse(
                        responseBody!!.id,
                        responseBody.username,
                        responseBody.email,
                        responseBody.role
                    )
                    login(userLoginResponse)
                    Log.d("Nicolas Login", "Login successful: $responseBody")
                    startActivity(intent)
                } else {
                    Log.e("Nicolas Login", "Login failed: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Nicolas Login", "Login error: ${e.message}")
            }
            Log.i("Nicolas Login","Out login...")
        }
    }
}