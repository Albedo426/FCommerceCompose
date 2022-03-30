package com.mobilist.fcommercecompose.util

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlin.math.round
fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun Double.percentage(percentage: Int): String {
    var l=1.0
    if(percentage!=0){
        l = percentage.toDouble()/100
    }
    val price =this*l
    return "%.2f".format(price)
}
fun Double.percentageDouble(percentage: Int): Double {
    var l = 1.0
    if (percentage != 0) {
        l = percentage.toDouble() / 100
    }
    return this * l
}


/*
fun ImageView.downloadFromUrl(url:String?, circularProgressDrawable: CircularProgressDrawable){
    val optionos = RequestOptions()
        .placeholder(circularProgressDrawable)
        //.placeholder(placeHolderProgressBar(context)) test et
        .error(R.mipmap.ic_launcher)//defauld resim
    Glide.with(context)
        .setDefaultRequestOptions(optionos)
        .load(url)
        .into(this)

}
fun placeHolderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
    }
}
//xmlde çalışması için
@BindingAdapter("android:downloadUrl")
fun dowloandImage(imageView:ImageView,url:String?){
    imageView.downloadFromUrl(url, placeHolderProgressBar(imageView.context))
}*/
