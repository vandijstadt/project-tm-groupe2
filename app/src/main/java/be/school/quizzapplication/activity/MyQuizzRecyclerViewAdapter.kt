package be.school.quizzapplication.activity

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import be.school.quizzapplication.databinding.FragmentQuizzBinding
import be.school.quizzapplication.placeholder.PlaceholderContent.PlaceholderItem

class MyQuizzRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyQuizzRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentQuizzBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: FragmentQuizzBinding) : RecyclerView.ViewHolder(binding.root)
}
