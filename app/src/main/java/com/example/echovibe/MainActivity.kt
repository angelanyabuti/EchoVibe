package com.example.echovibe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.delay
import com.example.echovibe.ui.theme.EchoVibeTheme
import com.example.echovibe.auth.Registration // Import the Registration composable
import com.example.echovibe.routes.Routes
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.echovibe.auth.Login
import com.example.echovibe.pages.Home
import com.example.echovibe.ui.theme.Black1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Show XML splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EchoVibeTheme(darkTheme = false, dynamicColor = false) {
                var isSplashAnimationVisible by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(3000) // Show animation for 3 seconds
                    isSplashAnimationVisible = false
                }

                if (isSplashAnimationVisible) {
                    AnimatedSplashTransition()
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedSplashTransition() {
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(1000))
        scale.animateTo(1f, tween(1000, easing = FastOutSlowInEasing))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Just fade in the transition, no need for an extra logo
        Box(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha.value)
                .scale(scale.value)
        )
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { if (currentRoute != Routes.Login.route && currentRoute != Routes.Registration.route) {
            BottomAppBar(navController)
        } }
        ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Routes.Registration.route) {
                composable(Routes.Registration.route) {
                    Registration(navController = navController)
                }
                composable(Routes.Login.route) {  // Ensure this exists
                    Login(navController = navController)
                }
                composable(Routes.Home.route) {  // Ensure this exists
                    Home()
                }
            }
        }
    }
}
@Composable
fun BottomAppBar(navController: NavHostController){
    val selected = remember { //shows what is selected
        mutableStateOf(Icons.Default.Home)
    }
            BottomAppBar(
                modifier = Modifier
                    .height(70.dp),
                containerColor = MaterialTheme.colorScheme.onBackground, // Change to your desired color
                contentColor = MaterialTheme.colorScheme.onPrimary // Icon/Text color
            ) {
                IconButton(
                     //all icons are equally sized
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Routes.Home.route){
                            popUpTo(0) //avoid multiple back button clicks

                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ){
                        Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(30.dp),
                            tint = if (selected.value == Icons.Default.Home) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                        )

                }
                IconButton(
                    //all icons are equally sized
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Routes.Home.route){
                            popUpTo(0) //avoid multiple back button clicks

                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ){
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if (selected.value == Icons.Default.Search) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                    )

                }
                IconButton(
                    //all icons are equally sized
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Routes.Home.route){
                            popUpTo(0) //avoid multiple back button clicks

                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ){
                    Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if (selected.value == Icons.Default.Favorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                    )

                }
                IconButton(
                    //all icons are equally sized
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Routes.Home.route){
                            popUpTo(0) //avoid multiple back button clicks

                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ){
                    Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if (selected.value == Icons.Default.Person) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                    )

                }

            }

}
