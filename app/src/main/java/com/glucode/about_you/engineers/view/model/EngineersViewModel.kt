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
        engineersView.updateRecyclerViewList(engineers)
    }

    fun engineerListSortSelectedBy(ascending: Boolean, sortBy: Int) {
        when (sortBy){
            sortByYears -> {
                if (ascending) {
                    engineers.sortWith(compareBy { it.quickStats.years })
                } else {
                    engineers.sortWith(compareByDescending { it.quickStats.years})
                }
            }
            sortByCoffees -> {
                if (ascending) {
                    engineers.sortWith(compareBy { it.quickStats.coffees })
                } else {
                    engineers.sortWith(compareByDescending { it.quickStats.coffees})
                }
            }
            sortByBugs -> {
                if (ascending) {
                    engineers.sortWith(compareBy { it.quickStats.bugs })
                } else {
                    engineers.sortWith(compareByDescending { it.quickStats.bugs})
                }
            }
        }
        engineersView.updateRecyclerViewList(engineers)
    }

    fun listItemClicked(engineer: Engineer) {
        engineersView.navigateToAboutEngineer(engineer)
    }

}