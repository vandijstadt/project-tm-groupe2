package be.school.quizzapplication.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import be.school.quizzapplication.R
import be.school.quizzapplication.activity.ModifyActivity
import be.school.quizzapplication.databinding.FragmentGestionBinding
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch
import java.net.CookieHandler
import java.net.CookieManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GestionFragment : Fragment() {

    private var binding: FragmentGestionBinding? = null

    private var id: Int = 1
    fun setId(id: Int) {
        this.id = id
    }

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

    private fun setUpToken() {
        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)

        val cookies = cookieManager.cookieStore.cookies
        for(cookie in cookies){
            if(cookie.name == "token"){
                val token = cookie.value
                RetrofitFactory.addTokenToClient(token)
            }
        }
    }

    private fun setUpListener() {
        binding?.buttonDelete?.setOnClickListener {
            Log.i("Nicolas delete", "Debut de la suppression")
            AlertDialog.Builder(context)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this quiz?")
                .setPositiveButton("Delete") { dialog, which ->
                    performDelete(1) // TODO : a changer
                }
                .setNegativeButton("Cancel") { dialog, which ->
                }
                .show()
        }
        binding?.buttonModify?.setOnClickListener {
            Log.i("Nicolas update", "Debut de la modification")
            val intent = Intent(requireContext(), ModifyActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
    private val apiService = RetrofitFactory.instance.create(IQuizzRepository::class.java)
    private fun performDelete(quizzId: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.delete(quizzId)
                if (response.isSuccessful && response.code() == 204) {
                    Log.d("Nicolas delete", "Delete successful")
                    // TODO : messages
                    Toast.makeText(context, "Successful removal", Toast.LENGTH_LONG)
                } else {
                    // TODO : messages alerte
                    Toast.makeText(context, "Failed removal", Toast.LENGTH_LONG)
//                    Log.e("Nicolas Login", "Login failed: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Failed removal", Toast.LENGTH_LONG)
                Log.e("Nicolas delete", "Login error: ${e.message}")
            }
            Log.i("Nicolas delete", "Out login...")
        }
    }
}