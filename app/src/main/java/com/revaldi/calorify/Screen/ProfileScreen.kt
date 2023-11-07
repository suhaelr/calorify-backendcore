package com.revaldi.calorify.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.revaldi.calorify.Data.UserViewModel
import com.revaldi.calorify.Helper.PreferenceManager
import com.revaldi.calorify.R

@Composable
fun ProfileScreen(viewModel: UserViewModel,navController: NavController) {
    val sharedPreferences = PreferenceManager(LocalContext.current)
    val username = sharedPreferences.getUsername()
    val totalBmr by remember { mutableStateOf(sharedPreferences.getBmr()) }
    val totalBmi by remember { mutableStateOf(sharedPreferences.getBmi()) }
    val activity by remember { mutableStateOf(sharedPreferences.getActivityLevel()) }
    val height by remember { mutableStateOf(sharedPreferences.getHeight()) }
    val weight by remember { mutableStateOf(sharedPreferences.getWeight()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(PaddingValues(24.dp, 0.dp, 24.dp, 0.dp) ),
    ) {

        Row( modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(PaddingValues(0.dp, 40.dp, 0.dp, 0.dp)),
            horizontalArrangement = Arrangement.Center,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Group 7",
                modifier = Modifier
                    .width(130.dp)
                    .height(30.dp)
                    .align(alignment = Alignment.Top))
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(PaddingValues(0.dp, 25.dp, 0.dp, 12.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_dummy),
                contentDescription = "Group 7",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = username.toString(),
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.height),
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = "${height} cm",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.weight),
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = "${weight} kg",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.activity),
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = "${activity}",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.bmr),
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = "${totalBmr} kcal",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Row(modifier = Modifier.padding(PaddingValues(0.dp,15.dp,0.dp,10.dp)), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.bmi),
                contentDescription = "Group 7",
                tint = Color(0xff6f7cfc) ,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

            )
            Text(
                text = "${totalBmi}",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 30.dp)
            )

        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.Black)
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.logoutNow(navController)
            },
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors( Color.Red),
            modifier = Modifier.width(300.dp).height(40.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Logout",
                color = Color.White,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Black)
            )
        }

    }
}

