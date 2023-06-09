package com.uzcoder.countries.activities

import android.R
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uzcoder.countries.MainActivity
import com.uzcoder.countries.database.entity.SaveData
import com.uzcoder.countries.databinding.ActivitySplashBinding
import com.uzcoder.countries.manager.PrefsManager
import com.uzcoder.countries.util.ConnectionLiveData
import com.uzcoder.countries.util.Utils
import com.uzcoder.countries.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    var timer = 0
    private lateinit var cld : ConnectionLiveData
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        countDownTimer()
    }

    private fun initViews() {
        cld = ConnectionLiveData(application)
        val nameArray : ArrayList<String> = ArrayList()
        viewModel.subscribers.observe(this) {
            if (it.isEmpty()) {
                cld.observe(this) { isConnected ->
                    if (isConnected) {
                        binding.apply {
                            viewModel.allCountry.observe(this@SplashActivity) {coastList ->
                                if (coastList != null) {
                                    for (i in coastList) {
                                        nameArray.add(i.name.common)
                                    }
                                }
                                val saveData = SaveData()
                                saveData.id = 1
                                saveData.name = nameArray
                                Log.d("Arrays","Saqlandi += ${saveData.name}" )
                                viewModel.insert(saveData)
                            }
                            viewModel.apiPostList()
                        }
                    } else {
                        binding.apply {
                        }
                    }

                }
            }else {
            }
        }

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.apply {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.black, theme))
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                getWindow().setStatusBarColor(getResources().getColor(R.color.black, theme))
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    private fun countDownTimer() {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
                timer++
                binding.progress.setProgress(timer * 100 / (5000 / 1000))
                binding.tvProgress.text = "${timer*20}%"
            }
            override fun onFinish() {
                timer++
                binding.progress.setProgress(100)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}