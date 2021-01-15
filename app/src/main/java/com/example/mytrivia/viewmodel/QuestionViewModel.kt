package com.example.mytrivia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.domain.QuestionUseCase
import com.example.mytrivia.helpers.MutableLiveDataCustom
import kotlinx.coroutines.launch

/**
 * View model de [MyFragment]
 * @author Axel Sanchez
 */
class QuestionViewModel(private val questionUseCase: QuestionUseCase, private val idQuestion: Long) : ViewModel() {

    private val listData: MutableLiveDataCustom<Response.Question?> by lazy {
        MutableLiveDataCustom<Response.Question?>().also {
            getQuestion(idQuestion)
        }
    }

    private fun setListData(question: Response.Question?) {
        listData.postValue(question)
    }

    private fun getQuestion(id: Long) {
        viewModelScope.launch {
            setListData(questionUseCase.getQuestionFromId(id))
        }
    }

    fun getQuestionLiveData(): LiveData<Response.Question?> {
        return listData
    }

    class QuestionViewModelFactory(private val questionUseCase: QuestionUseCase, private val idQuestion: Long) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(QuestionUseCase::class.java, Long::class.java)
                .newInstance(questionUseCase, idQuestion)
        }
    }
}