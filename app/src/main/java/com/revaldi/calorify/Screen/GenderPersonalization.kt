package com.revaldi.calorify.Screen
import android.app.Application
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revaldi.calorify.Navigation.Screen
import com.revaldi.calorify.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revaldi.calorify.Data.UserViewModel

object gender {
    const val male = "male"
    const val female = "female"
}

@Composable
fun GenderPersonalization(navController: NavHostController,viewModel: UserViewModel) {

    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What is your gender?",
            color = Color(0xFF3B3B3B),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            ),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "For better experience, we need to know your gender",
            color = Color(0xFF3B3B3B),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp
            ),
            modifier = Modifier.width(298.dp)
        )
        Spacer(modifier = Modifier.height(68.dp))
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomRadioButton(
                text = "Male",
                isSelected = selectedOption == gender.male,
                id = R.drawable.male,
                onSelected = { selectedOption = gender.male
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Female",
                isSelected = selectedOption == gender.female,
                id = R.drawable.female,
                onSelected = { selectedOption = gender.female }
            )
        }
        Spacer(modifier = Modifier.height(131.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {  navController.navigate(Screen.UsernamePersonalization.route) },
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
                    viewModel.gender.value=selectedOption
                    navController.navigate(Screen.HeightWeightPersonalization.route) },
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
fun CustomRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    id: Int,
    onSelected: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onSelected() }
            .border( if (isSelected) 2.dp else 0.dp, Color(0xFF6E7BFB), RoundedCornerShape(15.dp))
            .height(400.dp)
            .width(150.dp)
            .fillMaxSize(),
        backgroundColor = Color.White,
        elevation = if (isSelected) 4.dp else 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id),
                contentDescription = "Radio Button",
                colorFilter = if (isSelected) ColorFilter.tint(Color(0xFF6E7BFB)) else ColorFilter.tint(Color(0xFFC5CAFD)),
                modifier = Modifier.size(240.dp)
            )
            Text(
                text = text,
                color =  Color(0xFF3B3B3B),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            )
        }
    }
}

@Preview
@Composable
fun GetGenderPreview() {
    GenderPersonalization(navController = NavHostController(LocalContext.current),viewModel = UserViewModel(
        Application()
    ))
}

