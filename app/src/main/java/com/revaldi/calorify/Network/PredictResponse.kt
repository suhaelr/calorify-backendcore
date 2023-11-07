package com.revaldi.calorify.Network

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @SerializedName("result")
    val result: String,
)