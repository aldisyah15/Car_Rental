package com.example.carrental.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

@Composable
fun AppButtonLogin(text: String, image: Int, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF242528) else Color(0xFFF7F7F7)),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = text,
                fontFamily = PlusJakartaSansFont,
                fontSize = FontSize.md,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}