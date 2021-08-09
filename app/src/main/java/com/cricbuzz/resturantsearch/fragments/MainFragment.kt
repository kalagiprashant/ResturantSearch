package com.cricbuzz.resturantsearch.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cricbuzz.resturantsearch.R
import com.cricbuzz.resturantsearch.adapters.ResturantAdapter
import com.cricbuzz.resturantsearch.databinding.FragmentMainBinding
import com.cricbuzz.resturantsearch.viewmodels.MainFragmentViewModel
import java.util.*

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var adapter: ResturantAdapter
    lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        initViews()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initViews() {
        binding.recyvlerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        val resturant = viewModel.getResturantModel(activity as Context)
        val menuItems = viewModel.getMenusModel(activity as Context)

        adapter = ResturantAdapter(resturant.restaurants, menuItems.menus,  activity as Context)
        binding.recyvlerView.adapter = adapter
    }


}