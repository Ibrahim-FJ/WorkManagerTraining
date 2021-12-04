package com.example.workmanagertraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkManagerViewModel: ViewModel() {
    private var _names = MutableLiveData("dhff")
    val names: LiveData<String>  = _names


    fun setNames(newNames: String?){
        println("setNames")
        _names.value = newNames
        println(_names.value)
    }
}

























