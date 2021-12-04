package com.example.workmanagertraining

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import java.lang.ArithmeticException

class WorkManagerViewModel(application: Application): ViewModel() {
    private var _names = MutableLiveData("dhff")
    val names: LiveData<String>  = _names



    init {
        WorkManager.getInstance(application).getWorkInfosByTag("WorkManagerTraining")
        test(application.applicationContext)
    }

    fun setNames(newNames: String?){
        println("setNames")
        _names.value = newNames
        println(_names.value)


    }


    private fun test(context: Context): Uri{
        val resource = context.resources
        val text = Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE).build()
        return text
    }



    class BlurViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(WorkManagerViewModel::class.java)) {
                WorkManagerViewModel(application) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }


}


























