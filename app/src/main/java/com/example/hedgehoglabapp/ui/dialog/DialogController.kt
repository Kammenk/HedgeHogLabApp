package com.example.hedgehoglabapp.ui.dialog

import android.content.Context
import com.example.hedgehoglabapp.R

fun Context.showCustomDialog(customDialog: CustomDialog) {
    when (customDialog) {
        is RemoveItemAlertDialog -> showRemoveItemAlert(customDialog.removeItemCallback)
        is RemoveAllItemsAlertDialog -> showRemoveAllItemsAlert(customDialog.removeAllItemsCallback)
    }
}

fun Context.showRemoveItemAlert(
    removeItemCallback: () -> Unit
) {
    androidx.appcompat.app.AlertDialog.Builder(this)
        .setTitle(R.string.remove_item_title)
        .setMessage(R.string.remove_item_message)
        .setPositiveButton(R.string.positive_answer) { _, _ ->
            removeItemCallback.invoke()
        }
        .setNegativeButton(R.string.negative_answer) { d, _ -> d.dismiss() }
        .create()
        .show()
}

fun Context.showRemoveAllItemsAlert(
    removeAllItemsCallback: () -> Unit
) {
    androidx.appcompat.app.AlertDialog.Builder(this)
        .setTitle(R.string.remove_all_items_title)
        .setMessage(R.string.remove_all_items_message)
        .setPositiveButton(R.string.positive_answer) { _, _ ->
            removeAllItemsCallback.invoke()
        }
        .setNegativeButton(R.string.negative_answer) { d, _ -> d.dismiss() }
        .create()
        .show()
}