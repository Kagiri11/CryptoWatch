package com.example.cryptowatch.ui.fragments.coins

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptowatch.models.Coins
import com.example.cryptowatch.repositories.MainRepository
import com.example.cryptowatch.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinsViewModel(val repository: MainRepository,application: Application) : AndroidViewModel(application) {

    private val _coins : MutableLiveData<Resource<Coins>> = MutableLiveData()
    val coins : LiveData<Resource<Coins>> = _coins

    private fun fetchCoinMarketData() = viewModelScope.launch {
        _coins.postValue(Resource.Loading())
        val result=repository.fetchCoinMarketData()
        _coins.postValue(dealWithCoinsResponse(result))
    }

    private fun dealWithCoinsResponse(response: Response<Coins>):Resource<Coins>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)

            }
        }
        return  Resource.Error(response.message())
    }
}