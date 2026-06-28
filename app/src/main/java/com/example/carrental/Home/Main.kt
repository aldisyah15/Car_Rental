package com.example.carrental.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carrental.R
import com.example.carrental.Profile.ProfilePage
import com.example.carrental.Schedule.SchedulePage
import com.example.carrental.favorite.FavoritePage
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

@Composable
fun HomePage(navController: NavHostController) {

    Scaffold(
    ) { innerPading ->
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            MainHome(innerPaddingValues = innerPading, navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainHome(innerPaddingValues: PaddingValues, navControllerr: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, innerPaddingValues = innerPaddingValues)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = "Home"

            ) {
                composable("Home") {
                    HomeNav(innerPadding = innerPaddingValues, navControllerr)
                }

                composable("Profile") {
                    ProfilePage()
                }

                composable("Schedule") {
                    SchedulePage()
                }

                composable("Favorite") {
                    FavoritePage()
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController, innerPaddingValues: PaddingValues) {
    var IsActiv by remember { mutableStateOf("Home") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = innerPaddingValues.calculateBottomPadding())
            .background(color = Color.White)
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconNav(Onclick = {
                IsActiv = "Home"
                navController.navigate("Home")
            }, Img = R.drawable.home_01, Text = "Home", IsActiv = IsActiv == "Home")
            IconNav(
                Onclick = {
                    IsActiv = "Favorite"
                    navController.navigate("Favorite")
                },
                Img = R.drawable.heart,
                Text = "Favorite",
                IsActiv = IsActiv == "Favorite"
            )
            IconNav(
                Onclick = {
                    IsActiv = "Schedule"
                    navController.navigate("Schedule")
                },
                Img = R.drawable.calendar_minus,
                Text = "Schedule",
                IsActiv = IsActiv == "Schedule"
            )
            IconNav(
                Onclick = {
                    IsActiv = "Profile"
                    navController.navigate("Profile")
                },
                Img = R.drawable.user_circle,
                Text = "Profile",
                IsActiv = IsActiv == "Profile"
            )
        }
    }
}

@Composable
fun IconNav(Onclick: () -> Unit = {}, Img: Int, Text: String, IsActiv: Boolean) {
    Button(
        onClick = Onclick,
        colors = ButtonDefaults.buttonColors(containerColor = if (IsActiv) Color.Blue else Color.Transparent),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            if (IsActiv) {
                Image(
                    painter = painterResource(Img),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    text = Text,
                    fontSize = FontSize.md,
                    fontFamily = PlusJakartaSansFont,
                    color = Color.White
                )
            } else {
                Image(
                    painter = painterResource(Img),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    HomePage(
        navController = {} as NavHostController
    )
}
