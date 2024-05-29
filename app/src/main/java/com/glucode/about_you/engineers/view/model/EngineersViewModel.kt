package com.glucode.about_you.engineers.view.model


import androidx.lifecycle.ViewModel
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.view.EngineersView


class EngineersViewModel(private val engineers: MutableList<Engineer>,
                         private val engineersView: EngineersView) : ViewModel() {

    val sortByYears: Int = 1
    val sortByCoffees: Int = 2
    val sortByBugs: Int = 3

    init {
        engineersView.setupRecyclerViewList(engineers)
    }

    fun engineerListSortAscendingBy(sortBy: Int) {
        when (sortBy){
            sortByYears -> {
                engineers.sortWith(compareBy { it.getQuickStats().years })
            }
            sortByCoffees -> {
                engineers.sortWith(compareBy { it.getQuickStats().coffees })
            }
            sortByBugs -> {
                engineers.sortWith(compareBy { it.getQuickStats().bugs })
            }
        }
        engineersView.updateRecyclerViewList(engineers)
    }

    fun listItemClicked(engineer: Engineer) {
        engineersView.navigateToAboutEngineer(engineer)
    }

}