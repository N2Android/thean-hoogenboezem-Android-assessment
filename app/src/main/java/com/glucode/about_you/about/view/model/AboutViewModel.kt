package com.glucode.about_you.about.view.model

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.glucode.about_you.about.view.AboutView
import com.glucode.about_you.engineers.models.Engineer

class AboutViewModel(private val engineer: Engineer,
                     private val view: AboutView) : ViewModel() {
    init {
        view.setupProfileCard(engineer)
        view.setupQuestions(engineer.questions)
    }

    fun onPhotoResult(uri: Uri) {
        view.updateProfilePicture(uri)
        engineer.defaultImageName = uri.toString()
    }

    fun onAnswerChanged(questionText: String, answer: String) {
        val questionToUpdate = engineer.questions.find { it.questionText == questionText }
        val index = questionToUpdate?.answerOptions?.indexOf(answer)

        if (questionToUpdate != null) {
            questionToUpdate.answer.text = answer
            questionToUpdate.answer.index = index
        }
    }
}