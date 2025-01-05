package be.school.quizzapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.school.quizzapplication.dto.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R

/**
 * A fragment representing a list of Items.
 */
class QuizzListFragment : Fragment() {
    private val quizzes: ArrayList<GetAllQuizzesResponse> = arrayListOf()
    private lateinit var quizzRecyclerViewAdapter: QuizzRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quizz_list, container, false)

        quizzRecyclerViewAdapter = QuizzRecyclerViewAdapter(quizzes) {       }

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = quizzRecyclerViewAdapter
            }
        }
        return view
    }
    fun initUIWithQuizzes(quizzList: List<GetAllQuizzesResponse>){
        quizzList.forEach { quizzes.add(it) }
        quizzRecyclerViewAdapter.notifyItemInserted(0)
    }

    companion object {
        fun newInstance(columnCount: Int) =
            QuizzListFragment().apply {
            }
    }
}