package com.revaldi.calorify.Screen

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revaldi.calorify.Data.UserViewModel

import com.revaldi.calorify.R

@Composable
fun Register(navController: NavHostController,viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(width = 390.dp)
                .padding(start = 24.dp,
                    end = 24.dp,
                    top = 105.dp,
                    bottom = 41.dp)
                .fillMaxHeight(),

            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Group 7",
                colorFilter = ColorFilter.tint(Color(0xff6e7bfb)),
                modifier = Modifier
                    .width(width = 146.dp)
                    .height(height = 33.dp))
            Spacer(
                modifier = Modifier
                    .height(height = 52.dp))
            Text(text = "Already have an account?",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp))
            Text(text = "Sign In and start tracking your calories",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp))
            RoundedTextField(
                hint = "Revaldiaye@gmail.com",
                label = "Email",
                value = email,
                onValueChange = { newValue -> email = newValue})

            PasswordRoundedTextField(
                hint = "Password",
                label = "Password",
                value = password,
                onValueChange = {newValue-> password = newValue}
            )
            PasswordRoundedTextField(
                hint = "Password",
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = {newValue-> confirmPassword = newValue }
            )
            Spacer(
                modifier = Modifier
                    .height(height = 16.dp))
            RegisterButton(
                onClick = {
                    Log.e("Register", email)
                    Log.e("Register", password)
                    Log.e("Register", confirmPassword)
                    viewModel.registerNow(email,password,confirmPassword,navController)
                }
            )

        }
    }



}

@Composable
fun RegisterButton(
    onClick: () -> Unit
) {
    Button(
        onClick =  onClick ,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(Color(0xff7884fc)),
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp)
            .padding(horizontal = 24.dp,
                vertical = 8.dp)
    ) {
        Text(
            text = "Register",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp)
        )
    }
}

//make a new composable function for the text field with hint



@Preview
@Composable
fun RegisterPreview() {
    Register(navController = NavHostController(LocalContext.current),viewModel = UserViewModel(
        Application()
    ))
}
