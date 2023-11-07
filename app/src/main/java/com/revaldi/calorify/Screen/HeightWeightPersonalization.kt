package com.revaldi.calorify.Screen
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revaldi.calorify.Navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.revaldi.calorify.Data.UserViewModel


@Composable
fun HeightWeightPersonalization(navController: NavHostController,viewModel:UserViewModel) {
    var heightText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(height = 77.dp))
        Text(
            text = "How tall are you?",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        )
        Spacer(modifier = Modifier.height(height = 20.dp))

        // Text Field for Height Input
        RoundedTextField(
            value = heightText,
            onValueChange = { heightText = it },
            label = "Height",
            hint = "cm",
            modifier = Modifier
                .width(width = 343.dp)
                .height(height = 70.dp))
        Spacer(modifier = Modifier.height(height = 77.dp))
        Text(
            text = "How weight are you?",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        )
        Spacer(modifier = Modifier.height(height = 20.dp))

        // Text Field for Weight Input
        RoundedTextField(
            value = weightText,
            onValueChange = { weightText = it },
            label = "Weight",
            hint = "kg",
            modifier = Modifier
                .width(width = 343.dp)
                .height(height = 70.dp))



        Spacer(modifier = Modifier.height(height = 265.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {  navController.navigate(Screen.GenderPersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors( Color(0xff6e7bfb)),
                modifier = Modifier.width(144.dp).height(54.dp)
            ) {
                Text(
                    text = "Back",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Black)
                )
            }

            Button(
                onClick = {
                    viewModel.height.value = heightText.toInt()
                    viewModel.weight.value = weightText.toInt()
                    navController.navigate(Screen.BirthAndPlacePersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors( Color(0xff6f7cfc)),
                modifier = Modifier.width(144.dp).height(54.dp)
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
fun RoundedTextField(
    hint: String,
    label:String,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Transparent

        ),
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(color = Color.Gray)
            )
        }
    )
}

@Preview
@Composable
fun HeightWeightPersonalizationPreview() {
    HeightWeightPersonalization(navController = NavHostController(LocalContext.current),viewModel = UserViewModel(
        Application()
    ))
}

