package com.uzcoder.countries

import android.R
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uzcoder.countries.adapter.ViewPagerAdapter
import com.uzcoder.countries.database.entity.SaveData
import com.uzcoder.countries.databinding.ActivityMainBinding
import com.uzcoder.countries.util.ConnectionLiveData
import com.uzcoder.countries.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.uzcoder.countries.util.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var adapter: ViewPagerAdapter? = null
    private lateinit var cld: ConnectionLiveData
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkNetworkConnection()
        initViews()
//        myEnter()
        hideKeyboard()
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(application)
        cld.observe(this) { isConnected ->

            if (isConnected) {
                binding.apply {
                    flWifi1.visibility = View.VISIBLE
                    flWifi2.visibility = View.GONE
                    saveTryDatabase()
                }

            } else {
                binding.apply {
                    flWifi1.visibility = View.GONE
                    flWifi2.visibility = View.VISIBLE
                    Toast.makeText(this@MainActivity, "Internet not connect!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    private fun initViews() {
        if (!Utils.isNetworkAvailable(this)) {
            binding.apply {
                flWifi1.visibility = View.GONE
                flWifi2.visibility = View.VISIBLE
            }
        }
        val tabTitles = arrayOf("Country name", "Flags", "Coat of arms")
        adapter = ViewPagerAdapter(this)
        binding.apply {
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
            ivSearch.setOnClickListener {
                adapter!!.setData(etSearch.text.toString())
                viewPager.adapter = adapter
                etSearch.setText("")
                hideKeyboard()
            }
        }

        viewModel.subscribers.observe(this) {
            if (it.isNotEmpty()) {
                for (i in it) {
                    if (i.name!!.isNotEmpty()) {
                        binding.apply {
                            val adapterArray = ArrayAdapter<String>(
                                this@MainActivity,
                                R.layout.simple_list_item_1,
                                i.name!!
                            )
                            etSearch.setAdapter(adapterArray)
                            etSearch.threshold = 1
                        }
                    }
                }
            }
            Log.d("FromDatabase", "$$$$ + $it")
        }

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.apply {
                    clBody.setBackgroundColor(Color.BLACK)
                    appBar.setBackgroundResource(com.uzcoder.countries.R.drawable.appbar_resource_dark_mode)
                    tvTitle.setTextColor(Color.parseColor("#FFFFFF"))
                    ivMenu.setColorFilter(Color.WHITE)
                    ivWifi2.setColorFilter(Color.WHITE)
                    ivSearch.setColorFilter(Color.WHITE)
                    etSearch.setBackgroundResource(com.uzcoder.countries.R.drawable.edittext_layout_dark_mode)
                    tabLayout.setTabTextColors(
                        Color.parseColor("#FFFFFF"),
                        Color.parseColor("#ffffff")
                    )
                    getWindow().setStatusBarColor(getResources().getColor(R.color.black, theme))
                }
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                getWindow().setStatusBarColor(getResources().getColor(R.color.black, theme))
            }

            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    private fun saveTryDatabase() {
        val nameArray: ArrayList<String> = ArrayList()
        viewModel.subscribers.observe(this) {
            if (it.isNotEmpty()) {
                for (i in it) {
                    if (i.name!!.isEmpty()) {
                        viewModel.allCountry.observe(this) { coastList ->
                            if (coastList != null) {
                                for (i in coastList) {
                                    nameArray.add(i.name.common)
                                }
                            }
                            viewModel.clearAll()
                            val saveData = SaveData()
                            saveData.id = 1
                            saveData.name = nameArray
                            viewModel.insert(saveData)
                        }
                        viewModel.apiPostList()
                    }

                }
            } else {
                viewModel.allCountry.observe(this) { coastList ->
                    if (coastList != null) {
                        for (i in coastList) {
                            nameArray.add(i.name.common)
                        }
                    }
                    val saveData = SaveData()
                    saveData.id = 1
                    saveData.name = nameArray
                    viewModel.insert(saveData)
                }
                viewModel.apiPostList()
            }
        }
    }

    fun myEnter() {
        binding.apply {
            etSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    hideKeyboard()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    /** Hide our keyboard **/
    fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}