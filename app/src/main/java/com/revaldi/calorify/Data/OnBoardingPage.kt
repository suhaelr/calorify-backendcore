package com.revaldi.calorify.Data

import androidx.annotation.DrawableRes
import com.revaldi.calorify.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.img1,
        title = "Track your calorie intake with just a click!",
        description = "Watch Your Waistline with Calorify's Camera Detection Technology."
    )

    object Second : OnBoardingPage(
        image = R.drawable.img2,
        title = "Take Control of Your Calorie with Calorify",
        description = "Report of your calorie will be given at the end of the week."
    )

    object Third : OnBoardingPage(
        image = R.drawable.img3,
        title = "Stay Healthy!",
        description = "Healthty life with various tips & trick from trusted article."
    )
}
