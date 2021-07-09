package com.example.cryptowatch.ui.fragments.coins

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowatch.repositories.MainRepository

class CoinsViewModelFactory(val repository: MainRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinsViewModel(repository,application) as T
    }
}