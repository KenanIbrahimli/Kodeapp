package com.knni.kode_app.utils

import android.text.TextUtils
import android.widget.ImageView
import com.knni.kode_app.R
import com.squareup.picasso.Picasso

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}

fun String.convertDate(birtday: String): String{
    var month = birtday.substring(5, 7)
    var _month = when(month){
        "01" -> "Январь"
            "02" -> "Февраль"
        "03" -> "Март"
        "04" -> "Апрель"
        "05" -> "Май"
        "06" -> "Июнь"
        "07" -> "Июль"
        "08" -> "Август"
        "09" -> "Сентябрь"
        "10" -> "Октябрь"
        "10" -> "Ноябрь"
        "11" -> "Декабрь"

        else -> ""
    }
    return "${birtday.substring(8, 10)} $_month ${birtday.substring(0, 4)}"
}