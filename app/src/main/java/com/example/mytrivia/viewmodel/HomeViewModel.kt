package com.example.mytrivia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.domain.HomeUseCase
import com.example.mytrivia.helpers.MutableLiveDataCustom
import kotlinx.coroutines.launch

/**
 * View model de [HomeFragment]
 * @author Axel Sanchez
 */
class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val listData = MutableLiveDataCustom<Response.Question?>()

    private fun setListData(question: Response.Question?) {
        listData.postValue(question)
    }

    fun getQuestion(category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            setListData(homeUseCase.getQuestion(category, difficulty, type))
        }
    }

    fun getQuestionLiveData(): LiveData<Response.Question?> {
        return listData
    }

    class HomeViewModelFactory(private val homeUseCase: HomeUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(HomeUseCase::class.java)
                .newInstance(homeUseCase)
        }
    }
}