package com.revaldi.calorify.Data

import com.google.gson.annotations.SerializedName

data class FoodDetect(
    @SerializedName("query") var query : String? = null,
)
