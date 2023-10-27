package com.example.androidsocketclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidsocketclientapp.presentation.chat.ChatScreen
import com.example.androidsocketclientapp.presentation.login.LoginScreen
import com.example.androidsocketclientapp.ui.theme.AndroidSocketClientAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSocketClientAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController =navController, startDestination = "login_screen") {
                        composable("login_screen") {
                            LoginScreen(onNavigate = navController::navigate)
                        }
                     composable(
                         route = "chat_screen/{userName}",
                         arguments = listOf(
                             navArgument(name = "useName"){
                                 type = NavType.StringType
                                 nullable = true
                             }
                         )
                     )   {
                         val userName = it.arguments?.getString("userName")
                         ChatScreen(userName = userName)
                     }
                    }
                }
            }
        }
    }
}
