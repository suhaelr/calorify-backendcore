package com.revaldi.calorify.Network
import com.google.gson.annotations.SerializedName


data class AllUserDataResponse(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("nation")
    val nation: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("activitylevel")
    val activitylevel: String,
    @SerializedName("bmi")
    val bmi: Bmi,
    @SerializedName("bmr")
    val bmr: Bmr,

)

data class Bmi(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("request_result")
    val requestResult: String,
    @SerializedName("status_code")
    val statusCode: Int
)

data class Bmr(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("request_result")
    val requestResult: String,
    @SerializedName("status_code")
    val statusCode: Int
)

data class Data(
    @SerializedName("bmi")
    val bmi: Double,
    @SerializedName("health")
    val health: String,
    @SerializedName("healthy_bmi_range")
    val healthyBmiRange: String
)

data class DataX(
    @SerializedName("BMR")
    val bmr: Double,
)



