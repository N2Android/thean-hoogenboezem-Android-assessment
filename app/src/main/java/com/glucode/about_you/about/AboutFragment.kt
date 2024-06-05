package com.glucode.about_you.about

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.glucode.about_you.R
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

    companion object {
        private const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 101
        private const val ACCESS_MEDIA_LOCATION_REQUEST_CODE = 102
        private const val REQUEST_CODE_GALLERY_IMAGE = 103
    }

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
        val engineerName = arguments?.getString(getString(R.string.engineer_name_key))
        val engineer = MockData.engineers.first { it.getName() == engineerName }
        engineerProfileView = ProfileCardView(requireContext())
        viewModel = AboutViewModel(engineer, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                handlePermissionResult(grantResults)
            }

            ACCESS_MEDIA_LOCATION_REQUEST_CODE -> {
                handlePermissionResult(grantResults)
            }
        }
    }

    private fun handlePermissionResult(grantResults: IntArray) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            viewModel.onPermissionGranted()
        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            viewModel.onPermissionDenied()
        } else {
            Toast.makeText(requireContext(),
                getString(R.string.permission_denied_toast_message), Toast.LENGTH_SHORT).show()
        }
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

    override fun requestPermission() {
        checkAndRequestPermissions()
    }

    override fun openGallery() {
        val pictureIntent = Intent(Intent.ACTION_PICK)
        pictureIntent.setType("image/*")
        startActivityForResult(pictureIntent, REQUEST_CODE_GALLERY_IMAGE)
    }

    override fun requestUserToUpdatePermissionSetting() {
        showSettingsDialog()
    }

    private fun showSettingsDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.permission_required_settings_prompt))
            .setMessage(getString(R.string.permission_required_settings_prompt_message))
            .setPositiveButton(getString(R.string.go_to_settings_positive_button_text)) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireContext().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.cancel_button_settings_prompt)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun checkAndRequestPermissions() {
        val permissionsNeeded = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_MEDIA_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_MEDIA_LOCATION)
        }

        if (permissionsNeeded.isNotEmpty()) {
            requestPermissions(permissionsNeeded.toTypedArray(), READ_EXTERNAL_STORAGE_REQUEST_CODE)
        } else {
            viewModel.onPermissionGranted()
        }
    }

    override fun questionAnswerChanged(question: String, questionAnswer: String) {
        viewModel.onAnswerChanged(question, questionAnswer)
    }

    override fun profileClicked() {
        viewModel.onProfileImageClicked()
    }
}