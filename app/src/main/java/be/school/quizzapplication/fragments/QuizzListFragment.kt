package be.school.quizzapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R

/**
 * A fragment representing a list of Items.
 */
class QuizzListFragment : Fragment(), OnQuizDeletedListener  {

    interface OnQuizDeletedListener {
        fun onQuizDeleted(quizId: Int)
    }

    private val quizzes: ArrayList<GetAllQuizzesResponse> = arrayListOf()
    private var quizzRecyclerViewAdapter: QuizzRecyclerViewAdapter=QuizzRecyclerViewAdapter(quizzes) { quiz ->
        if(quiz is GetAllQuizzesResponse)
            showQuizDetailsFragment(quiz)
    }

    private var onQuizDeletedListener: OnQuizDeletedListener? = null

    fun setOnQuizDeletedListener(listener: OnQuizDeletedListener) {
        this.onQuizDeletedListener = listener
    }
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
    private fun showQuizDetailsFragment(quiz: GetAllQuizzesResponse) {
        val detailFragment = GestionFragment.newInstance(quiz)
        detailFragment.setOnQuizDeletedListener(this)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView_quizzManagerFragment, detailFragment)
            .addToBackStack(null)
            .commit()
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
    override fun onQuizDeleted(quizId: Int) {
        val position = quizzes.indexOfFirst { it.id == quizId }
        if (position != -1) {
            quizzes.removeAt(position)
            quizzRecyclerViewAdapter.notifyItemRemoved(position)
        }
    }
}