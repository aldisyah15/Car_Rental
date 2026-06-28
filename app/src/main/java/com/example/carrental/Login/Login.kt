package com.example.carrental.Login

import android.widget.CheckBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrental.R
import com.example.carrental.ui.components.AppButton
import com.example.carrental.ui.components.AppButtonLogin
import com.example.carrental.ui.components.AppInput
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

@Composable
fun Login(home: () -> Unit, registerNowPage : () -> Unit) {
    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                Logo()
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Let’s Get You Login!",
                    fontSize = FontSize.xl,
                    fontFamily = PlusJakartaSansFont,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(7.dp))
                Text(
                    text = "Enter your information below",
                    fontSize = FontSize.sm,
                    fontFamily = PlusJakartaSansFont,
                    color = Color(0xFF7B7B7B)
                )

                Spacer(Modifier.height(10.dp))

                SectionInputAndLogin(home = home)
                SectionRegisterNow(registerNowPage = registerNowPage)

            }
        }
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.iconlogo),
        contentDescription = null,
        modifier = Modifier
            .width(64.dp)
            .height(64.dp)
    )
}

@Composable
fun LineHorizontal() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .padding(5.dp)
                .width(120.dp)
                .background(Color(0xFFDFDFDF))
        )
        Text(
            text = "Or Login With",
            fontSize = FontSize.sm,
            fontFamily = PlusJakartaSansFont
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(5.dp)
                .width(120.dp)
                .background(Color(0xFFDFDFDF))
        )
    }
}

@Composable
fun RememberMe() {
    var Checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Checkbox(
            checked = Checked,
            onCheckedChange = {},
            colors = CheckboxDefaults.colors(Color.Blue)
        )

        Text(
            text = "Remember Me",
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.md,
            modifier = Modifier
                .clickable(
                    onClick = { Checked = !Checked }
                )
                .offset(x = (-5).dp)
        )
    }
}

@Composable
fun ForgotPassword() {
    Text(
        text = "Forgot Password?",
        color = Color.Blue,
        fontSize = FontSize.md,
        fontFamily = PlusJakartaSansFont,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clickable(
                onClick = {}
            )
    )
}

@Composable
fun SectionInputAndLogin(home: () -> Unit) {
    Column() {
        var emailValue by remember { mutableStateOf("") }
        var passwordValue by remember { mutableStateOf("") }

        val isFill = emailValue.isEmpty() && passwordValue.isEmpty()

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppButtonLogin("Google", image = R.drawable.google, onclick = {})
            AppButtonLogin("Google", image = R.drawable.apple, onclick = {})
        }

        Spacer(Modifier.height(20.dp))

        LineHorizontal()

        Spacer(Modifier.height(30.dp))

        AppInput(
            "Email Address",
            placeholder = "Email",
            value = emailValue,
            onvaluechange = { emailValue = it },
            isPassword = false)
        Spacer(Modifier.height(10.dp))
        AppInput(
            "Password",
            placeholder = "Password",
            value = passwordValue,
            onvaluechange = { passwordValue = it },
            isPassword = true)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RememberMe()
            ForgotPassword()
        }

        Spacer(Modifier.height(10.dp))

        AppButton(
            "Login",
            onClick = {
                home()
            },
            modifier = Modifier,
            txColor = if (isFill) Color(0xFFA1A1A1) else Color.White,
            bgColor = if (isFill) Color(0xFFEDEDED) else Color.Blue
        )
    }
}

@Composable
fun SectionRegisterNow(registerNowPage: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don’t have an account?",
            fontFamily = PlusJakartaSansFont,
            color = Color(0xFF7B7B7B),
            fontSize = FontSize.sm
        )

        Text(
            text = "Register Now",
            fontSize = FontSize.sm,
            fontFamily = PlusJakartaSansFont,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable(
                    onClick = registerNowPage
                )
        )
    }
}

@Composable
@Preview
fun LoginPriview() {
    Login(home = {}, registerNowPage = {})
}