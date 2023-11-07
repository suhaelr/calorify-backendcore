package com.revaldi.calorify.Screen

import android.app.Application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

import com.revaldi.calorify.Data.FoodDetect
import com.revaldi.calorify.Data.UserViewModel


@Composable
fun ResultPicture( food:String,viewModel: UserViewModel,navController: NavController) {
    var serving by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 24.dp, end = 24.dp, top = 20.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Predicted food ",
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = food,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        RoundedTextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            value = serving,
            onValueChange = { newValue -> serving = newValue.filter { it.isDigit() }},
            label = "Serving Size",
            hint = "grams"
        )
        Text(
            text = "Serving size is the amount of food that you eat at one time. It is listed on food labels in grams .",
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(320.dp))
        Button(
            onClick = {
                      val foodServ = FoodDetect(query = "${serving}g ${food}")
                    viewModel.addFoodDetected(foodServ,navController)
            },
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors( Color(0xff6f7cfc)),
            modifier = Modifier.width(300.dp).height(70.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Save",
                color = Color.White,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Black)
            )
        }


    }
}


@Preview
@Composable
fun PreviewResultPicture(){
    ResultPicture("Waffles", viewModel = UserViewModel(Application())  ,NavController(LocalContext.current))
}