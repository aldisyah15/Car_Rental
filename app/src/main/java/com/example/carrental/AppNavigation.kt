package com.example.carrental

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carrental.Home.HomePage
import com.example.carrental.Login.Login
import com.example.carrental.Onboarding.Onboarding
import com.example.carrental.Register.Register

@Composable
fun AppNavigation() {

    val NavigationController = rememberNavController()

    NavHost(

        navController = NavigationController,

        startDestination = "OnBoarding"

    ) {

        composable("OnBoarding") {

            Onboarding(onFinish = {

                NavigationController.navigate("Login")

            })

        }



        composable("Login") {

            Login(

                home = {

                    NavigationController.navigate("Home")

                },

                registerNowPage = {

                    NavigationController.navigate("Register")

                }

            )

        }



        composable("Home") {

            HomePage(navController = NavigationController)

        }



        composable("Register") {

            Register(onLogin = {

                NavigationController.navigate("Login")

            }, navController = NavigationController)

        }

        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { data ->
            val idCar = data.arguments?.getString("id")
            CarDetailScreen(id = idCar, navController = NavigationController)
        }

    }


}

