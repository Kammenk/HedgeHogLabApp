package com.example.hedgehoglabapp.ui.dialog

sealed class CustomDialog
class RemoveItemAlertDialog(val removeItemCallback: () -> Unit): CustomDialog()
class RemoveAllItemsAlertDialog(val removeAllItemsCallback: () -> Unit) : CustomDialog()