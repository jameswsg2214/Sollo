package com.example.furniture.addFurniture.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddFurnitureViewModelFactory(private var application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddFurnitureViewModel(application) as T
    }
}