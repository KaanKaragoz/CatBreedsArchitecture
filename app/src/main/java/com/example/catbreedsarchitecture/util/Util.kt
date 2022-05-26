package com.example.catbreedsarchitecture.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catbreedsarchitecture.data.Breed
import com.example.catbreedsarchitecture.ui.home.HomeFragmentDirections

fun ImageView.downloadFromUrl(url:String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context:Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view:ImageView,url:String?){
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}

@BindingAdapter("android:navigationToFragment")
fun navigationToFragment(view: LinearLayout, breed: Breed) {
    view.setOnClickListener {
         val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(breed)
        Navigation.findNavController(view).navigate(action)
    }

}




