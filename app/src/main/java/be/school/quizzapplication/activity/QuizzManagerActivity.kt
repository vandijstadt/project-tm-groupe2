package be.school.quizzapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.ActivityQuizzManagerBinding
import be.school.quizzapplication.fragments.QuizzListFragment
import be.school.quizzapplication.fragments.QuizzManagerFragment

class QuizzManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizzManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizzManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}