package com.revaldi.calorify.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodHistory(
    val foodName: String,
    val foodCalories: Double,
    val foodServingSize: Int,
    val foodDate: String
) : Parcelable