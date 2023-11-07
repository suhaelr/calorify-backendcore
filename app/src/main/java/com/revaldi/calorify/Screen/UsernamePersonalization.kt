package com.revaldi.calorify.Screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revaldi.calorify.Navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.revaldi.calorify.Data.UserViewModel

@Composable
fun UsernamePersonalization(navController: NavHostController,viewModel: UserViewModel) {
    var first by remember { mutableStateOf("") }
    var last by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
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

        ) {
            Text(
                text = "What is your name?",
                color = Color(0xFF3B3B3B),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            RoundedTextField(
                hint = "Write your first name",
                label = "First name",
                value = first,
                onValueChange = { newValue -> first = newValue}
            )
            RoundedTextField(
                hint = "Write your last name",
                label = "Last name",
                value = last,
                onValueChange = { newValue -> last = newValue}
            )
            Spacer(
                modifier = Modifier
                    .height(height = 340.dp))

            Button(
                onClick = {
                    fullname = first+" "+last
                    viewModel.username.value = fullname
                    navController.navigate(Screen.GenderPersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors( Color(0xff6f7cfc)),
                modifier = Modifier.width(144.dp).height(54.dp).align(Alignment.End)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Black)
                )
            }

        }

    }
}
@Preview
@Composable
fun PreviewUsernamePersonalization(){
    UsernamePersonalization(navController = NavHostController(LocalContext.current),viewModel = UserViewModel(
        Application()
    ))
}

//make a new composable function for the text field with hint






