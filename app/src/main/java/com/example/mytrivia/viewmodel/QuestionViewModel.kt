package com.example.mytrivia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.domain.QuestionUseCase
import com.example.mytrivia.helpers.MutableLiveDataCustom

/**
 * View model de [MyFragment]
 * @author Axel Sanchez
 */
class QuestionViewModel(private val questionUseCase: QuestionUseCase) : ViewModel() {

    private val listData = MutableLiveDataCustom<Response.Question?>()

    private fun setListData(question: Response.Question?) {
        listData.postValue(question)
    }

    suspend fun getQuestion(id: Long) {
        setListData(questionUseCase.getQuestionFromId(id))
    }

    fun getQuestionLiveData(): LiveData<Response.Question?> {
        return listData
    }

    /**
     * Factory of [QuestionViewModel]
     * @param [questionUseCase]
     * @author Axel Sanchez
     */
    class QuestionViewModelFactory(private val questionUseCase: QuestionUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(QuestionUseCase::class.java)
                .newInstance(questionUseCase)
        }
    }
}