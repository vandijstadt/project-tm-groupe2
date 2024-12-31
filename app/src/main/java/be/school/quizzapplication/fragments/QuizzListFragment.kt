package be.school.quizzapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.school.quizzapplication.R
import be.school.quizzapplication.dto.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.fragments.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class QuizzListFragment : Fragment() {
    private val quizzes: ArrayList<GetAllQuizzesResponse> = arrayListOf()
    private val quizzRecyclerViewAdapter = QuizzRecyclerViewAdapter(quizzes)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quizz_list, container, false)

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