package com.test.myapplication.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay

abstract class BaseViewModel : ViewModel() {
    val counter1LD = MutableLiveData<Int>()
    val counter2LD = MutableLiveData<Int>()

    var counterJob: Job? = null

    abstract fun startCounter()

    open fun stopCounter() {
        counterJob?.cancelChildren()
        counterJob = null
        counter1LD.value = 0
        counter2LD.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        stopCounter()
    }

    open suspend fun doSomething(liveData: MutableLiveData<Int>): List<Int> {
        var counter = 0
        while (counter < 10) {
            counter++
            liveData.postValue(counter)
            delay(1_000)
        }
        return listOf(1, 2, 3)
    }

    companion object {
        const val LOG_TAG = "Counter"
    }
}