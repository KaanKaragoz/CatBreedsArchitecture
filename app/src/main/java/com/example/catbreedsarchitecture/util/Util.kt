package com.example.catbreedsarchitecture.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catbreedsarchitecture.data.source.local.BreedsLocalRepository
import com.squareup.picasso.Picasso
import javax.inject.Inject


/* G L I D E */
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

@BindingAdapter("android:downloadUrl") // databinding de bu fonksiyon çağırılacak
fun downloadImage(view:ImageView,url:String?){
    //Picasso.get().load(url).into(view)
    view.downloadFromUrl(url, placeholderProgressBar(view.context))

}


