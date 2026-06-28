package com.example.carrental.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carrental.Login.Logo
import com.example.carrental.R
import com.example.carrental.ui.components.AppButton
import com.example.carrental.ui.components.AppInput
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont
import com.example.carrental.viewModel.RegisterViewModel

@Composable
fun Register(onLogin: () -> Unit, navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                Logo()
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Register Your Account",
                    fontSize = FontSize.xl,
                    fontWeight = FontWeight.Bold,
                    fontFamily = PlusJakartaSansFont
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Enter your information below",
                    fontSize = FontSize.sm,
                    fontFamily = PlusJakartaSansFont,
                    color = Color(0xFF7B7B7B)
                )

                SectionInputRegister(navController = navController)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have an account? ",
                        color = Color(0xFF7B7B7B),
                        fontFamily = PlusJakartaSansFont
                    )
                    Text(
                        text = "Login",
                        color = Color.Blue,
                        fontFamily = PlusJakartaSansFont,
                        modifier = Modifier.clickable(onClick = onLogin)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionInputRegister(
    viewModel: RegisterViewModel = viewModel(),
    navController: NavController
) {
    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            navController.navigate("Home") {
                popUpTo("OnBoarding") { inclusive = true }
            }
        }
    }

    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var phone_numberValue by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }

    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        AppInput("Name", "Name", { nameValue = it }, nameValue, false)
        AppInput("Email Address", "Email Address", { emailValue = it }, emailValue, false)
        AppInput("Mobile Number", "Mobile Number", { phone_numberValue = it }, phone_numberValue, false)
        AppInput("Password", "Password", { password = it }, password, true)
        AppInput("Re Enter Password", "Re Enter Password", { rePassword = it }, rePassword, true)

        Spacer(Modifier.height(10.dp))
        AppButton(
            "Register",
            {
                if (password == rePassword) {
                    viewModel.register(nameValue, password, emailValue, phone_numberValue)
                } else {
                    isSheetOpen = true
                }
            },
            Modifier,
            Color.Blue,
            txColor = Color.White
        )

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = false },
                sheetState = sheetState,
                containerColor = Color.White
            ) {
                PasswordNotMatch()
            }
        }
    }
}

@Composable
fun PasswordNotMatch() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Password Tidak Cocok",
            color = Color.Red,
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.md,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterPreview() {
    Register(onLogin = {}, navController = rememberNavController())
}
