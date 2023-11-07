package com.revaldi.calorify.Data

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.revaldi.calorify.Helper.PreferenceManager
import com.revaldi.calorify.Navigation.Screen
import com.revaldi.calorify.Network.*
import com.revaldi.calorify.Screen.BotNav
import com.revaldi.calorify.Utils.deleteFile
import com.revaldi.calorify.Utils.reduceFileImage
import com.revaldi.calorify.Utils.uriToFile
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class UserViewModel(application: Application) : AndroidViewModel(application) {
    val username = mutableStateOf("")
    val nation = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")
    val birthday = mutableStateOf("")
    val gender = mutableStateOf("")
    val height = mutableStateOf(0)
    val weight = mutableStateOf(0)
    val activity = mutableStateOf("")
    val age = mutableStateOf(0)
//    private val _myCal =   MutableLiveData(0.0)
//    val myCal:LiveData<Double> = _myCal
    val myCal = mutableStateOf(0.0)
    val foodDetected =   mutableStateOf("")
    var status =  mutableStateOf(0)
    private var preferenceManager = PreferenceManager(application)



    fun registerNow(email: String, password: String, confirmPassword: String, navController: NavController){
        val newUser= NewUser(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )
        RetrofitClient.api.registerUser(newUser).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()
                    initialLogin(email, password)
                    navController.navigate(Screen.UsernamePersonalization.route)
                } else {
                    Toast.makeText(navController.context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.e("Register", "Error: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(navController.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Register", "Error: ${t.message}")
            }
        })
    }
    fun loginNow(email: String, password: String, navController: NavController){
        val LoginUser= LoginUser(
            email = email,
            password = password,
        )

        RetrofitClient.api.loginUser(LoginUser).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()
                    Log.e("Login", "Success: ${response.body()}")
                    Log.e("Login", "Success: ${response.body()!!.user_id}")
                    preferenceManager.saveLoginData(response.body()!!.user_id)
                    CoroutineScope(Dispatchers.IO).launch {
                        getBmiBmrData(response.body()!!.user_id)
                        delay(1000)
                        saveUserDataAll(response.body()!!.user_id)
                        withContext(Dispatchers.Main) {
                            delay(1000)
                            navController.navigate(BotNav.Homepage.route)
                        }
                    }

                } else {
                    Toast.makeText(navController.context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.e("Login", "Error: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(navController.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Login", "Error: ${t.message}")
            }
        })
    }
    fun initialLogin(email: String, password: String){
        val LoginUser= LoginUser(
            email = email,
            password = password,
        )

        RetrofitClient.api.loginUser(LoginUser).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Log.e("Login", "Success: ${response.body()}")
                    Log.e("Login", "Success: ${response.body()!!.user_id}")
                    preferenceManager.saveLoginData(response.body()!!.user_id)
                } else {
                    Log.e("Login", "Error: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Login", "Error: ${t.message}")
            }
        })
    }
    fun saveUserDataAll(userId: String){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getUserData(userId)
                if (response.userId == preferenceManager.getUserId()){
                    preferenceManager.saveUserData(response)
                    status.value = 200
                    Log.e("Get User Data", "Success: ${response.username}")
                    Log.e("Get User Data", "Success: ${response.bmr}")
                    Log.e("Get User Data", "Success: ${status.value}")
                    Log.e("Login", "Success: ${preferenceManager.getBmr()}")
                    Log.e("Login", "Success: ${preferenceManager.getBmi()}")
                }
            } catch (e: Exception) {
                Log.e("Get User Data", "Error: ${e.message}")
            }
        }
    }
    fun getBmiBmrData(userId:String){

        RetrofitClient.api.getBmi(userId).enqueue(object : Callback<Bmi> {
            override fun onResponse(call: Call<Bmi>, response: Response<Bmi>) {
                if (response.isSuccessful) {
                    Toast.makeText(getApplication(), "Success", Toast.LENGTH_SHORT).show()
                    Log.e("Get BMI", "Success: ${response.body()}")
                } else {
                    Toast.makeText(getApplication(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.e("Get BMI", "Error: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<Bmi>, t: Throwable) {
                Toast.makeText(getApplication(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Get BMI", "Error: ${t.message}")
            }
        })

        RetrofitClient.api.getBmr(userId).enqueue(object : Callback<Bmr> {
            override fun onResponse(call: Call<Bmr>, response: Response<Bmr>) {
                if (response.isSuccessful) {
                    Toast.makeText(getApplication(), "Success", Toast.LENGTH_SHORT).show()
                    Log.e("Get BMR", "Success: ${response.body()}")
                } else {
                    Toast.makeText(getApplication(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.e("Get BMR", "Error: ${response.body()}")
                }
            }
            override fun onFailure(call: Call<Bmr>, t: Throwable) {
                Toast.makeText(getApplication(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Get BMR", "Error: ${t.message}")
            }

        })
    }
    fun addUserData(navController: NavController) {
        val userData = UserData(
            username = username.value,
            nation = nation.value,
            birthday = birthday.value,
            gender = gender.value,
            height = height.value,
            weight = weight.value,
            activitylevel = activity.value
        )
        val userId = "${preferenceManager.getUserId()}"
        RetrofitClient.api.addUserData(userId,userData).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(500)
                        getBmiBmrData(userId)
                        delay(1000)
                        saveUserDataAll(userId)
                        withContext(Dispatchers.Main) {
                            delay(1000)
                            navController.navigate(Screen.Greeting.route)
                        }
                    }

                } else {
                    Toast.makeText(navController.context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.e("Add User Data", "Error: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(navController.context, "ErrorAdd: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Add User Data", "Error: ${t.message}")
            }
        })
    }
    fun addFoodDetected(query:FoodDetect,navController: NavController){
        RetrofitClient.api.addNutrition(query).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.e("Food Detect", "Berhasil: ${response.body()}")
                    getCaloriesNow(navController)

                } else {
                    Log.e("Food Detect", "Error: ${response.body()}")
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Food Detect", "Failure: ${t.message}")
            }
        })
    }
    fun getCaloriesNow(navController: NavController){
        RetrofitClient.api.getTotalCal().enqueue(object : Callback<TotalCaloriesResponse> {
            override fun onResponse(call: Call<TotalCaloriesResponse>, response: Response<TotalCaloriesResponse>) {
                if (response.isSuccessful) {
                    val total = response.body()!!.totalCaloriesData.totalCalories
                    preferenceManager.saveMyCal(total.toInt())
                    Log.e("get cal", "Berhasil: ${response.body()}")
                    Log.e("get cal", "Berhasil: ${preferenceManager.getMyCal()}")
                    navController.navigate(BotNav.Homepage.route)
                } else {
                    Log.e("get cal", "Error: ${response.body()}")
                }
            }
            override fun onFailure(call: Call<TotalCaloriesResponse>, t: Throwable) {
                Log.e("get cal", "Error: ${t.message}")
            }
        })
    }
    fun uploadImage(imageUri: Uri, context: Context, navController: NavController) {
        val preferenceManager = PreferenceManager(context)
        val file0 = uriToFile(imageUri, context)
        val file = reduceFileImage(file0 as File)
        val requestBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)
        RetrofitClient.apiModel.predictImage(multipartBody).enqueue(object : Callback<PredictResponse> {
            override fun onResponse(call: Call<PredictResponse>, response: Response<PredictResponse>) {
                if (response.isSuccessful) {
                    preferenceManager.saveFoodDetected(response.body()!!.result)
                    Log.e("Predict", "Berhasil: ${response.body()!!.result}")
                    Toast.makeText(context, "Success Predict Food", Toast.LENGTH_SHORT).show()
                    val food = preferenceManager.getFoodDetected()
                    navController.navigate(Screen.Result(food.toString()).route)
                    deleteFile(file)
                } else {
                    Log.e("Predict", "Error: ${response.body()}")
                    Toast.makeText(context, "Error Predict Food", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                Log.e("Predict", "Error: ${t.message}")
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun logoutNow(navController: NavController){
        RetrofitClient.api.logout().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.e("Logout", "Berhasil: ${response.body()}")
                    navController.navigate(Screen.Login.route)
                    preferenceManager.deleteData()
                } else {
                    Log.e("Logout", "Error: ${response.body()}")
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Logout", "Error: ${t.message}")
            }
        })
    }


}


