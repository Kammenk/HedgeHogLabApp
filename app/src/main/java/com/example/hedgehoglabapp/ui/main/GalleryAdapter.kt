package com.example.hedgehoglabapp.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.hedgehoglabapp.R
import com.example.hedgehoglabapp.databinding.GalleryItemLayoutBinding
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel
import com.example.hedgehoglabapp.utils.DataBoundListAdapter

class GalleryAdapter(val listener: OpenItemListener) : DataBoundListAdapter<PexelsImagesItemUiModel,
        GalleryItemLayoutBinding>(GalleryItemDiffUtil()) {

    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): GalleryItemLayoutBinding = createViewDataBinding(
        R.layout.gallery_item_layout,
        parent
    )


    private fun createViewDataBinding(
        @LayoutRes layout: Int,
        parent: ViewGroup
    ): GalleryItemLayoutBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )

    override fun bind(
        binding: GalleryItemLayoutBinding,
        item: PexelsImagesItemUiModel
    ) {
        binding.uiModel = item
        binding.listener = listener
    }
}

class GalleryItemDiffUtil : DiffUtil.ItemCallback<PexelsImagesItemUiModel>() {
    override fun areItemsTheSame(
        oldItem: PexelsImagesItemUiModel,
        newItem: PexelsImagesItemUiModel
    ): Boolean = oldItem.imageId == newItem.imageId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: PexelsImagesItemUiModel,
        newItem: PexelsImagesItemUiModel
    ): Boolean = oldItem == newItem

}