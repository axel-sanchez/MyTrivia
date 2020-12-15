package com.example.mytrivia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mytrivia.R
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.databinding.FragmentHomeBinding
import com.example.mytrivia.helpers.Constants.ANIMALS
import com.example.mytrivia.helpers.Constants.ART
import com.example.mytrivia.helpers.Constants.CELEBRITIES
import com.example.mytrivia.helpers.Constants.EASY
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_BOARD_GAMES
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_BOOKS
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_CARTOON_AND_ANIMATIONS
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_COMICS
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_FILMS
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_MUSIC
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_MUSICALS_AND_THEATRES
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_TELEVISION
import com.example.mytrivia.helpers.Constants.ENTERTAINMENT_VIDEO_GAMES
import com.example.mytrivia.helpers.Constants.GENERAL_KNOWLEDGE
import com.example.mytrivia.helpers.Constants.GEOGRAPHY
import com.example.mytrivia.helpers.Constants.HARD
import com.example.mytrivia.helpers.Constants.HISTORY
import com.example.mytrivia.helpers.Constants.MEDIUM
import com.example.mytrivia.helpers.Constants.MULTIPLE_CHOICE
import com.example.mytrivia.helpers.Constants.MYTHOLOGY
import com.example.mytrivia.helpers.Constants.POLITICS
import com.example.mytrivia.helpers.Constants.SCIENCE_AND_NATURE
import com.example.mytrivia.helpers.Constants.SCIENCE_COMPUTERS
import com.example.mytrivia.helpers.Constants.SCIENCE_GADGETS
import com.example.mytrivia.helpers.Constants.SCIENCE_MATHEMATICS
import com.example.mytrivia.helpers.Constants.SPORTS
import com.example.mytrivia.helpers.Constants.TRUE_FALSE
import com.example.mytrivia.helpers.Constants.VEHICLES
import com.example.mytrivia.ui.customs.BaseFragment
import com.example.mytrivia.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * @author Axel Sanchez
 */
class HomeFragment: BaseFragment() {

    var category = 0
    var difficultly = ""
    var type = ""

    private var fragmentMyBinding: FragmentHomeBinding? = null
    private val binding get() = fragmentMyBinding!!

    private val viewModelFactory: HomeViewModel.HomeViewModelFactory by inject()
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMyBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMyBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trueFalse.setOnClickListener {
            binding.trueFalse.disable()
            binding.multipleOption.enable()
            type = TRUE_FALSE
        }

        binding.multipleOption.setOnClickListener {
            binding.multipleOption.disable()
            binding.trueFalse.enable()
            type = MULTIPLE_CHOICE
        }

        binding.easy.setOnClickListener {
            binding.easy.disable()
            binding.hard.enable()
            binding.medium.enable()
            difficultly = EASY
        }

        binding.medium.setOnClickListener {
            binding.medium.disable()
            binding.easy.enable()
            binding.hard.enable()
            difficultly = MEDIUM
        }

        binding.hard.setOnClickListener {
            binding.hard.disable()
            binding.easy.enable()
            binding.medium.enable()
            difficultly = HARD
        }

        binding.start.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getQuestion(category, difficultly, type)
            }
        }

        setUpViewModel()

        adapterCategories()
    }

    private fun setUpViewModel() {
        val myObserver = Observer<Response.Question?> {
            it?.let {
                val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment(it.id)
                findNavController().navigate(action)
            }
        }
        viewModel.getQuestionLiveData().observe(viewLifecycleOwner, myObserver)
    }

    private fun adapterCategories() {
        val spinner: Spinner = binding.category
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(parent?.getItemAtPosition(position)?.toString()?:""){
                    "General Knowledge" -> category = GENERAL_KNOWLEDGE
                    "Entertainment: Books" -> category = ENTERTAINMENT_BOOKS
                    "Entertainment: films" -> category = ENTERTAINMENT_FILMS
                    "Entertainment: music" -> category = ENTERTAINMENT_MUSIC
                    "Entertainment: musicals y theatres" -> category = ENTERTAINMENT_MUSICALS_AND_THEATRES
                    "Entertainment: television" -> category = ENTERTAINMENT_TELEVISION
                    "Entertainment: video games" -> category = ENTERTAINMENT_VIDEO_GAMES
                    "Entertainment: board games" -> category = ENTERTAINMENT_BOARD_GAMES
                    "Science and nature" -> category = SCIENCE_AND_NATURE
                    "Science: Computers" -> category = SCIENCE_COMPUTERS
                    "Science: mathematics" -> category = SCIENCE_MATHEMATICS
                    "Mythology" -> category = MYTHOLOGY
                    "Sports" -> category = SPORTS
                    "Geography" -> category = GEOGRAPHY
                    "History" -> category = HISTORY
                    "Politics" -> category = POLITICS
                    "Art" -> category = ART
                    "Celebrities" -> category = CELEBRITIES
                    "Animals" -> category = ANIMALS
                    "Vehicles" -> category = VEHICLES
                    "Entertainment: comics" -> category = ENTERTAINMENT_COMICS
                    "Science: gadgets" -> category = SCIENCE_GADGETS
                    "Entertainment: cartoon and animations" -> category = ENTERTAINMENT_CARTOON_AND_ANIMATIONS
                    "Entertainment: japanese anime and manga" -> category = ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}