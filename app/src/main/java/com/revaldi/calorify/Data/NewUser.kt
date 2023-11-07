package com.revaldi.calorify.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewUser(
    val email : String,
    val password : String,
    val confirmPassword : String,
) : Parcelable
