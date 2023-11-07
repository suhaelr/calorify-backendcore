package com.revaldi.calorify.Navigation



sealed class Screen(val route: String) {
    object Splash : Screen(route="splash_screen")
    object Onboarding : Screen(route = "onboarding_screen")
    object Register : Screen(route = "register_screen")
    object Login : Screen(route = "login_screen")
    object UsernamePersonalization : Screen(route = "username_personalization_screen")
    object Greeting : Screen(route = "greeting_screen")
    object GenderPersonalization : Screen(route = "gender_personalization_screen")
    object HeightWeightPersonalization : Screen(route = "heightweight_personalization_screen")

    object ActivityPersonalization : Screen(route = "activity_personalization_screen")
    object BirthAndPlacePersonalization : Screen(route = "birth_and_place_personalization_screen")

    object Home : Screen("home_screen")
    object DetailNews : Screen("detail_news_screen")
    object Camera : Screen("camera_screen")
    object Profile : Screen("profile_screen")
    object FoodHistory : Screen("food_history_screen")
    data class Result(val food:String) : Screen("result/${food}")

}