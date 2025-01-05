package be.school.quizzapplication.fragments

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import be.school.quizzapplication.activity.PlayQuizzActivity

import androidx.appcompat.app.AppCompatActivity
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.R
import be.school.quizzapplication.databinding.FragmentQuizzListItemBinding

class QuizzRecyclerViewAdapter(
    private val values: List<GetAllQuizzesResponse>,
    param: (Any) -> Unit
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.quizzId.text = item.id.toString()
        holder.quizzTitle.text = item.title
        holder.quizzTheme.text = item.theme.title.replaceFirstChar {
            it.uppercase()
        }.first().toString()
        holder.itemView.setOnClickListener {
            Log.i("Mickaël","Item ${item.id} clicked")
            val context = holder.itemView.context
            val intent = Intent(context, PlayQuizzActivity::class.java)
            intent.putExtra("QUIZZ_ID", item.id)
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            onLongClickListener(holder, item)
            true
        }

    }

    private fun onLongClickListener(holder: ViewHolder, quiz: GetAllQuizzesResponse) {
        Log.i("Nicolas long press", "ID : " + quiz.id.toString())
        val handler = Handler(Looper.getMainLooper())
        val fragmentManager =
            (holder.itemView.context as? AppCompatActivity)?.supportFragmentManager
        val detailFragment = GestionFragment.newInstance(quiz) // Create fragment instance
        detailFragment.id=quiz.id
        fragmentManager?.beginTransaction()
            ?.replace(
                R.id.fragmentContainerView_quizzManagerFragment,
                detailFragment
            ) // Replace fragment
            ?.addToBackStack(null) // Add to back stack
            ?.commit() // Commit transaction

        val dismissRunnable = Runnable {
            fragmentManager?.beginTransaction()
                ?.remove(detailFragment)
                ?.commit()
        }
        handler.postDelayed(dismissRunnable, 3000)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentQuizzListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val quizzId: TextView = binding.tvQuizzItemFragmentId
        val quizzTitle: TextView = binding.tvQuizzItemFragmentTitle
        val quizzTheme: TextView = binding.tvQuizzItemFragmentTheme
    }

}