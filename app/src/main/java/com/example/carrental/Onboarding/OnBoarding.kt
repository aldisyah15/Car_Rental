package com.example.carrental.Onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrental.R
import com.example.carrental.ui.components.AppButton
import com.example.carrental.ui.theme.FontSize

data class OnBoardingPage(
    val img: Int,
    val title: String,
    val description: String
)

val pages = listOf(
    OnBoardingPage(
        R.drawable.image1,
        "Discover the Perfect Car \nfor Every Journey",
        "Explore a wide selection of vehicles designed \nto match your travel needs."
    ),
    OnBoardingPage(
        R.drawable.image2,
        "Book Your Rental Easily \nin Just a Few Steps",
        "Choose your dates, location, and car with \na smooth booking experience."
    ),
    OnBoardingPage(
        R.drawable.image3,
        "Pick Up Your Car and \nStart Driving",
        "Quick pickups, easy returns, and \na hassle-free rental process."
    )

)

@Composable
fun Onboarding(onFinish: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DisplayContent(pages[currentStep], currentStep)

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AppButton("Skip", {if (currentStep <= 0) currentStep = pages.size - 1 else currentStep--}, Modifier.width(163.dp), Color(0xFFF7F7F7), Color.Black)
            AppButton("Next", {if (currentStep >= pages.size -1 ) onFinish() else currentStep++}, Modifier.width(163.dp), Color.Blue, Color.White)
        }
    }
}

@Composable
fun DisplayContent(page: OnBoardingPage, currentStep: Int = 0) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(page.img),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .background(Color(0xFFF7F7F7))
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(9.dp)
                        .clip(CircleShape)
                        .background(if (index == currentStep) Color.Blue else Color(0xFFDFDFDF))

                )
            }
        }

        Text(
            text = page.title,
            fontSize = FontSize.xl,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = page.description,
            fontSize = FontSize.sm,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF7B7B7B),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingPreview() {
    Onboarding({})
}