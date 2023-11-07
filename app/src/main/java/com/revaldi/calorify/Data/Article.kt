package com.revaldi.calorify.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String,val title: String, val description: String, val url: String, val urlToImage: String, val publishedAt: String
) : Parcelable
