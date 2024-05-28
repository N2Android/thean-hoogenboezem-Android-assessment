package com.glucode.about_you.about.view.model

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.glucode.about_you.about.view.AboutView
import com.glucode.about_you.engineers.models.Engineer

class AboutViewModel(val engineer: Engineer,
                     val view: AboutView) : ViewModel() {
    init {
        view.setupProfileCard(engineer)
        view.setupQuestions(engineer.questions)
    }

    fun onPhotoResult(uri: Uri) {
        view.updateProfilePicture(uri)
        engineer.defaultImageName = uri.toString()
    }
}