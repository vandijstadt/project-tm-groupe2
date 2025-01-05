package be.school.quizzapplication.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.FragmentGestionBinding
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch
import java.net.CookieHandler
import java.net.CookieManager

class GestionFragment : Fragment() {

    private var binding: FragmentGestionBinding? = null
    private var id: Int = 1
    fun setId(id: Int) {
        this.id = id
    }

    private var onQuizDeletedListener: OnQuizDeletedListener? = null

    fun setOnQuizDeletedListener(listener: OnQuizDeletedListener) {
        this.onQuizDeletedListener = listener
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
            AlertDialog.Builder(context)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this quiz?")
                .setPositiveButton("Delete") { dialog, which ->
                    Log.i("Nicolas", "ID : $id")
                    performDelete(id) // TODO : a changer
                }
                .setNegativeButton("Cancel") { dialog, which ->
                }
                .show()
        }
        binding?.buttonModify?.setOnClickListener {
            Log.i("Nicolas update", "Debut de la modification")
        }
    }
    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performDelete(quizId: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.delete(quizId)
                if (response.isSuccessful && response.code() == 204) {
                    Log.d("Nicolas delete", "Delete successful")
                    Toast.makeText(context, "Successful removal", Toast.LENGTH_LONG).show()
                    onQuizDeletedListener?.onQuizDeleted(quizId)  // Notify the listener
                } else {
                    Toast.makeText(context, "Failed removal", Toast.LENGTH_LONG).show()
                    Log.e("Nicolas Login", "Login failed: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Failed removal", Toast.LENGTH_LONG).show()
                Log.e("Nicolas delete", "Login error: ${e.message}")
            }
        }
    }


}

interface OnQuizDeletedListener {
    fun onQuizDeleted(quizId: Int)
}
