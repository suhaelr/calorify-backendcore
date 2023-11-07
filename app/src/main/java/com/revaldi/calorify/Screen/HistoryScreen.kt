package com.revaldi.calorify.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revaldi.calorify.Network.FoodField
import com.revaldi.calorify.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HistoryScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var newsState by remember { mutableStateOf<List<FoodField>?>(null) }

    LaunchedEffect(true) {
        coroutineScope.launch {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.api.getAllNutrition()
            }
            newsState = response.foodFields
        }
    }
    Column {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.padding(24.dp).clickable { navController.navigate(BotNav.Homepage.route) })
            Text(
                text = "History",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )

        }
        if (newsState != null) {
            LazyColumn {
                items(newsState!!) { food ->
                    HistoryItem(food)
                }
            }
        } else {
            Text(text = "Loading...", modifier = Modifier.padding(16.dp))
        }
    }
}
@Composable
fun HistoryItem(food : FoodField){
    Card(modifier = Modifier.fillMaxWidth().height(160.dp).padding(24.dp,10.dp,24.dp,20.dp),elevation = 8.dp, shape = RoundedCornerShape(10.dp), backgroundColor = Color.White) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column() {
                Text(text = food.name, fontSize = 26.sp, fontWeight = FontWeight.Bold )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = food.date, fontSize = 14.sp, fontWeight = FontWeight.Light )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${food.servingSize} grams", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "${food.calories} kcal", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold , maxLines = 1)
        }

    }

}
