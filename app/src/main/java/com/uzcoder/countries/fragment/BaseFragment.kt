package com.uzcoder.countries.fragment

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.uzcoder.countries.activities.DetailsActivity
import com.uzcoder.countries.model.WelcomeElement

open class BaseFragment : Fragment() {
    open fun openDetailsWithShared(context : Context,seletedItem : WelcomeElement) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("item",seletedItem)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity
        )
        startActivity(intent,options.toBundle())
    }
}