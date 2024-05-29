package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.view.EngineersView
import com.glucode.about_you.engineers.view.model.EngineersViewModel
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : Fragment(), EngineersView {
    private lateinit var binding: FragmentEngineersBinding
    private lateinit var viewModel: EngineersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        viewModel = EngineersViewModel(MockData.engineers.toMutableList(), this)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_years -> {
                viewModel.engineerListSortAscendingBy(viewModel.sortByYears)
                return true
            }
            R.id.action_coffees -> {
                viewModel.engineerListSortAscendingBy(viewModel.sortByCoffees)
                return true
            }
            R.id.action_bugs -> {
                viewModel.engineerListSortAscendingBy(viewModel.sortByBugs)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setupRecyclerViewList(engineersList: List<Engineer>) {
        updateRecyclerView(engineersList)
    }

    override fun updateRecyclerViewList(engineersList: List<Engineer>) {
        updateRecyclerView(engineersList)
    }

    private fun updateRecyclerView(engineersList: List<Engineer>) {
        binding.list.adapter = EngineersRecyclerViewAdapter(engineersList) {
            viewModel.listItemClicked(it)
        }
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    override fun navigateToAboutEngineer(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}