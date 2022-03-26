package com.example.hedgehoglabapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hedgehoglabapp.databinding.FragmentDetailsBinding
import com.example.hedgehoglabapp.ui.dialog.showCustomDialog
import com.example.hedgehoglabapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        observeLiveData()
        observeSnackBar()
        observeCustomDialog()
    }

    private fun observeCustomDialog() {
        viewModel.customDialogLiveData.observe(requireActivity()) {
           requireContext().showCustomDialog(it)
        }
    }

    private fun observeLiveData() {
        binding.listener = viewModel
        viewModel.uiModelLiveData.observe(requireActivity()) {
            binding.uiModel = it
        }
    }

    private fun observeSnackBar() {
        viewModel.customSnackBarLiveData.observe(requireActivity()) {
            binding.root.showSnackBar(it)
        }
    }
}