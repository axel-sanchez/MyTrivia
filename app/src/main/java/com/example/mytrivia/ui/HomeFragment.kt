package com.example.mytrivia.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytrivia.R
import com.example.mytrivia.databinding.FragmentHomeBinding
import com.example.mytrivia.domain.HomeUseCase
import com.example.mytrivia.helpers.Constants.*
import com.example.mytrivia.helpers.NetworkHelper
import com.example.mytrivia.ui.customs.BaseFragment
import com.example.mytrivia.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

/**
 * @author Axel Sanchez
 */
class HomeFragment : BaseFragment() {

    var category = 0
    var difficultly = ""
    var type = ""

    private var fragmentMyBinding: FragmentHomeBinding? = null
    private val binding get() = fragmentMyBinding!!

    private val homeUseCase: HomeUseCase by inject()
    private val viewModel: HomeViewModel by viewModels(
        factoryProducer = { HomeViewModel.HomeViewModelFactory(homeUseCase) }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMyBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMyBinding = null
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (difficultly) {
            EASY.valueString -> {
                binding.easy.disable()
            }
            MEDIUM.valueString -> {
                binding.medium.disable()
            }
            HARD.valueString -> {
                binding.hard.disable()
            }
        }

        when (type) {
            TRUE_FALSE.valueString -> binding.trueFalse.disable()
            MULTIPLE_CHOICE.valueString -> binding.multipleOption.disable()
        }

        binding.trueFalse.setOnClickListener {
            binding.trueFalse.disable()
            binding.multipleOption.enable()
            type = TRUE_FALSE.valueString
        }

        binding.multipleOption.setOnClickListener {
            binding.multipleOption.disable()
            binding.trueFalse.enable()
            type = MULTIPLE_CHOICE.valueString
        }

        binding.easy.setOnClickListener {
            binding.easy.disable()
            binding.hard.enable()
            binding.medium.enable()
            difficultly = EASY.valueString
        }

        binding.medium.setOnClickListener {
            binding.medium.disable()
            binding.easy.enable()
            binding.hard.enable()
            difficultly = MEDIUM.valueString
        }

        binding.hard.setOnClickListener {
            binding.hard.disable()
            binding.easy.enable()
            binding.medium.enable()
            difficultly = HARD.valueString
        }

        binding.btnStart.setOnClickListener {
            if (category != 0 && difficultly != "" && type != "") {
                if (NetworkHelper.isOnline(requireContext())) {
                    binding.btnStart.disable()
                    binding.progress.show()
                    binding.progress.playAnimation()
                    viewModel.getQuestion(category, difficultly, type)
                } else {
                    Toast.makeText(context, "You don't have internet connection", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please, select all fields", Toast.LENGTH_SHORT).show()
            }
        }

        setUpViewModel()

        adapterCategoriesSpinner()
    }

    private fun setUpViewModel() {
        viewModel.getQuestionLiveData().observe(viewLifecycleOwner, {
            binding.progress.cancelAnimation()
            binding.progress.hide()
            it?.let {
                val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment(it.id)
                findNavController().navigate(action)
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "No questions found", Toast.LENGTH_SHORT).show()
                binding.btnStart.enable()
            }
        })
    }

    private fun adapterCategoriesSpinner() {
        val spinner: Spinner = binding.category
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.getItemAtPosition(position)?.toString() ?: "") {
                    "General Knowledge" -> category = GENERAL_KNOWLEDGE.valueInt
                    "Entertainment: Books" -> category = ENTERTAINMENT_BOOKS.valueInt
                    "Entertainment: films" -> category = ENTERTAINMENT_FILMS.valueInt
                    "Entertainment: music" -> category = ENTERTAINMENT_MUSIC.valueInt
                    "Entertainment: musicals y theatres" -> category = ENTERTAINMENT_MUSICALS_AND_THEATRES.valueInt
                    "Entertainment: television" -> category = ENTERTAINMENT_TELEVISION.valueInt
                    "Entertainment: video games" -> category = ENTERTAINMENT_VIDEO_GAMES.valueInt
                    "Entertainment: board games" -> category = ENTERTAINMENT_BOARD_GAMES.valueInt
                    "Science and nature" -> category = SCIENCE_AND_NATURE.valueInt
                    "Science: Computers" -> category = SCIENCE_COMPUTERS.valueInt
                    "Science: mathematics" -> category = SCIENCE_MATHEMATICS.valueInt
                    "Mythology" -> category = MYTHOLOGY.valueInt
                    "Sports" -> category = SPORTS.valueInt
                    "Geography" -> category = GEOGRAPHY.valueInt
                    "History" -> category = HISTORY.valueInt
                    "Politics" -> category = POLITICS.valueInt
                    "Art" -> category = ART.valueInt
                    "Celebrities" -> category = CELEBRITIES.valueInt
                    "Animals" -> category = ANIMALS.valueInt
                    "Vehicles" -> category = VEHICLES.valueInt
                    "Entertainment: comics" -> category = ENTERTAINMENT_COMICS.valueInt
                    "Science: gadgets" -> category = SCIENCE_GADGETS.valueInt
                    "Entertainment: cartoon and animations" -> category = ENTERTAINMENT_CARTOON_AND_ANIMATIONS.valueInt
                    "Entertainment: japanese anime and manga" -> category = ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA.valueInt
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}