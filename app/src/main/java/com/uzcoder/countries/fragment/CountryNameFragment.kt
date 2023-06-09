package com.uzcoder.countries.fragment

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.uzcoder.countries.R
import com.uzcoder.countries.databinding.FragmentOneBinding
import com.uzcoder.countries.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.uzcoder.countries.adapter.DataAdapter
import com.uzcoder.countries.utils.Facts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryNameFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentOneBinding? = null
    var adapterData : DataAdapter? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = arguments
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews(view,bundle)
        darkOrLightMode()
        return view
    }

    private fun initViews(view: View?, bundle: Bundle?) {
        val message = bundle?.getString("fragment1Container")
        if (message != null) {
            binding.progressBar.visibility = View.VISIBLE

        }else {
            binding.recycler.layoutParams.height = 0
            binding.progressBar.visibility = View.GONE
        }
        try {
            viewModel.allCountryByName.observe(requireActivity()) { coastList ->
                coastList.forEach {
                    binding.apply {
                        Glide.with(requireContext()).load(it.flags.png).into(ivTitle)
                        tvCountrName.text = it.name.common
                        tvCountrNameOrg.text = it.name.official
                        it.capital?.forEach { tvCapitalName.text = "Capital : ${it}" }
                        tvSum.text = "******"
                        tvSymbolSum.text = "******"
                        tvLanguage.text = it.cca2
                        tvBorders.text = "Borders : ${it.borders}"
                        tvArea.text = "Area : ${it.area} км²"
                        tvPeople.text = "Population : ${it.population}"
                        var text = ""
                        it.car.signs?.forEach { text += it }
                        tvCar.text = "Car : signs - ${text}, Side : ${it.car.side}"
                        tvContinents.text = it.region.name
                        progressBar.visibility = View.GONE
                        Glide.with(requireContext()).load(it.coatOfArms.png).into(ivArms)
                        Glide.with(requireContext()).load(it.flags.png).into(ivFlag)
                        tvFlagDesc.text = it.flags.alt
                        tvBtn.text = "Interesting facts about ${it.name.common}"
                        when(it.name.common) {
                            "Uzbekistan" -> {
                                adapterData = DataAdapter(Facts.uzbekistan())
                                tvSum.text = it.currencies!!.UZS.name
                                tvSymbolSum.text = "Symbol : ${it.currencies!!.UZS.symbol}"
                            }else -> {
                            binding.recycler.layoutParams.height = 0
                        }
                        }
                        binding.recycler.apply {
                            this.adapter = adapterData
                            set3DItem(true)
                            setAlpha(true)
                            setInfinite(true)
                        }
                    }
                }
            }
        }catch (e : Exception) {

        }
        message?.let { viewModel.apiGetByName(it) }

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
                    binding.fragmentCountryName.setBackgroundColor(Color.BLACK)
                    tvCountrName.setTextColor(Color.WHITE)
                    tvCountrNameOrg.setTextColor(Color.WHITE)
                    tvCapitalName.setTextColor(Color.WHITE)
                    llLline.setBackgroundColor(Color.WHITE)
                    tvSum.setTextColor(Color.WHITE)
                    tvSymbolSum.setTextColor(Color.WHITE)
                    llBackground.setBackgroundResource(R.drawable.restange_layout_dark_mode)
                    tvLanguage.setTextColor(Color.WHITE)
                    tvBorders.setTextColor(Color.WHITE)
                    tvArea.setTextColor(Color.WHITE)
                    tvPeople.setTextColor(Color.WHITE)
                    tvCar.setTextColor(Color.WHITE)
                    tvContinents.setTextColor(Color.WHITE)
                    tvCoast.setTextColor(Color.WHITE)
                    tvFlag.setTextColor(Color.WHITE)
                    tvFlagDesc.setTextColor(Color.WHITE)
                    ivArea.setColorFilter(Color.WHITE)
                    tvBtn.setTextColor(Color.WHITE)
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }
}