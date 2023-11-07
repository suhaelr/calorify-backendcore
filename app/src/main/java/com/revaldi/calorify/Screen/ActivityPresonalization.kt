package com.revaldi.calorify.Screen
import android.app.Application

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revaldi.calorify.Navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revaldi.calorify.Data.UserViewModel

@Composable
fun ActivityPersonalization(navController: NavHostController,viewModel: UserViewModel) {
    var selectedOption by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize().background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(height = 77.dp))
        Text(
            text = "Rate your activity",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(height = 20.dp))
        Text(
            text = "Excluding exercise",
            color = Color(0xff3b3b3b),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.width(width = 298.dp)
        )
        Spacer(modifier = Modifier.height(height = 61.dp))

        RadioButtonCard(
            isSelected = selectedOption == "level_1",
            onClick = { selectedOption = "level_1" },
            label = "Not Very Active",
            description = "Activities may include walking, light jogging, or recreational sports."
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        RadioButtonCard(
            isSelected = selectedOption == "level_2",
            onClick = { selectedOption = "level_2"},
            label = "Somewhat Active",
            description = "Activities may include brisk walking, running, swimming, cycling, or moderate-intensity workouts."
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        RadioButtonCard(
            isSelected = selectedOption == "level_3",
            onClick = { selectedOption = "level_3" },
            label = "Active",
            description = "Activities may include vigorous cardio workouts, weightlifting, high-intensity interval training (HIIT), or participating in competitive sports."
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        RadioButtonCard(
            isSelected = selectedOption == "level_4",
            onClick = { selectedOption = "level_4" },
            label = "Very Active",
            description = "They have a high activity level and may participate in multiple workouts or physical activities throughout the day."
        )

        Spacer(modifier = Modifier.height(height = 50.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {  navController.navigate(Screen.BirthAndPlacePersonalization.route) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff6e7bfb)),
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
                            viewModel.activity.value = selectedOption
                            viewModel.addUserData(navController)
                          },
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
fun RadioButtonCard(
    isSelected: Boolean,
    onClick: () -> Unit,
    label: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(width = 342.dp)
            .height(height = 95.dp)
            .background(if (isSelected) Color(0xff6f7cfc) else Color.White,
                shape = RoundedCornerShape(10.dp))
            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)

    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xff6371ff),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(
            text = description,
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp)
        )
    }
}

@Preview
@Composable
fun ActivityPersonalizationPreview() {
    ActivityPersonalization(navController = NavHostController(LocalContext.current),viewModel = UserViewModel(
        Application()
    ))
}