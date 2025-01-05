package be.school.quizzapplication.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.school.quizzapplication.DTO.quizz.GetAllQuizzesResponse
import be.school.quizzapplication.repository.IQuizzRepository
import com.school.tmproject.placeholder.RetrofitFactory
import kotlinx.coroutines.launch

class QuizzManagerViewModel : ViewModel() {
    private val quizzRepository = RetrofitFactory.instance
        .create(IQuizzRepository::class.java)

    val mutableQuizzLiveData: MutableLiveData<List<GetAllQuizzesResponse>> = MutableLiveData()

    fun startGetAllQuizzes(){
        viewModelScope.launch {
            val quizzes = quizzRepository.getAll()
            mutableQuizzLiveData.postValue(quizzes)
        }
    }
}