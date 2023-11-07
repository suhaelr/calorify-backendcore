package com.revaldi.calorify.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.revaldi.calorify.Data.Article
import com.revaldi.calorify.Navigation.Screen
import com.revaldi.calorify.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun NewsScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var newsState by remember { mutableStateOf<List<Article>?>(null) }
    var selectedArticle by remember { mutableStateOf<Article?>(null) }

            LaunchedEffect(true) {
                coroutineScope.launch {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitClient.apiNews.getHealthHeadlines()
                    }
                    newsState = response.articles
                }
            }
            Column {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.padding(24.dp).clickable { navController.navigate(BotNav.Homepage.route) })
                    Text(
                        text = "Articles",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )

                }
                if (newsState != null) {
                    LazyColumn {
                        items(newsState!!) { article ->
                            NewsListItem(article) {
                                selectedArticle = article
                                navController.currentBackStackEntry?.savedStateHandle?.set("article", selectedArticle)
                                navController.navigate(Screen.DetailNews.route)
                            }
                        }
                    }
                } else {
                    Text(text = "Loading...", modifier = Modifier.padding(16.dp))
                }
            }
}

@Composable
fun NewsListItem(article: Article, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(24.dp, 8.dp, 24.dp, 8.dp)
            .clickable(onClick = onItemClick)
            ,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White

    ) {
        Column() {
            Image(
                painter = rememberImagePainter(article.urlToImage),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(200.dp),

            )

            Text(
                modifier = Modifier.padding(14.dp),
                text = article.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )


        }

    }
}

@Preview
@Composable
fun NewsListItemPreview() {
    NewsScreen(navController = NavController(LocalContext.current))
}