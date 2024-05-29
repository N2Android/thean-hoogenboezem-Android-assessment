package com.glucode.about_you.engineers.models

data class Engineer(
    private val name: String,
    private val role: String,
    private var defaultImageName: String,
    private val quickStats: QuickStats,
    private var questions: List<Question>,
) {
    fun getName() : String{
        return name
    }

    fun getRole(): String {
        return role
    }

    fun updateDefaultImage(imagePath: String) {
        defaultImageName = imagePath
    }

    fun getDefaultImage(): String {
        return defaultImageName
    }

    fun getQuickStats(): QuickStats {
        return quickStats
    }

    fun getQuestions(): List<Question> {
        return questions
    }
}