package com.example.workmanagertraining

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.work.*
import com.example.workmanagertraining.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private val viewModel: WorkManagerViewModel by viewModels{
        WorkManagerViewModel.BlurViewModelFactory(
            application
        )
    }


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.workManagerViewModel = viewModel

        activateWorkRequest(applicationContext)
    //    workManagerViewModel.setNames("onCreate")



    }


}



class FirstWorkManager(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    override fun doWork(): Result {
        return try {
            var input = inputData.getString("Team_45")
         //   workManagerViewModel.setNames(input)
            input = "Khaled, Aymen"
       //     MainActivity().test(input)


            Result.success(workDataOf("Team_45" to input))
        } catch (e: Exception) {
            println("Failure1")
            Result.failure()
        }
    }
}

class SecondWorkManger(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        return try {
            var input = inputData.getString("Team_45")

           // workManagerViewModel.setNames(input)

            input = "Khaled, Aymen, Rakan"

            Result.success(workDataOf("Team_45" to input))
        } catch (e: Exception) {
            Result.failure()
        }
    }

}

class ThirdWorkManger(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        return try {
            var input = inputData.getString("Team_45")
           // workManagerViewModel.setNames(input)
            input = "Khaled, Aymen, Rakan, Ibrahim"


            Result.success(workDataOf("Team_45" to input))
        } catch (e: Exception) {
            Result.failure()
        }
    }

}


class ForthWorkManger(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        return try {
            val input = inputData.getString("Team_45")
        //    workManagerViewModel.setNames(input)

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

}

fun activateWorkRequest(context: Context) {
    val firstWorkManager = OneTimeWorkRequestBuilder<FirstWorkManager>()
        .setInputData(setInput())
        .build()


    var continuation = WorkManager.getInstance(context)
        .beginUniqueWork(
            "WorkManagerTraining",
            ExistingWorkPolicy.REPLACE,
            firstWorkManager
        )

    val secondWorkManager = OneTimeWorkRequestBuilder<SecondWorkManger>()

    continuation = continuation.then(secondWorkManager.build())

    val thirdWorkManager = OneTimeWorkRequestBuilder<ThirdWorkManger>()
    continuation = continuation.then(thirdWorkManager.build())



    val forthWorkManger = OneTimeWorkRequestBuilder<ForthWorkManger>()
    continuation = continuation.then(forthWorkManger.build())

    continuation.enqueue()


    continuation.enqueue()

}


/**
 * Input and output is passed in and out via Data objects. Data objects are lightweight containers for key/value pairs.
 * They are meant to store a small amount of data that might pass into and out from WorkRequests.
 */


fun setInput(): Data {

    return Data.Builder().putString("Team_45", "Jana").build()
}

fun sleep() {
    try {
        Thread.sleep(5000, 0)
    } catch (e: InterruptedException) {
        Log.e("TAG", e.message.toString())
    }

}
