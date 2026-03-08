package com.englishlearning.ui.visual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.englishlearning.R

class VisualLearningFragment : Fragment() {

    private lateinit var viewModel: VisualLearningViewModel

    private lateinit var tvEnglishWord: TextView
    private lateinit var tvArabicTranslation: TextView
    private lateinit var tvPhonetic: TextView
    private lateinit var tvExampleSentence: TextView
    private lateinit var tvProgress: TextView
    private lateinit var imgWord: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnMarkLearned: Button
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_visual_learning, container, false)
        
        viewModel = ViewModelProvider(this).get(VisualLearningViewModel::class.java)

        initViews(root)
        setupListeners()
        observeViewModel()

        return root
    }

    private fun initViews(root: View) {
        tvEnglishWord = root.findViewById(R.id.tv_english_word)
        tvArabicTranslation = root.findViewById(R.id.tv_arabic_translation)
        tvPhonetic = root.findViewById(R.id.tv_phonetic)
        tvExampleSentence = root.findViewById(R.id.tv_example_sentence)
        tvProgress = root.findViewById(R.id.tv_progress)
        imgWord = root.findViewById(R.id.img_word)
        progressBar = root.findViewById(R.id.progress_bar)
        btnMarkLearned = root.findViewById(R.id.btn_mark_learned)
        btnNext = root.findViewById(R.id.btn_next)
    }

    private fun setupListeners() {
        btnMarkLearned.setOnClickListener {
            viewModel.markCurrentWordAsLearned()
        }

        btnNext.setOnClickListener {
            viewModel.loadNextWord()
        }
    }

    private fun observeViewModel() {
        viewModel.currentWord.observe(viewLifecycleOwner) { word ->
            word?.let {
                tvEnglishWord.text = it.englishWord
                tvArabicTranslation.text = it.arabicTranslation
                tvPhonetic.text = it.phonetic
                tvExampleSentence.text = it.exampleSentence
                
                btnMarkLearned.isEnabled = !it.isLearned
                btnMarkLearned.text = if (it.isLearned) {
                    getString(R.string.already_learned)
                } else {
                    getString(R.string.mark_as_learned)
                }
            }
        }

        viewModel.progress.observe(viewLifecycleOwner) { progress ->
            val progressText = getString(R.string.words_learned, progress.first, progress.second)
            tvProgress.text = progressText
            progressBar.max = progress.second
            progressBar.progress = progress.first
        }
    }
}
