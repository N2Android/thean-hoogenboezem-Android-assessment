package com.glucode.about_you.engineers.view.model

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.view.EngineersView
import com.glucode.about_you.mockdata.MockData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EngineerViewModelTest {

    @Mock
    private lateinit var view: EngineersView
    @Mock
    private lateinit var engineerList: MutableList<Engineer>
    private lateinit var engineersViewModel: EngineersViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun verify_engineerView_Model_init_Updates_RecyclerView() {
        engineersViewModel = EngineersViewModel(engineerList, view)

        verify(view).setupRecyclerViewList(engineerList)
    }

    @Test
    fun verify_engineers_sorting_by_years() {
        engineersViewModel = EngineersViewModel(engineerList, view)

        engineersViewModel.engineerListSortAscendingBy(engineersViewModel.sortByYears)

        verify(view).updateRecyclerViewList(engineerList)
    }

    @Test
    fun verify_engineers_sorting_by_coffees() {
        engineersViewModel = EngineersViewModel(engineerList, view)

        engineersViewModel.engineerListSortAscendingBy(engineersViewModel.sortByCoffees)

        verify(view).updateRecyclerViewList(engineerList)
    }

    @Test
    fun verify_engineers_sorting_by_bugs() {
        engineersViewModel = EngineersViewModel(engineerList, view)

        engineersViewModel.engineerListSortAscendingBy(engineersViewModel.sortByBugs)

        verify(view).updateRecyclerViewList(engineerList)
    }

    @Test
    fun verify_list_item_clicked_navigates_to_about_fragment() {
        engineersViewModel = EngineersViewModel(engineerList, view)

        val engineer = MockData.engineers[0]

        engineersViewModel.listItemClicked(engineer)

        verify(view).navigateToAboutEngineer(engineer)
    }
}