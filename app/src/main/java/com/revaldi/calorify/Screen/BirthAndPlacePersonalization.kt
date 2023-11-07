package com.revaldi.calorify.Screen

import android.app.Application

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.platform.LocalContext
import com.revaldi.calorify.Data.UserViewModel
import com.revaldi.calorify.Navigation.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

import java.time.format.DateTimeFormatter
import java.util.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun BirthAndPlacePersonalization(navController: NavHostController,viewModel: UserViewModel){

    var selectedDate by remember { mutableStateOf("") }

    var country by remember { mutableStateOf("") }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
            .background(color = Color.White),

    ) {
        Spacer(modifier = Modifier.height(77.dp))
        Text(
            text = "When were you born?",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "We will calculate your calorie needs",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.width(298.dp)
        )
        Spacer(modifier = Modifier.height(45.dp))


        showDatePickerDialog { date ->
            selectedDate = date
        }

        // Display selected date
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(71.dp))
        Text(
            text = "Where do you live?",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(21.dp))
        RoundedTextField(
            modifier = Modifier
                .width(width = 390.dp)
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = country,
            onValueChange = { newValue -> country = newValue},
            label = "Country",
            hint = "Indonesia"
        )
        Spacer(modifier = Modifier.height(220.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate(Screen.HeightWeightPersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff6e7bfb)),
                modifier = Modifier
                    .width(144.dp)
                    .height(54.dp)

            ) {
                Text(
                    text = "Back",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Black)
                )
            }

            Button(
                onClick = {
                    viewModel.birthday.value = selectedDate
                    viewModel.nation.value = country
                    navController.navigate(Screen.ActivityPersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff6f7cfc)),
                modifier = Modifier
                    .width(144.dp)
                    .height(54.dp)
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

@Composable
fun showDatePickerDialog(onDateSelected: (String) -> Unit) {
    val dialogState = rememberMaterialDialogState()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    var myDate by remember { mutableStateOf("") }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            val formattedDate = dateFormatter.format(date)
            onDateSelected(formattedDate)
            myDate = formattedDate
        }
    }

    Button(
        modifier = Modifier.width(340.dp).height(75.dp).border(0.5.dp, Color.Black, RoundedCornerShape(10.dp)),
        onClick = {
            dialogState.show()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
    ) {
        Text(if(myDate == "") "Select Date" else myDate, fontSize = 20.sp, textAlign = TextAlign.Center)
    }


}





@Preview
@Composable
fun BirthAndPlacePersonalizationPreview() {
    val context = LocalContext.current
    val viewModel = UserViewModel(application = context.applicationContext as Application)

    BirthAndPlacePersonalization(navController = rememberNavController(), viewModel = viewModel)
}




