package com.revaldi.calorify.Data

import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("email"           ) var email           : String? = null,
                   @SerializedName("password"        ) var password        : String? = null,
                   )