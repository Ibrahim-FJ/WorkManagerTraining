package com.example.workmanagertraining

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        activateWorkRequest(applicationContext)
    }
}


class TestWorkManager(context: Context, parameters: WorkerParameters): Worker(context, parameters) {
    override fun doWork(): Result {
        return try {
            println("Ibrahim Alfaifi")
            Result.success()
        }catch (e: Exception){
            Result.failure()
        }
    }
}


fun activateWorkRequest(context: Context){
    val imageWorker = OneTimeWorkRequestBuilder<TestWorkManager>()
        .setInitialDelay(15, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(imageWorker)

}