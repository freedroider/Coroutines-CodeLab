package com.test.myapplication.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class WithContextViewModel : BaseViewModel() {
    override fun startCounter() {
        /**
         * Создаем и запускаем корутину привязанную к viewModelScope и сохраняем ссылку на Job, чтобы можно было
         * отменить выполнение корутины в методе stopCounter
         */
        counterJob = viewModelScope.launch {
            /**
             * Создаем дочернюю корутину для последовательного выполнения задачи и сохранения результата в list1
             */
            val list1 = withContext(Dispatchers.IO) {
                doSomething(counter1LD)
            }

            /**
             * Создаем дочернюю корутину для последовательного выполнения задачи и сохранения результата в list2
             */
            val list2 = withContext(Dispatchers.IO)  {
                doSomething(counter2LD)
            }
            Log.w(LOG_TAG, "list1: ${list1.joinToString()}")
            Log.w(LOG_TAG, "list2: ${list2.joinToString()}")
        }
    }
}