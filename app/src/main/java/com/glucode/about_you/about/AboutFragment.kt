package com.glucode.about_you.about

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.ProfileCardView
import com.glucode.about_you.about.views.ProfilePictureInterface
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class AboutFragment: Fragment(), ProfilePictureInterface {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var engineer: Engineer
    private lateinit var engineerProfileView: ProfileCardView
    private val REQUEST_CODE_GALLERY_IMAGE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val engineerName = arguments?.getString("name")
        engineer = MockData.engineers.first { it.name == engineerName }
        setUpProfileCard(engineerName.toString())
        setUpQuestions()
    }

    private fun setUpProfileCard(engineerName: String) {
        engineerProfileView = ProfileCardView(requireContext())
        engineerProfileView.setupProfilePictureClickListener(this)
        if (engineer.defaultImageName != "") {
            engineerProfileView.setProfilePicture(engineer.defaultImageName.toUri())
        }
        engineerProfileView.addName(engineerName)
        engineerProfileView.addRole(engineer.role)
        binding.container.addView(engineerProfileView)
    }

    private fun setUpQuestions() {
        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.d("Test", "No Photo")
            return
        }
        when (requestCode) {
            REQUEST_CODE_GALLERY_IMAGE -> {
                if (data != null) {
                    val selectedImageUri = data.data
                    if (selectedImageUri != null) {
                        engineerProfileView.setProfilePicture(selectedImageUri)
                        engineer.defaultImageName = selectedImageUri.toString()
                    }
                }
            }
        }
    }

    override fun selectPhoto() {
        val pictureIntent = Intent(Intent.ACTION_PICK)
        pictureIntent.setType("image/*")
        startActivityForResult(pictureIntent, REQUEST_CODE_GALLERY_IMAGE)
    }
}