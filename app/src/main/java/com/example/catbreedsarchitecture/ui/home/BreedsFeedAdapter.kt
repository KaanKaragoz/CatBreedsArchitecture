package com.example.catbreedsarchitecture.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.catbreedsarchitecture.R
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.databinding.BreedsItemsBinding

class BreedsFeedAdapter : ListAdapter<Breed, BreedsFeedAdapter.BreedsFeedViewHolder>(BreedsFeedDiffCallback()) {
    class BreedsFeedViewHolder(var view: BreedsItemsBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BreedsItemsBinding>(inflater,R.layout.breeds_items,parent,false)
        return BreedsFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsFeedViewHolder, position: Int) {
    holder.view.breed = getItem(position)

    }

    class BreedsFeedDiffCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.name == newItem.name


        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }




}