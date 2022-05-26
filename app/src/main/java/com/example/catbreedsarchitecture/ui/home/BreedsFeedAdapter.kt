package com.example.catbreedsarchitecture.ui.home


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreedsarchitecture.R
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.databinding.BreedsItemsBinding
import com.example.catbreedsarchitecture.util.BreedClickListener

class BreedsFeedAdapter( val onFavouriteChanged : (String?, Boolean?) -> Unit ) : BreedClickListener, ListAdapter<Breed, BreedsFeedAdapter.BreedsFeedViewHolder>(BreedsFeedDiffCallback())  {
    class BreedsFeedViewHolder(var view: BreedsItemsBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BreedsItemsBinding>(inflater,R.layout.breeds_items,parent,false)
        return BreedsFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsFeedViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.breed = item

        if (item.IsCatliked == true) {
            holder.view.btnLike.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
        else {
            holder.view.btnLike.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.view.btnLike.setOnClickListener {
            onFavouriteChanged(item.name, item.IsCatliked)
        }


        holder.view.executePendingBindings()
    }

    class BreedsFeedDiffCallback : DiffUtil.ItemCallback<Breed>() {

        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.name == newItem.name && oldItem.IsCatliked == newItem.IsCatliked
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    override fun onBreedClicked(v: View) {

        v.setOnClickListener {
            val k = "asd"
            Log.d("asd",k)
        }
        val name = v.findViewById<TextView>(R.id.catName).text

       // val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(name)
        //Navigation.findNavController().navigate(action)
    }


}