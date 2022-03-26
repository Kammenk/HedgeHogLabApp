package com.example.hedgehoglabapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hedgehoglabapp.databinding.FragmentHomeBinding
import com.example.hedgehoglabapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val galleryAdapter: GalleryAdapter by lazy {
        GalleryAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        initRecyclerView()
        initSearchViewListener()
        observeLiveData()
        observeSnackBar()
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(requireActivity()) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
    }

    private fun initSearchViewListener() {
        binding.gallerySearchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchImages(it)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    private fun observeSnackBar() {
        viewModel.customSnackBarLiveData.observe(requireActivity()) {
            binding.root.showSnackBar(it)
        }
    }

    private fun observeLiveData() {
        viewModel.uiModelLiveData.observe(requireActivity()) {
            binding.uiModel = it
            galleryAdapter.submitList(it.galleryItemList)
        }
    }

    private fun initRecyclerView() {
        binding.galleryList.apply {
            layoutManager = StaggeredGridLayoutManager(
                PRODUCT_LIST_COLUMN_NUM,
                GridLayoutManager.VERTICAL
            ).apply {
                gapStrategy =
                    StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
            setHasFixedSize(true)
            addOnScrollListener(addRecyclerViewScrollListener())
            adapter = galleryAdapter
        }
    }

    private fun addRecyclerViewScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!binding.galleryList.canScrollVertically(END_OF_VERTICAL_SCROLL)) {
                viewModel.onScrollAction()
            }
        }
    }

    companion object {
        const val PRODUCT_LIST_COLUMN_NUM = 2
        private const val END_OF_VERTICAL_SCROLL = 1
    }
}