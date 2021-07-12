package com.example.cryptowatch.ui.fragments.coinDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowatch.repositories.MainRepository

class CoinDetailsVMFactory(
    private val repository: MainRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinDetailsVM(repository, application) as T
    }
}