package com.uzcoder.countries.activities

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderView
import com.uzcoder.countries.R
import com.uzcoder.countries.adapter.DataAdapter
import com.uzcoder.countries.adapter.ImageSliderAdapter
import com.uzcoder.countries.databinding.ActivityDetailsBinding
import com.uzcoder.countries.model.WelcomeElement
import com.uzcoder.countries.utils.Facts

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var adapter : ImageSliderAdapter? = null
    var adapterData : DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        darkOrLightMode()
        window.statusBarColor = ContextCompat.getColor(this,R.color.black)
        val modelList : ArrayList<String> = ArrayList()
        val item = intent.getSerializableExtra("item") as WelcomeElement
        binding.apply {
//            Glide.with(this@DetailsActivity).load(item.flags.png).into(ivTitle)
            modelList.add(item.flags.png)
            item.coatOfArms.png?.let { modelList.add(it) }
            tvCountrName.text = item.name.common
            tvCountrNameOrg.text = item.name.official
            item.capital?.forEach { tvCapitalName.text = "Capital : ${it}" }
            tvSum.text = "******"
            tvSymbolSum.text = "******"
            tvLanguage.text = item.cca2
            tvBorders.text = "Borders : ${item.borders}"
            tvArea.text = "Area : ${item.area} км²"
            tvPeople.text = "Population : ${item.population}"
            var text = ""
            item.car.signs?.forEach { text += it }
            tvCar.text = "Car : signs - ${text}, Side : ${item.car.side}"
            tvContinents.text = item.region.name
            Glide.with(this@DetailsActivity).load(item.coatOfArms.png).into(ivArms)
            Glide.with(this@DetailsActivity).load(item.flags.png).into(ivFlag)
            tvFlagDesc.text = item.flags.alt
            tvBtn.text = "Interesting facts about ${item.name.common}"
        }
        adapter = ImageSliderAdapter(this,modelList)
        binding.autoImageSlider.startAutoCycle()
        binding.autoImageSlider.setAutoCycle(true)

        binding.autoImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.autoImageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        binding.autoImageSlider.setScrollTimeInSec(3)
        binding.autoImageSlider.setSliderAdapter(adapter!!)
        when(item.name.common) {
            "Uzbekistan" -> {
                adapterData = DataAdapter(Facts.uzbekistan())
                binding.tvSum.text = item.currencies!!.UZS.name
                binding.tvSymbolSum.text = "Symbol : ${item.currencies!!.UZS.symbol}"
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