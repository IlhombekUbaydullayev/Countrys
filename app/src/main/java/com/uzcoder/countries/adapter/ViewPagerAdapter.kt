package com.uzcoder.countries.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uzcoder.countries.fragment.CoastFragment
import com.uzcoder.countries.fragment.CountryNameFragment
import com.uzcoder.countries.fragment.FlagsFragment
import com.google.gson.Gson


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private var fragment1Container: String? = null

    override fun getItemCount(): Int {
        return 3
    }

    fun setData(container: String) {
        fragment1Container = container
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> {
                val fragment1 = CountryNameFragment()

                val bundle = Bundle()
                val gson = Gson()

                bundle.putString("fragment1Container", fragment1Container)
                fragment1.setArguments(bundle)
                return fragment1
            }
            1 -> return FlagsFragment()
            2 -> return CoastFragment()
        }
        return CountryNameFragment()
    }

}