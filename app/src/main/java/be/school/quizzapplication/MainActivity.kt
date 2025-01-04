package be.school.quizzapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.school.quizzapplication.activity.LoginActivity
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
//        test()
    }

//    private fun test() {
//        var idAMettre = 1
//        binding.buttonTest.setOnClickListener {
//            binding.fragmentContainerViewTest.visibility =
//                if (binding.fragmentContainerViewTest.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
//            if (binding.fragmentContainerViewTest.visibility == View.VISIBLE){
//                binding.fragmentContainerViewTest.getFragment<QuizzManagerFragment>().setId(idAMettre)
//            }
//        }
//    }


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