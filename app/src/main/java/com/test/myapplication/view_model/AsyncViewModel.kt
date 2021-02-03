package com.test.myapplication.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class AsyncViewModel : BaseViewModel() {
    override fun startCounter() {
        /**
         * Создаем и запускаем корутину привязанную к viewModelScope и сохраняем ссылку на Job, чтобы можно было
         * отменить выполнение корутины в методе stopCounter
         */
        counterJob = viewModelScope.launch {
            /**
             * Создаем дочернюю корутину для паралельного выполнения задачи и сохраняем ссылку на Deferred
             * для ожидания выполнения и получения результатов корутины
             */
            val deferred1 = async {
                doSomething(counter1LD)
            }

            /**
             * Создаем дочернюю корутину для паралельного выполнения задачи и сохраняем ссылку на Deferred
             * для ожидания выполнения и получения результатов корутины
             */
            val deferred2 = async {
                doSomething(counter2LD)
            }
            /**
             *  Дожидаемся результатов и распаковываем результаты при помощи Destructuring Declarations
             *  https://kotlinlang.org/docs/reference/multi-declarations.html
             *  в переменные list1 и list2.
             *  Так-же можно попробовать следующие варианты распаковки:
             *  val (list1) = awaitAll(deffered1, deffered2)
             *  val (_, list2) = = awaitAll(deffered1, deffered2)
             */
            val (list1, list2) = awaitAll(deferred1, deferred2)
            Log.w(LOG_TAG, "list1: ${list1.joinToString()}")
            Log.w(LOG_TAG, "list2: ${list2.joinToString()}")
        }
    }
}