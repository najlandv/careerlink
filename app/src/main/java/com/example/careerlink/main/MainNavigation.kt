package com.example.careerlink.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.frontend.login.screen.LoginScreen
import com.example.careerlink.frontend.loker.EditLokerScreen
import com.example.careerlink.frontend.loker.ListLokerScreen
import com.example.careerlink.frontend.loker.ListLowonganMyPostScreen
import com.example.careerlink.frontend.loker.TambahLokerScreen
import com.example.careerlink.frontend.register.screen.RegisterScreen

@Composable
fun MainNavigation(tokenDataStore: TokenDataStore) {
    val token by tokenDataStore.accessToken.collectAsState(initial = null)
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (token.isNullOrBlank()) "login" else "list-loker-my-post"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("list-loker") {
            ListLokerScreen()
        }
        composable("list-loker-my-post") {
            ListLowonganMyPostScreen(navController = navController)
        }
        composable("add-loker") {
            TambahLokerScreen(navController = navController)
        }
        composable("edit-loker/{id}") { backStackEntry ->
            val lokerId = backStackEntry.arguments?.getInt("id")
            if (lokerId != null) {
                EditLokerScreen(lokerId = lokerId)
            }
        }

//        composable("list-magang-my-post") {
//            ListPostMagangSayaScreen()
//        }
    }
}
