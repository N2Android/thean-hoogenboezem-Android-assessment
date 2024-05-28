package com.glucode.about_you.engineers.view

import com.glucode.about_you.engineers.models.Engineer

interface EngineersView {
    fun updateRecyclerViewList(engineersList: List<Engineer>)
    fun navigateToAboutEngineer(engineer: Engineer)
}