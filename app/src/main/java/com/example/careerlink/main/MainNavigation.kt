package com.example.careerlink.main

import EditSertifikasiScreen
import ListMyPostSertifikasiScreen
import TambahSertifikasiScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.frontend.home.HomeScreen
import com.example.careerlink.frontend.login.screen.LoginScreen
import com.example.careerlink.frontend.loker.EditLokerScreen
import com.example.careerlink.frontend.loker.ListLokerScreen
import com.example.careerlink.frontend.loker.ListLowonganMyPostScreen
import com.example.careerlink.frontend.loker.TambahLokerScreen
import com.example.careerlink.frontend.magang.DetailMagangScreen
import com.example.careerlink.frontend.magang.EditMagangScreen
import com.example.careerlink.frontend.magang.ListMagangScreen
import com.example.careerlink.frontend.magang.ListPostMagangSayaScreen
import com.example.careerlink.frontend.magang.TambahMagangScreen
import com.example.careerlink.frontend.profile.ChangePasswordScreen
import com.example.careerlink.frontend.profile.EditProfileScreen
import com.example.careerlink.frontend.profile.ProfileScreen
import com.example.careerlink.frontend.register.screen.RegisterScreen
import com.example.careerlink.frontend.sertifikasi.DetailSertifikasiScreen
import com.example.careerlink.frontend.sertifikasi.ListSertifikasiScreen

@Composable
fun MainNavigation(tokenDataStore: TokenDataStore) {
    val token by tokenDataStore.accessToken.collectAsState(initial = null)
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (token.isNullOrBlank()) "login" else "home"
    ) {
//        Authentication
        composable("login") {
//            LoginScreen(navController = navController)
            TambahMagangScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }

//        Home
        composable("home") {
            HomeScreen(navController = navController)
        }

//        Loker
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
            val lokerId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (lokerId != null) {
                EditLokerScreen(lokerId = lokerId, navController = navController)
            } else {
                // Handle error, e.g., show a Toast or navigate back
                println("Error: Invalid loker ID")
            }
        }

//        Magang
        composable("list-magang") {
            ListMagangScreen(navController = navController)
        }
        composable("list-magang-my-post") {
            ListPostMagangSayaScreen(navController = navController)
        }
        composable("detail-magang/{id}") { backStackEntry ->
            val magangId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (magangId != null) {
                DetailMagangScreen (magangId = magangId, navController = navController)
            } else {
                println("Error: Invalid loker ID")
            }
        }
        composable("add-magang") {
            TambahMagangScreen(navController = navController)
        }
        composable("edit-magang/{id}") { backStackEntry ->
            val magangId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (magangId != null) {
                EditMagangScreen(magangId = magangId, navController = navController)
            } else {
                println("Error: Invalid loker ID")
            }
        }

//        Sertifikasi
        composable("list-sertifikasi") {
            ListSertifikasiScreen(navController = navController)
        }
        composable("list-sertifikasi-my-post") {
            ListMyPostSertifikasiScreen(navController = navController)
        }
        composable("detail-sertifikasi/{id}") { backStackEntry ->
            val sertifikasiId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (sertifikasiId != null) {
                DetailSertifikasiScreen(sertifikasiId = sertifikasiId, navController = navController)
            } else {
                println("Error: Invalid loker ID")
            }
        }
        composable("add-sertfikasi") {
            TambahSertifikasiScreen(navController = navController)
        }
        composable("edit-sertifikasi/{id}") { backStackEntry ->
            val sertifikasiId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (sertifikasiId != null) {
                EditSertifikasiScreen(sertifikasiId = sertifikasiId, navController = navController)
            } else {
                println("Error: Invalid loker ID")
            }
        }

//        Profile
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("change-password") {
            ChangePasswordScreen(navController = navController)
        }
        composable("edit-profile") {
            EditProfileScreen(navController = navController)
        }

    }
}
