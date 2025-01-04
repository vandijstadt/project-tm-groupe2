package be.school.quizzapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.FragmentGestionBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GestionFragment : Fragment() {

    private var binding: FragmentGestionBinding? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gestion, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(quiz: GetAllQuizzesResponse) =
            GestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, quiz.id.toString())
                    putString(ARG_PARAM2, quiz.title)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGestionBinding.bind(view) // Initialize binding here
        setUpListener()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Release binding to prevent memory leaks
    }
    private fun setUpListener() {
        binding?.buttonDelete?.setOnClickListener {
            Log.i("Nicolas delete", "Debut de la suppression")



        }
        binding?.buttonModify?.setOnClickListener {
            Log.i("Nicolas update", "Debut de la modification")
        }
    }
}