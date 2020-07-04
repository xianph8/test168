package com.test.test168.coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.test168.R
import kotlinx.coroutines.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class TestCoroutinesActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }
        GlobalScope.launch(handler) {

        }
        /*GlobalScope.launch {
            Log.i(TAG, "current thread name 1 : ${Thread.currentThread().name}")
        }

        Thread {
            Log.i(TAG, "current thread name 2 : ${Thread.currentThread().name}")
        }.start()

        thread {
            Log.i(TAG, "current thread name 3 : ${Thread.currentThread().name}")
        }*/

        val job = GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }
        job.cancel()

        GlobalScope.launch(Dispatchers.Main) {
            val io1 = async { ioCode1() }
            val io2 = async { ioCode2() }
            io1.await()
            io2.await()
            uiCode1()
        }

        classicIoCode1 {
            uiCode1()
        }

        classicIoCode1(false) {
            uiCode1()
        }

    }

    private val executor = ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, LinkedBlockingDeque())

    private fun classicIoCode1(uiThread: Boolean = true, block: () -> Unit) {
        executor.execute {
            Log.i(TAG, "current thread classicIoCode1 : ${Thread.currentThread().name}")
            if (uiThread) {
                runOnUiThread {
                    block()
                }
            } else {
                block()
            }
        }
    }

    private suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            Log.i(TAG, "current thread ioCode1 : ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode2() {
        withContext(Dispatchers.IO) {
            Log.i(TAG, "current thread ioCode2 : ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode3() {
        withContext(Dispatchers.IO) {
            Log.i(TAG, "current thread ioCode3 : ${Thread.currentThread().name}")
        }
    }

    private fun uiCode1() {
        Log.i(TAG, "current thread uiCode1 : ${Thread.currentThread().name}")
    }

    private fun uiCode2() {
        Log.i(TAG, "current thread uiCode2 : ${Thread.currentThread().name}")
    }

    private fun uiCode3() {
        Log.i(TAG, "current thread uiCode3 : ${Thread.currentThread().name}")
    }

}