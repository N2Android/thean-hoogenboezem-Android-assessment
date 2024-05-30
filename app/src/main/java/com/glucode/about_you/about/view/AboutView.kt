package com.glucode.about_you.about.view

import android.net.Uri
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.Question

interface AboutView {
    fun setupProfileCard(engineer: Engineer)
    fun setupQuestions(questions: List<Question>)
    fun updateProfilePicture(uri: Uri)
    fun requestPermission()
    fun openGallery()
    fun requestUserToUpdatePermissionSetting()
}