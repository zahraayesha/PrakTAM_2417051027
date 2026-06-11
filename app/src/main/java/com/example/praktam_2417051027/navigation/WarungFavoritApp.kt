package com.example.praktam_2417051027.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051027.model.Makanan
import com.example.praktam_2417051027.model.MakananRepository
import com.example.praktam_2417051027.model.UserAccount
import com.example.praktam_2417051027.ui.screens.DetailMakananScreen
import com.example.praktam_2417051027.ui.screens.HomeScreen
import com.example.praktam_2417051027.ui.screens.LoginScreen
import com.example.praktam_2417051027.ui.screens.ProfileScreen
import com.example.praktam_2417051027.ui.screens.RegisterScreen
import kotlinx.coroutines.launch

private const val MAKANAN_API_URL =
    "https://gist.githubusercontent.com/zahraayesha/53181eb693c0435344214c52d65f3c6c/raw/9461cefddd031bdd9a02301a0a65a54473a30622/menu_makanan.json"

@Composable
fun WarungFavoritApp() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    var akunTerdaftar by remember {
        mutableStateOf(
            UserAccount(
                nama = "Rara",
                email = "rara@mail.com",
                password = "123456"
            )
        )
    }

    var userLogin by remember { mutableStateOf<UserAccount?>(null) }
    var daftarMakanan by remember { mutableStateOf<List<Makanan>>(emptyList()) }
    var isLoadingData by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    suspend fun loadMakanan() {
        isLoadingData = true

        try {
            daftarMakanan = MakananRepository.getMakanan(MAKANAN_API_URL)
            errorMessage = null
        } catch (e: Exception) {
            daftarMakanan = MakananRepository.getFallbackMakanan()
            errorMessage = "Data dari server gagal dimuat. Menampilkan data cadangan."
        }

        isLoadingData = false
    }

    LaunchedEffect(Unit) {
        loadMakanan()
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                akunTerdaftar = akunTerdaftar,
                onLoginSuccess = { user ->
                    userLogin = user
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { akunBaru ->
                    akunTerdaftar = akunBaru
                    userLogin = akunBaru
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                user = userLogin,
                daftarMakanan = daftarMakanan,
                isLoadingData = isLoadingData,
                errorMessage = errorMessage,
                onRefresh = {
                    scope.launch {
                        loadMakanan()
                    }
                }
            )
        }

        composable("detail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0

            DetailMakananScreen(
                index = index,
                daftarMakanan = daftarMakanan,
                navController = navController
            )
        }

        composable("profile") {
            ProfileScreen(
                user = userLogin,
                totalMenu = daftarMakanan.size,
                onBackHome = {
                    navController.popBackStack()
                },
                onLogout = {
                    userLogin = null
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}