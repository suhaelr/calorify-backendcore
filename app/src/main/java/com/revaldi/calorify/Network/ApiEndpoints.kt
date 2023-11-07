package com.revaldi.calorify.Network

import com.revaldi.calorify.Data.FoodDetect
import com.revaldi.calorify.Data.LoginUser
import com.revaldi.calorify.Data.NewUser
import com.revaldi.calorify.Data.UserData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoints {
    @POST("auth/registerdata")
    fun addUserData(
        @Header("Authorization") token: String,
        @Body request: UserData
    ): Call<ApiResponse>
    @POST("auth/login")
    fun loginUser(
        @Body request: LoginUser
    ): Call<LoginResponse>
    @POST("auth/register")
    fun registerUser(
        @Body request: NewUser
    ): Call<ApiResponse>
    @GET("api/bmibyid/{user_id}")
    fun getBmi(
        @Path("user_id") id: String
    ): Call<Bmi>
    @GET("api/bmrbyid/{user_id}")
    fun getBmr(
        @Path("user_id") id: String
    ): Call<Bmr>
    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: String
    ): AllUserDataResponse
    @POST("auth/logout")
    suspend fun logoutUser(
    ): ApiResponse
    @Multipart
    @POST("predict")
    fun predictImage(
        @Part image: MultipartBody.Part
    ): Call<PredictResponse>
    @POST("api/nutrition")
    fun addNutrition(
        @Body request: FoodDetect
    ): Call<ApiResponse>
    @GET("api/nutrition")
    suspend fun getAllNutrition(
    ): AllNutritionResponse
    @GET("api/nutrition/totalcal")
    fun getTotalCal(
    ): Call<TotalCaloriesResponse>
    @POST("auth/logout")
    fun logout(
    ): Call<ApiResponse>

    @GET("NewsAPI/top-headlines/category/health/in.json")
    suspend fun getHealthHeadlines(): NewsResponse
}