package com.test.myapplication.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class OwnScopeViewModel : BaseViewModel(), CoroutineScope {
    private val supervisorJob = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.w(LOG_TAG, throwable.stackTraceToString())
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + supervisorJob + exceptionHandler

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    override fun startCounter() {
        /**
         * Создаем и запускаем корутину привязанную к OwnScopeViewModel с собственным CoroutineScope
         * (в реальных проектах можно перенести скоуп в BaseViewModel).
         * Сохраняем ссылку на Job, чтобы можно было отменить выполнение корутины в методе stopCounter
         */
        counterJob = launch {
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
        }
    }

    override suspend fun doSomething(liveData: MutableLiveData<Int>): List<Int> {
        /**
         * Переопределяем метод doSomething чтобы он выкидывал исключение
         */
        super.doSomething(liveData)
        throw Exception()
    }
}