package com.englishlearning.ui.speech

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.englishlearning.R
import java.util.*

class SpeechFragment : Fragment() {

    private lateinit var viewModel: SpeechViewModel
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer
    private var isRecording = false

    private lateinit var tvSentence: TextView
    private lateinit var tvRecognizedText: TextView
    private lateinit var tvFeedback: TextView
    private lateinit var tvAccuracy: TextView
    private lateinit var btnListen: Button
    private lateinit var btnRecord: Button

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_speech, container, false)
        
        viewModel = ViewModelProvider(this).get(SpeechViewModel::class.java)

        initViews(root)
        setupTextToSpeech()
        setupSpeechRecognizer()
        setupListeners()
        observeViewModel()

        return root
    }

    private fun initViews(root: View) {
        tvSentence = root.findViewById(R.id.tv_sentence)
        tvRecognizedText = root.findViewById(R.id.tv_recognized_text)
        tvFeedback = root.findViewById(R.id.tv_feedback)
        tvAccuracy = root.findViewById(R.id.tv_accuracy)
        btnListen = root.findViewById(R.id.btn_listen)
        btnRecord = root.findViewById(R.id.btn_record)
    }

    private fun setupTextToSpeech() {
        textToSpeech = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
            }
        }
    }

    private fun setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                btnRecord.text = getString(R.string.listening)
            }

            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {
                isRecording = false
                btnRecord.text = getString(R.string.record_speech)
            }

            override fun onError(error: Int) {
                isRecording = false
                btnRecord.text = getString(R.string.record_speech)
                Toast.makeText(requireContext(), getString(R.string.error_recognition), Toast.LENGTH_SHORT).show()
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    val recognizedText = matches[0]
                    viewModel.evaluateSpeech(recognizedText)
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun setupListeners() {
        btnListen.setOnClickListener {
            val sentence = viewModel.currentSentence.value ?: return@setOnClickListener
            textToSpeech.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        btnRecord.setOnClickListener {
            if (checkAudioPermission()) {
                if (!isRecording) {
                    startRecording()
                } else {
                    stopRecording()
                }
            } else {
                requestAudioPermission()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.currentSentence.observe(viewLifecycleOwner) { sentence ->
            tvSentence.text = sentence
        }

        viewModel.speechResult.observe(viewLifecycleOwner) { result ->
            result?.let {
                tvRecognizedText.text = getString(R.string.recognized_text, it.recognizedText)
                tvAccuracy.text = getString(R.string.accuracy, (it.accuracy * 100).toInt())
                tvFeedback.text = it.feedback
            }
        }
    }

    private fun startRecording() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
        }
        isRecording = true
        speechRecognizer.startListening(intent)
    }

    private fun stopRecording() {
        isRecording = false
        speechRecognizer.stopListening()
    }

    private fun checkAudioPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestAudioPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECORD_AUDIO),
            REQUEST_RECORD_AUDIO_PERMISSION
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
        speechRecognizer.destroy()
    }
}
