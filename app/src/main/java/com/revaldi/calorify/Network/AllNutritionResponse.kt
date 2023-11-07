package com.revaldi.calorify.Network
import com.google.gson.annotations.SerializedName


data class AllNutritionResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("foodFields")
    val foodFields: List<FoodField>,
    @SerializedName("status")
    val status: String
)

data class FoodField(
    @SerializedName("calories")
    val calories: Double,
    @SerializedName("date")
    val date: String,
    @SerializedName("food_id")
    val foodId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("servingSize")
    val servingSize: Int
)