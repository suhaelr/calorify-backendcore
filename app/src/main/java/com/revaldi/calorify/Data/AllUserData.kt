package com.revaldi.calorify.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompleteUserData(
    val user : UserData,
    val bmi : BMI,
    val bmr : BMR
): Parcelable
@Parcelize
data class BMR(
    val data: DataX,
): Parcelable
@Parcelize
data class BMI(
    val data: Data,
): Parcelable
@Parcelize
data class Data(
    val bmi: Double,
    val health: String,
    val healthy_bmi_range: String
): Parcelable
@Parcelize
data class DataX(
    val BMR: Double
): Parcelable