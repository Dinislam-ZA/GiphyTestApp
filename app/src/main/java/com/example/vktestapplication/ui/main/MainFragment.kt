package com.example.vktestapplication.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vktestapplication.appComponent
import com.example.vktestapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(), GifClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var factory: MainViewModelFactory

    val viewModel: MainViewModel by viewModels { factory }
    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        context?.let { MainAdapter(it, this) }
    }


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        with(binding){
            rcView.adapter = adapter
            rcView.layoutManager = GridLayoutManager(activity, 2)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.checkFun()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.setQuery(newText ?: "")
                    return false
                }

            })
        }

        viewModel.query
            .flowWithLifecycle(this.lifecycle, Lifecycle.State.CREATED)
            .launchIn(this.lifecycleScope)

        this.lifecycleScope.launch {
            viewModel.gifs.collectLatest { pagingData -> adapter?.submitData(pagingData) }
        }


        return binding.root
    }

    override fun onGifClick() {
        TODO("Not yet implemented")
    }


}