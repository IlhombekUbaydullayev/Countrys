package com.uzcoder.countries.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uzcoder.countries.R
import com.uzcoder.countries.databinding.ItemLayoutFlagBinding
import com.uzcoder.countries.helper.ItemTouchHelperAdapter
import com.uzcoder.countries.model.WelcomeElement
import com.bumptech.glide.Glide
import java.util.Collections

class FlagAdapter(
    val context : Context,
    private val clickListener: (WelcomeElement) -> Unit
): RecyclerView.Adapter<FlagViewHolder>(), ItemTouchHelperAdapter {
    var movies = mutableListOf<WelcomeElement>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movies: List<WelcomeElement>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutFlagBinding.inflate(inflater, parent, false)
        return FlagViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FlagViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            Glide.with(context).load(movie.flags.png).placeholder(R.drawable.spinner_loader).into(ivCoast)
            tvCoast.text = movie.cca3
        }
        holder.binding.llPosts.setOnClickListener {
            clickListener(movie)
        }
        when (context.resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                holder.binding.tvCoast.setTextColor(Color.WHITE)
            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(movies, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(movies, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        movies.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
class FlagViewHolder(val binding: ItemLayoutFlagBinding) : RecyclerView.ViewHolder(binding.root) {
}