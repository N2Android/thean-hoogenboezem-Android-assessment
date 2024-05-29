package com.glucode.about_you.about

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.view.AboutView
import com.glucode.about_you.about.view.model.AboutViewModel
import com.glucode.about_you.about.views.ProfileCardView
import com.glucode.about_you.about.views.ProfilePictureView
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.about.views.QuestionView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.Question
import com.glucode.about_you.mockdata.MockData

class AboutFragment: Fragment(),
    ProfilePictureView, AboutView, QuestionView {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var engineerProfileView: ProfileCardView
    private lateinit var viewModel: AboutViewModel
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
        val engineer = MockData.engineers.first { it.getName() == engineerName }
        engineerProfileView = ProfileCardView(requireContext())
        viewModel = AboutViewModel(engineer, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_CODE_GALLERY_IMAGE -> {
                if (data != null) {
                    val selectedImageUri = data.data
                    if (selectedImageUri != null) {
                        viewModel.onPhotoResult(selectedImageUri)
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

    override fun setupProfileCard(engineer: Engineer) {
        engineerProfileView.setupProfilePictureClickListener(this)
        if (engineer.getDefaultImage() != "") {
            engineerProfileView.setProfilePicture(engineer.getDefaultImage().toUri())
        }
        engineerProfileView.addName(engineer.getName())
        engineerProfileView.addRole(engineer.getRole())
        engineerProfileView.addYears(engineer.getQuickStats().years.toString())
        engineerProfileView.addCoffees(engineer.getQuickStats().coffees.toString())
        engineerProfileView.addBugs(engineer.getQuickStats().bugs.toString())
        binding.container.addView(engineerProfileView)
    }

    override fun setupQuestions(questions: List<Question>) {
        questions.forEach { question ->
            val questionView = QuestionCardView(requireContext(), null, 0, this)
            questionView.questionTitle = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    override fun updateProfilePicture(uri: Uri) {
        engineerProfileView.setProfilePicture(uri)
    }

    override fun questionAnswerChanged(question: String, questionAnswer: String) {
        viewModel.onAnswerChanged(question, questionAnswer)
    }
}