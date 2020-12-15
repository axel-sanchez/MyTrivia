package com.example.mytrivia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.mytrivia.R
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.databinding.FragmentQuestionBinding
import com.example.mytrivia.helpers.Constants
import com.example.mytrivia.ui.customs.BaseFragment
import com.example.mytrivia.viewmodel.QuestionViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

/**
 * A simple [Fragment] subclass.
 * @author Axel Sanchez
 */
class QuestionFragment: BaseFragment() {

    private val viewModelFactory: QuestionViewModel.QuestionViewModelFactory by inject()
    private val viewModel: QuestionViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(QuestionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idQuestion = QuestionFragmentArgs.fromBundle(requireArguments()).idQuestion

        lifecycleScope.launch {
            viewModel.getQuestion(idQuestion)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
    }

    private fun setUpObserver() {
        val myObserver = Observer<Response.Question?> {

            it?.let {

                if(it.type == Constants.MULTIPLE_CHOICE){

                    val category = binding.questionMultiple.root.findViewById<TextView>(R.id.category)
                    val question = binding.questionMultiple.root.findViewById<TextView>(R.id.question)
                    val optionOne = binding.questionMultiple.root.findViewById<TextView>(R.id.optionOne)
                    val optionTwo = binding.questionMultiple.root.findViewById<TextView>(R.id.optionTwo)
                    val optionThree = binding.questionMultiple.root.findViewById<TextView>(R.id.optionThree)
                    val optionFour = binding.questionMultiple.root.findViewById<TextView>(R.id.optionFour)

                    val answers = mutableListOf(it.correct_answer?:"", it.incorrect_answers?.get(0)?:"", it.incorrect_answers?.get(1)?:"", it.incorrect_answers?.get(2)?:"")
                    answers.shuffle()

                    for(i in 0..3){
                        when(i){
                            0 -> optionOne.text = answers[i]
                            1 -> optionTwo.text = answers[i]
                            2 -> optionThree.text = answers[i]
                            3 -> optionFour.text = answers[i]
                        }
                    }

                    binding.questionMultiple.root.show()
                    category.text = it.category
                    question.text = it.question

                    optionOne.setOnClickListener { _ ->
                        if(optionOne.text == it.correct_answer) optionOne.setBackgroundColor(resources.getColor(R.color.green))
                        else optionOne.setBackgroundColor(resources.getColor(R.color.red))

                        //optionOne.disable()
                        optionTwo.disable()
                        optionThree.disable()
                        optionFour.disable()
                    }

                    optionTwo.setOnClickListener { _ ->
                        if(optionTwo.text == it.correct_answer) optionTwo.setBackgroundColor(resources.getColor(R.color.green))
                        else optionTwo.setBackgroundColor(resources.getColor(R.color.red))

                        optionOne.disable()
                        //optionTwo.disable()
                        optionThree.disable()
                        optionFour.disable()
                    }

                    optionThree.setOnClickListener { _ ->
                        if(optionThree.text == it.correct_answer) optionThree.setBackgroundColor(resources.getColor(R.color.green))
                        else optionThree.setBackgroundColor(resources.getColor(R.color.red))

                        optionOne.disable()
                        optionTwo.disable()
                        //optionThree.disable()
                        optionFour.disable()
                    }
                    optionFour.setOnClickListener { _ ->
                        if(optionFour.text == it.correct_answer) optionFour.setBackgroundColor(resources.getColor(R.color.green))
                        else optionFour.setBackgroundColor(resources.getColor(R.color.red))

                        optionOne.disable()
                        optionTwo.disable()
                        optionThree.disable()
                        //optionFour.disable()
                    }

                    ///////////////////////////////////
                    binding.questionBoolean.root.hide()
                } else{

                    val category = binding.questionBoolean.root.findViewById<TextView>(R.id.category)
                    val question = binding.questionBoolean.root.findViewById<TextView>(R.id.question)
                    val optionOne = binding.questionBoolean.root.findViewById<TextView>(R.id.optionOne)
                    val optionTwo = binding.questionBoolean.root.findViewById<TextView>(R.id.optionTwo)

                    binding.questionBoolean.root.show()
                    category.text = it.category
                    question.text = it.question

                    val randomNum = Random().nextInt(2) + 1

                    if(randomNum == 1){
                        optionOne.text = it.correct_answer?:""
                        optionTwo.text = it.incorrect_answers?.firstOrNull()?:""
                    } else{
                        optionOne.text = it.incorrect_answers?.firstOrNull()?:""
                        optionTwo.text = it.correct_answer?:""
                    }

                    optionOne.setOnClickListener { _ ->
                        if(optionOne.text == it.correct_answer) optionOne.setBackgroundColor(resources.getColor(R.color.green))
                        else optionOne.setBackgroundColor(resources.getColor(R.color.red))
                    }

                    optionTwo.setOnClickListener { _ ->
                        if(optionTwo.text == it.correct_answer) optionTwo.setBackgroundColor(resources.getColor(R.color.green))
                        else optionTwo.setBackgroundColor(resources.getColor(R.color.red))
                    }


                    /////////////////////////////////////
                    binding.questionMultiple.root.hide()
                }
            }

        }
        viewModel.getQuestionLiveData().observe(viewLifecycleOwner, myObserver)
    }

    private var fragmentQuestionBinding: FragmentQuestionBinding? = null
    private val binding get() = fragmentQuestionBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentQuestionBinding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentQuestionBinding = null
    }
}