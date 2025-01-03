package be.school.quizzapplication.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import be.school.quizzapplication.fragments.placeholder.PlaceholderContent.PlaceholderItem
import be.school.quizzapplication.databinding.FragmentQuizzListItemBinding
import be.school.quizzapplication.dto.quizz.GetAllQuizzesResponse

class QuizzRecyclerViewAdapter(
    private val values: List<GetAllQuizzesResponse>
) : RecyclerView.Adapter<QuizzRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentQuizzListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.quizzId.text = item.id.toString()
        holder.quizzTitle.text = item.title
        holder.quizzTheme.text = item.theme.title.replaceFirstChar {
            it.uppercase()
        }.first().toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentQuizzListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val quizzId: TextView = binding.tvQuizzItemFragmentId
        val quizzTitle: TextView = binding.tvQuizzItemFragmentTitle
        val quizzTheme: TextView = binding.tvQuizzItemFragmentTheme
    }

}