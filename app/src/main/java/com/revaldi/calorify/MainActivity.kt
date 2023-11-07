package com.revaldi.calorify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.revaldi.calorify.Navigation.SetupNavGraph
import com.revaldi.calorify.Network.RetrofitClient
import com.revaldi.calorify.ui.Theme.Calorify

@ExperimentalAnimationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calorify {

                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
