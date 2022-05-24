package com.example.catbreedsarchitecture.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.catbreedsarchitecture.R
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.example.catbreedsarchitecture.databinding.BreedsItemsBinding

class BreedsFeedAdapter : ListAdapter<Breed, BreedsFeedAdapter.BreedsFeedViewHolder>(BreedsFeedDiffCallback()),BreedsClickListener  {
    class BreedsFeedViewHolder(var view: BreedsItemsBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BreedsItemsBinding>(inflater,R.layout.breeds_items,parent,false)
        return BreedsFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsFeedViewHolder, position: Int) {
        holder.view.breed = getItem(position)
        holder.view.listener = this

    }

    class BreedsFeedDiffCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.name == newItem.name


        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    override fun onItemClick(v: View) {
        Log.d("a","oldu")
        v.findViewById<ImageView>(R.id.btnLike).setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        Log.d("a","oldu")
    }


}