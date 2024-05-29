package com.glucode.about_you.about.view.model

import android.net.Uri
import com.glucode.about_you.about.view.AboutView
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AboutViewModelTest {

    @Mock
    private lateinit var view: AboutView
    @Mock
    private lateinit var uri: Uri
    private lateinit var engineer: Engineer
    private lateinit var aboutViewModel: AboutViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        engineer = MockData.engineers[0]
    }

    @Test
    fun verify_view_model_setup_profile_card() {
        aboutViewModel = AboutViewModel(engineer, view)

        verify(view).setupProfileCard(engineer)
    }


    @Test
    fun verify_view_model_setup_questions() {
        aboutViewModel = AboutViewModel(engineer, view)

        verify(view).setupQuestions(engineer.getQuestions())
    }

    @Test
    fun verify_view_model_onPhotoResult_updates_profile_picture() {
        aboutViewModel = AboutViewModel(engineer, view)

        aboutViewModel.onPhotoResult(uri)

        verify(view).updateProfilePicture(uri)
    }

    @Test
    fun verify_view_model_onPhotoResult_updates_engineer_model_profile() {
        aboutViewModel = AboutViewModel(engineer, view)

        aboutViewModel.onPhotoResult(uri)

        assert(engineer.getDefaultImage().equals(uri.toString()))
    }

    @Test
    fun verify_view_model_onAnswerChanged_text_is_updated() {
        aboutViewModel = AboutViewModel(engineer, view)

        val changedQuestion = engineer.getQuestions()[0]
        val changedAnswer = engineer.getQuestions()[0].answerOptions.get(2)

        aboutViewModel.onAnswerChanged(changedQuestion.questionText, changedAnswer)

        assert(engineer.getQuestions()[0].answer.text.equals(changedAnswer))
    }

    @Test
    fun verify_view_model_onAnswerChanged_index_is_updated() {
        aboutViewModel = AboutViewModel(engineer, view)

        val changedQuestion = engineer.getQuestions()[0]
        val changedAnswer = engineer.getQuestions()[0].answerOptions.get(2)
        val index = engineer.getQuestions()[0].answerOptions.indexOf(changedAnswer)

        aboutViewModel.onAnswerChanged(changedQuestion.questionText, changedAnswer)

        assert(engineer.getQuestions()[0].answer.index!!.equals(index))
    }
}