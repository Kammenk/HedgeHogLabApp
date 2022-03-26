package com.example.hedgehoglabapp

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.hedgehoglabapp.ui.binding.AppDataBindingComponent

@HiltAndroidApp
class HedgehogApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initialize Data Binding Adapter
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }
}