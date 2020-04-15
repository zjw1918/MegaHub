package io.mega.megahub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class NameViewModel: ViewModel() {
//    init {
//        Timber.d("ViewModel instance created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Timber.d("ViewModel instance about to be destroyed")
//    }

    val currentName: MutableLiveData<String>  by lazy { MutableLiveData<String>() }

}