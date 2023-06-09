package com.uzcoder.countries.fragment

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.uzcoder.countries.activities.DetailsActivity
import com.uzcoder.countries.adapter.FlagAdapter
import com.uzcoder.countries.databinding.FragmentTwoBinding
import com.uzcoder.countries.helper.SimpleItemTouchHelperCallback
import com.uzcoder.countries.model.WelcomeElement
import com.uzcoder.countries.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlagsFragment : BaseFragment() {

    private var adapter : FlagAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews(view)
        Log.d("create","fragment created view")
        darkOrLightMode()
        return view
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initViews(view: View?) {
        adapter = FlagAdapter(requireContext(),{ seletedItem: WelcomeElement ->listItemClicked(seletedItem)})
        binding.apply {
            rvCoast.layoutManager = GridLayoutManager(requireContext(),4)
            rvCoast.adapter = adapter
        }

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(adapter!!)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvCoast)

        viewModel.allCountry.observe(requireActivity()) {coastList ->
            if (coastList != null) {
                binding.progressBar.visibility = View.GONE
            }else binding.progressBar.visibility = View.VISIBLE
            if (coastList != null) {
                adapter!!.setMovieList(coastList)
            }
        }
        viewModel.apiPostList()

        viewModel.message.observe(requireActivity(), Observer {
            it.getContentIfNotHandler()?.let {
                Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun darkOrLightMode() {
        when (resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.apply {
                    flBackground.setBackgroundColor(Color.parseColor("#424040"))
                    rvCoast.setBackgroundColor(Color.BLACK)
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    private fun listItemClicked(seletedItem: WelcomeElement) {
        openDetailsWithShared(requireContext(),seletedItem)
    }
}