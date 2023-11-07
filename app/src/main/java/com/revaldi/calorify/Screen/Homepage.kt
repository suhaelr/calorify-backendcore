package com.revaldi.calorify.Screen


import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revaldi.calorify.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.revaldi.calorify.BuildConfig
import com.revaldi.calorify.Data.UserViewModel
import com.revaldi.calorify.Helper.PreferenceManager
import com.revaldi.calorify.Navigation.Screen


import java.io.*
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MyUI(navController: NavController) {
    val sharedPreferences = PreferenceManager(LocalContext.current)
    val username by remember { mutableStateOf(sharedPreferences.getUsername()) }
    val totalBmr by remember { mutableStateOf(sharedPreferences.getBmr())}
    val calories by remember { mutableStateOf(sharedPreferences.getMyCal()) }
    Log.e("MyUI", "username: $username")
    Log.e("MyUI", "totalBmr: $totalBmr")
    Log.e("MyUI", "calories: $calories")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row( modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(PaddingValues(0.dp, 40.dp, 0.dp, 0.dp)),
            horizontalArrangement = Arrangement.Center,

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Group 7",
                modifier = Modifier
                    .width(130.dp)
                    .height(30.dp)
                    .align(alignment = Alignment.Top))
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(PaddingValues(24.dp, 20.dp, 24.dp, 12.dp)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_dummy),
                contentDescription = "Group 7",
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clickable { navController.navigate(Screen.Profile.route) }
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(PaddingValues(16.dp, 0.dp, 0.dp, 0.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Hi, ${username}",
                    modifier = Modifier
                        .wrapContentSize(),
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(text = "Happy to see you!",
                    modifier = Modifier
                        .wrapContentSize(),
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 14.sp,
                )
            }
        }
        Text(
            text = "My Calories today",
            modifier = Modifier
                .wrapContentSize()
                .padding(PaddingValues(24.dp, 12.dp, 0.dp, 5.dp)),
            color = androidx.compose.ui.graphics.Color.Black,
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )
        Row(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            
                CustomComponent( canvasSize = 300.dp, indicatorValue = calories!!.toDouble(), maxIndicatorValue = totalBmr!!.toDouble())



            
        }
        Row( modifier =  Modifier
            .padding(PaddingValues(24.dp, 0.dp, 24.dp, 15.dp))
            .fillMaxWidth()
            .offset (y=(-40).dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
            Text(
                text = "History",
                modifier = Modifier
                    .wrapContentSize(),

                color = androidx.compose.ui.graphics.Color.Black,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
            Button(modifier = Modifier
                .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                .width(95.dp)
                .height(35.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = { navController.navigate(Screen.FoodHistory.route) }) {
                Text(
                    text = "See All",
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 14.sp,
                )
            }

        }
        Row(modifier = Modifier.offset (y=(-10).dp).align(Alignment.Start), horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.chart3),
                contentDescription = "Group 7",
                modifier = Modifier
                    .offset (y=(-30).dp)
                    .width(600.dp)
                    .height(300.dp)
                    .padding(PaddingValues(24.dp, 0.dp, 24.dp, 0.dp))
            )
        }

    }
}



@Composable
fun Homepage(navController: NavHostController,userViewModel: UserViewModel) {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
            userViewModel.uploadImage(capturedImageUri, context,navController)

        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                backgroundColor = Color(0xff6f7cfc),
                elevation = 22.dp,
            ) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                    }
                },
                contentColor = Color.White,
                backgroundColor = Color(0xff6f7cfc),
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MyUI(navController = navController)
        }
    }
}
@Composable
fun CustomComponent(
    canvasSize: Dp = 300.dp,
    indicatorValue: Double,
    maxIndicatorValue: Double,
    backgroundIndicatorColor: Color = Color.Gray.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = Color(0xff6e7bfb),
    foregroundIndicatorStrokeWidth: Float = 100f,
//    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h4.fontSize,
    bigTextColor: Color = Color.Black,
    bigTextSuffix: String = "/ $maxIndicatorValue",
    smallText: String = "My Calories",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = Color.Gray.copy(alpha = 0.3f)
) {
    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue.toInt(),
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue.toInt() == 0)
            MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
        else
            bigTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(
            bigText = receivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}







fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}









@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        backgroundColor = Color(0xff6f7cfc),
        elevation = 0.dp
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    item.icon?.let { icon ->
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                            tint = Color.White
                        )
                    }
                },
                label = {
                    item.title?.let { title ->
                        Text(
                            text = title,
                            color = Color.White
                        )
                    }
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

sealed class BotNav(val route: String, val title: String, val icon: ImageVector) {
    object Homepage : BotNav(route = "homepage_screen", "Home", Icons.Default.Home)
    object News : BotNav(route = "news_screen", "News", Icons.Default.List)

}
val items = listOf(
    BotNav.Homepage,
    BotNav.News
)




@Preview
@Composable
fun PreviewHomepage() {
    Homepage(navController = NavHostController(LocalContext.current), userViewModel = UserViewModel(
        Application()
    ))
}




