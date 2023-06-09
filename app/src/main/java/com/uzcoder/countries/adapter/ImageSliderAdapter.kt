package com.uzcoder.countries.adapter


import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.smarteist.autoimageslider.SliderViewAdapter
import com.uzcoder.countries.R
import com.uzcoder.countries.databinding.ItemImageSliderBinding
import com.uzcoder.countries.model.WelcomeElement


class ImageSliderAdapter(private val context: Context, imageList: List<String>) :
    SliderViewAdapter<ImageSliderAdapter.mySliderAdapter>() {
    private var imageList: List<String> = ArrayList<String>()

    init {
        this.imageList = imageList
    }

    override fun onCreateViewHolder(parent: ViewGroup): mySliderAdapter {
        val binding: ItemImageSliderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_image_slider,
            parent,
            false
        )
        return mySliderAdapter(binding)
    }

    override fun onBindViewHolder(viewHolder: mySliderAdapter, position: Int) {
        val model: String = imageList[position]
        Glide.with(context).load(model)
            .into(viewHolder.binding.imgSlider)

//        Glide.with(viewHolder.itemView)
//                .load(model.getUrl_slider_image())
//                .fitCenter()
//                .into(viewHolder.binding.imgSlider);
    }

    override fun getCount(): Int {
        return imageList.size
    }

    inner class mySliderAdapter(binding: ItemImageSliderBinding) :
        ViewHolder(binding.getRoot()) {
        val binding: ItemImageSliderBinding

        init {
            this.binding = binding
        }
    }
}