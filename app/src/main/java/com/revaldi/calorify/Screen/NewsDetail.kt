package com.revaldi.calorify.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.revaldi.calorify.Data.Article
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun NewsDetail(article: Article,navController: NavHostController) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date: Date = format.parse(article.publishedAt)
    Column(modifier = Modifier.padding(24.dp,24.dp,24.dp,0.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.clickable { navController.navigate(BotNav.News.route) })
            Text(
                text = "Article",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )

        }
        Image(
            painter = rememberImagePainter(article.urlToImage),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 15.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(200.dp)

        )
        Card(modifier = Modifier.fillMaxSize(),shape = RoundedCornerShape(10.dp), backgroundColor = Color.White){
            Column(modifier = Modifier.padding(18.dp,30.dp,18.dp,30.dp)) {
                Text(text = date.toString(), style = MaterialTheme.typography.body1, color = Color.Black)
                Text(text = article.title, style = MaterialTheme.typography.h6,color = Color.Black, fontWeight = FontWeight.Bold)
                Text(text = "By ${article.author}", style = MaterialTheme.typography.subtitle1,color = Color.Black)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = article.description, style = MaterialTheme.typography.body1,color = Color.Black, textAlign = TextAlign.Justify)
            }

        }

    }
}