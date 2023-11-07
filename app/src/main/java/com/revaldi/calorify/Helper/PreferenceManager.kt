package com.revaldi.calorify.Helper

import android.content.Context
import com.revaldi.calorify.Data.UserData
import com.revaldi.calorify.Data.UserViewModel
import com.revaldi.calorify.Network.AllUserDataResponse

class PreferenceManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    fun saveUserData(user:AllUserDataResponse) {
        sharedPreferences.edit().putString("userId", user.userId).apply()
        sharedPreferences.edit().putString("email",user.email ).apply()
        sharedPreferences.edit().putString("username",user.username).apply()
        sharedPreferences.edit().putString("nation", user.nation).apply()
        sharedPreferences.edit().putInt("age",user.age ).apply()
        sharedPreferences.edit().putString("gender",user.gender ).apply()
        sharedPreferences.edit().putInt("height",user.height ).apply()
        sharedPreferences.edit().putInt("weight",user.weight ).apply()
        sharedPreferences.edit().putString("activitylevel",user.activitylevel ).apply()
        sharedPreferences.edit().putString("bmi", user.bmi.data.bmi.toString()).apply()
        sharedPreferences.edit().putString("bmi_health", user.bmi.data.health).apply()
        sharedPreferences.edit().putString("bmi_health_range", user.bmi.data.healthyBmiRange).apply()
        sharedPreferences.edit().putString("bmr", user.bmr.data.bmr.toString()).apply()
    }
    fun saveFoodDetected(food:String){
        sharedPreferences.edit().putString("food_detect", food).apply()
    }
    fun getFoodDetected(): String? {
        return sharedPreferences.getString("food_detect", null)
    }
    fun saveMyCal(cal:Int){
        sharedPreferences.edit().putInt("my_cal", cal).apply()
    }
    fun getMyCal(): Int? {
        return sharedPreferences.getInt("my_cal", 0)
    }
    fun getBmi(): String? {
        return sharedPreferences.getString("bmi", null)
    }
    fun getBmiHealth(): String? {
        return sharedPreferences.getString("bmi_health", null)
    }
    fun getBmiHealthRange(): String? {
        return sharedPreferences.getString("bmi_health_range", null)
    }
    fun getBmr(): String? {
        return sharedPreferences.getString("bmr", null)
    }

    fun saveLoginData(userId: String) {
        sharedPreferences.edit().putString("userId", userId).apply()
    }
    fun clearLoginData() {
        sharedPreferences.edit().remove("userId").apply()
    }

    fun isLoggedIn(): Boolean {
        return getUserId() != null
    }
    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }
    fun getNation(): String? {
        return sharedPreferences.getString("nation", null)
    }
    fun getBirthday(): String? {
        return sharedPreferences.getString("birthday", null)
    }
    fun getAge(): Int? {
        return sharedPreferences.getInt("age", 0)
    }
    fun getGender():String? {
        return sharedPreferences.getString("gender",null)
    }
    fun getHeight():Int? {
        return sharedPreferences.getInt("height",0)
    }
    fun getWeight():Int? {
        return sharedPreferences.getInt("weight",0)
    }
    fun getActivityLevel():String? {
        return sharedPreferences.getString("activitylevel",null)
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }
    fun initialOpen(){
        sharedPreferences.edit().putBoolean("initial_open", true).apply()
    }
    fun getInitialOpen(): Boolean? {
        return sharedPreferences.getBoolean("initial_open", false)
    }
    fun deleteData() {
        sharedPreferences.edit().clear().apply()
        sharedPreferences.edit().putBoolean("initial_open", true).apply()
    }
    fun factoryReset(){
        sharedPreferences.edit().clear().apply()
    }



}