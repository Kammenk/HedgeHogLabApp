package com.example.hedgehoglabapp.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.hedgehoglabapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

object AppBindingAdapter {

    @BindingAdapter("visibleGone")
    fun visibleGone(
        view: View,
        isVisible: Boolean
    ) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("setImageFromUrl")
    fun setImageFromUrl(
        view: ImageView,
        image: String?
    ) {
        image?.let {
            Glide.with(view.context)
                .load(it)
                .into(view)
        }

    }

    @BindingAdapter("setActionBtnIcon")
    fun setActionBtnIcon(
        view: FloatingActionButton,
        isLiked: Boolean
    ) {
        view.setImageResource(
            if (isLiked)
                R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
        )
    }
}