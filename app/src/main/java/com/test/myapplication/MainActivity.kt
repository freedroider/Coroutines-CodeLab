package com.test.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.myapplication.databinding.ActivityMainBinding
import com.test.myapplication.view_model.BaseViewModel
import com.test.myapplication.view_model.WithContextViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BaseViewModel
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * Здесь можно подменить ViewModel и посмотреть на результаты выполнения
         */
        viewModel = ViewModelProvider(this).get(WithContextViewModel::class.java)

        setContentView(viewBinding.root)

        with(viewModel) {
            counter1LD.observe(this@MainActivity) { viewBinding.tvCounter1.text = it.toString() }
            counter2LD.observe(this@MainActivity) { viewBinding.tvCounter2.text = it.toString() }
        }

        with(viewBinding) {
            btStart.setOnClickListener { viewModel.startCounter() }
            btStop.setOnClickListener { viewModel.stopCounter() }
        }
    }
}