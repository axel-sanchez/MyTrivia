package com.example.mytrivia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.domain.HomeUseCase

/**
 * View model de [HomeFragment]
 * @author Axel Sanchez
 */
class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val listData = MutableLiveData<Response.Question?>()

    private fun setListData(question: Response.Question?) {
        listData.postValue(question)
    }

    suspend fun getQuestion(category: Int, difficulty: String, type: String) {
        setListData(homeUseCase.getQuestion(category, difficulty, type))
    }

    fun getQuestionLiveData(): LiveData<Response.Question?> {
        return listData
    }

    /**
     * Factory of [HomeViewModel]
     * @param [homeUseCase]
     * @author Axel Sanchez
     */
    class HomeViewModelFactory(private val homeUseCase: HomeUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(HomeUseCase::class.java)
                .newInstance(homeUseCase)
        }
    }
}