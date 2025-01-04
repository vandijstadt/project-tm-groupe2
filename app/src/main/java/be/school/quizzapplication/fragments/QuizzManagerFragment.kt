package be.school.quizzapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.FragmentQuizzManagerBinding

class QuizzManagerFragment : Fragment() {

    private lateinit var binding: FragmentQuizzManagerBinding
    private val viewModel: QuizzManagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizzManagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val quizzListFragment = childFragmentManager
            .findFragmentById(R.id.fragmentContainerView_quizzManagerFragment) as QuizzListFragment

        viewModel.mutableQuizzLiveData.observe(viewLifecycleOwner) {
            quizzListFragment.initUIWithQuizzes(it.toList())
        }

        viewModel.startGetAllQuizzes()
    }



}
