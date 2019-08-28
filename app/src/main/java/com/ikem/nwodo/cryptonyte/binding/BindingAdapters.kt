package com.ikem.nwodo.cryptonyte.binding

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.ikem.nwodo.cryptonyte.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageBackground")
    fun setImageViewBackground(imageView: ImageView, coinColor: String?){
        val drawable = ContextCompat.getDrawable(imageView.context, R.drawable.coin_icon_drawable)

        val gradientDrawable = drawable as GradientDrawable
        if (coinColor?.length == 4 || coinColor == null){
            gradientDrawable.setStroke(4, Color.parseColor("#000000"))
            gradientDrawable.setColor(Color.parseColor("#000000"))
        } else{
            gradientDrawable.setStroke(4, Color.parseColor(coinColor))
            gradientDrawable.setColor(Color.parseColor(coinColor))
        }
        imageView.background = gradientDrawable

    }

    @BindingAdapter("imageResource")
    fun setImage(imageView: ImageView, iconUrl: String?){
        GlideToVectorYou.justLoadImage(Activity(), Uri.parse(iconUrl), imageView)
    }
}