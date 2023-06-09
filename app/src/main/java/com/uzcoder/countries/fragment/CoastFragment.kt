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
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.uzcoder.countries.R
import com.uzcoder.countries.activities.DetailsActivity
import com.uzcoder.countries.adapter.CoastAdapter
import com.uzcoder.countries.databinding.FragmentThreeBinding
import com.uzcoder.countries.helper.SimpleItemTouchHelperCallback
import com.uzcoder.countries.model.WelcomeElement
import com.uzcoder.countries.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoastFragment : BaseFragment() {
    private var adapter : CoastAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("create","fragment created")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews(view)
        darkOrLightMode()
        Log.d("create","fragment created view")
        return view
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initViews(view: View?) {
        adapter = CoastAdapter(requireContext(),{ seletedItem: WelcomeElement ->listItemClicked(seletedItem)})
        binding.apply {
            rvCoast.layoutManager = GridLayoutManager(requireContext(),5)
            val animation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
            rvCoast.layoutAnimation = animation
            rvCoast.adapter = adapter
        }

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(adapter!!)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvCoast)

            viewModel.allCountry.observe(requireActivity()) {coastList ->
                if (coastList != null) {
                    binding.progressBar.visibility = View.GONE
                    adapter!!.setMovieList(coastList)
                }else {
                    binding.progressBar.visibility = View.VISIBLE
                }
        }
        viewModel.apiPostList()
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