package com.example.hedgehoglabapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hedgehoglabapp.databinding.FragmentFavoritesBinding
import com.example.hedgehoglabapp.ui.dialog.showCustomDialog
import com.example.hedgehoglabapp.ui.main.GalleryAdapter
import com.example.hedgehoglabapp.ui.main.HomeFragment.Companion.PRODUCT_LIST_COLUMN_NUM
import com.example.hedgehoglabapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    private val savedItemsAdapter: GalleryAdapter by lazy {
        GalleryAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(
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
        observeLiveData()
        observeSnackBar()
        observeNavigation()
        observeCustomDialog()
    }

    private fun initRecyclerView() {
        binding.savedItemsList.apply {
            layoutManager = StaggeredGridLayoutManager(
                PRODUCT_LIST_COLUMN_NUM,
                GridLayoutManager.VERTICAL
            ).apply {
                gapStrategy =
                    StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
            setHasFixedSize(true)
            adapter = savedItemsAdapter
        }
    }

    private fun observeLiveData() {
        binding.listener = viewModel
        viewModel.uiModelLiveData.observe(requireActivity()) {
            binding.uiModel = it
            savedItemsAdapter.submitList(it.galleryItemList)
        }
    }

    private fun observeSnackBar() {
        viewModel.customSnackBarLiveData.observe(requireActivity()) {
            binding.root.showSnackBar(it)
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(requireActivity()) {
            lifecycleScope.launchWhenResumed {
                findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragment2ToDetailsFragment(
                        it
                    )
                )
            }
        }
    }

    private fun observeCustomDialog() {
        viewModel.customDialogLiveData.observe(requireActivity()) {
            requireContext().showCustomDialog(it)
        }
    }
}