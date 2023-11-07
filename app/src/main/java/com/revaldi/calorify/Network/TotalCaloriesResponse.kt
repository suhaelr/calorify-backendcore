package com.revaldi.calorify.Network
import com.google.gson.annotations.SerializedName


data class TotalCaloriesResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalCaloriesData")
    val totalCaloriesData: TotalCaloriesData
)

data class TotalCaloriesData(
    @SerializedName("calories_id")
    val caloriesId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("total_calories")
    val totalCalories: Double
)