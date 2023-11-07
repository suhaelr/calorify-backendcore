package com.revaldi.calorify.Data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData (
val username : String,
val nation : String,
val birthday : String,
val gender: String,
val height: Int,
val weight:Int,
val activitylevel: String,
):Parcelable
