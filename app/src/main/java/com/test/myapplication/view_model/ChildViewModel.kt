package com.test.myapplication.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildViewModel : BaseViewModel() {
    override fun startCounter() {
        /**
         * Создаем и запускаем корутину привязанную к viewModelScope и сохраняем ссылку на Job, чтобы можно было
         * отменить выполнение корутины в методе stopCounter
         */
        counterJob = viewModelScope.launch {
            /**
             * Создаем дочернюю корутину и запускаем выполнение
             */
            launch(Dispatchers.IO) {
                val list1 = doSomething(counter1LD)
                Log.w(LOG_TAG, "list1: ${list1.joinToString()}")
            }

            /**
             * Создаем дочернюю корутину и запускаем выполнение
             */
            launch(Dispatchers.IO) {
                val list2 = doSomething(counter2LD)
                Log.w(LOG_TAG, "list2: ${list2.joinToString()}")
            }
            Log.w(LOG_TAG, "startCounter() function COMPLETED")
        }
    }
}