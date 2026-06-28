package com.example.carrental

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carrental.Home.Car
import com.example.carrental.Home.CarList
import com.example.carrental.Login.LineHorizontal
import com.example.carrental.ui.components.AppButton
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

data class Brand(
    val name: String,
    val imageResID: Int
)


@Composable
fun CarDetailScreen(id: String? = "1", navController: NavController) {
    val car = CarList.find { it.id == id }
    val Pager = rememberPagerState(
        pageCount = { car?.imageID?.size ?: 0 }
    )

    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme()) Color(0xFF3A3B3F) else Color(
                        0xFFF7F7F7
                    )
            )
        ) {
            LazyColumn() {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = innerPadding.calculateTopPadding())
                            .background(
                                color = if (isSystemInDarkTheme()) Color(0xFF3A3B3F) else Color(
                                    0xFFF7F7F7
                                )
                            )
                    ) {
                        CarouselImage(image = car?.imageID ?: emptyList(), pager = Pager)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(car?.imageID?.size ?: 0) { i ->
                                val isSelect = i == Pager.currentPage
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .size(7.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = if (isSelect) Color.Blue else Color(
                                                0xFFEDEDED
                                            )
                                        )
                                )
                            }
                        }

                        Spacer(Modifier.height(20.dp))

                        CarDetail(car = car, paddingValues = innerPadding)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp,
                        top = innerPadding.calculateTopPadding()
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackIcon(navHostController = navController)
                Favorite()
            }
        }
    }
}

@Composable
fun BackIcon(navHostController: NavController) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                shape = RoundedCornerShape(50)
            )
            .clickable(
                onClick = { navHostController.popBackStack() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(5.dp),
            colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
        )
    }
}

@Composable
fun Favorite() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                shape = RoundedCornerShape(50)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.love),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(5.dp),
            colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
        )
    }
}

@Composable
fun CarouselImage(image: List<Int>, pager: PagerState) {
    Column(
        modifier = Modifier
            .height(200.dp)
    ) {
        HorizontalPager(
            state = pager
        ) { page ->
            Image(
                painter = painterResource(image[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun CarDetail(car: Car?, paddingValues: PaddingValues) {
    var isExpand by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 15.dp, end = 15.dp, bottom = paddingValues.calculateBottomPadding())
            .background(
                if (isSystemInDarkTheme()) Color(0xFF050A10) else Color.Gray,
                shape = RoundedCornerShape(5)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                "Rolls Royce Ghost Series 2",
                fontFamily = PlusJakartaSansFont,
                fontSize = FontSize.xl,
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )

            Spacer(Modifier.height(10.dp))
            Text(
                text = car?.description.toString(),
                fontSize = FontSize.md,
                fontFamily = PlusJakartaSansFont,
                color = if (isSystemInDarkTheme()) Color(0xFFA1A1A1) else Color.Black,
                maxLines = if (isExpand) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = if (isExpand) "Read Less" else "...Read More",
                fontSize = FontSize.lg,
                fontFamily = PlusJakartaSansFont,
                color = Color(0xFF3792FA),
                modifier = Modifier.clickable(
                    onClick = { isExpand = !isExpand }
                )
            )
            Spacer(Modifier.height(10.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontFamily = PlusJakartaSansFont,
                            fontSize = FontSize.md,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(car?.rentalPrice)
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = PlusJakartaSansFont,
                            fontSize = FontSize.sm,
                            color = if (isSystemInDarkTheme()) Color(0xFFA1A1A1) else Color.White
                        )
                    ) {
                        append("/day")
                    }
                }
            )
            Spacer(Modifier.height(15.dp))
            HorizontalDivider()
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Fiture(
                    img = R.drawable.gear,
                    text = car?.gearType ?: ""
                )
                Fiture(
                    img = R.drawable.engine1,
                    text = car?.machineType ?: ""
                )
                Fiture(
                    img = R.drawable.seat,
                    text = car?.seat ?: ""
                )
            }

            Spacer(Modifier.height(15.dp))
            SalesProfile(text = car?.salesName ?: "")
        }
    }
}

@Composable
fun Fiture(img: Int, text: String) {
    Row(
        modifier = Modifier
            .height(44.dp)
            .background(
                color = if (isSystemInDarkTheme()) Color(0xFF242528) else Color(0xFFF7F7F7),
                shape = RoundedCornerShape(50)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(img),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(7.dp))
            Text(
                text = text,
                fontSize = FontSize.sm,
                fontFamily = PlusJakartaSansFont,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
    }
}

@Composable
fun SalesProfile(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Image(
                painter = painterResource(R.drawable.profile_sales),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
            )

            Spacer(Modifier.width(15.dp))

            Column() {
                Text(
                    text = "Sales Person",
                    fontSize = FontSize.sm,
                    fontFamily = PlusJakartaSansFont,
                    color = Color(0xFF7B7B7B)
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = text,
                    fontSize = FontSize.md,
                    fontFamily = PlusJakartaSansFont,
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }

        Row(
        ) {
                Image(
                    painter = painterResource(R.drawable.chatting),
                    contentDescription = "",
                    modifier = Modifier
                            .size(48.dp)
                        .background(Color(0xFF3B82F6), shape = RoundedCornerShape(50))
                        .padding(12.dp),
                    alignment = Alignment.Center
                )

        }
    }

    Spacer(Modifier.height(15.dp))
    BookNow(
        text = "Book Now",
        onClick = { /*TODO*/ },
        modifier = Modifier,
        bgColor = Color(0xFF3B82F6),
        txColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    )
}

@Composable
fun BookNow(text: String, onClick: () -> Unit, modifier: Modifier, bgColor: Color, txColor: Color) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = FontSize.md,
                fontFamily = PlusJakartaSansFont,
                color = if (isSystemInDarkTheme()) Color.White else txColor
            )
        }
    }
}

@Composable
@Preview()
fun CarDetailScreenPreview() {
    CarDetailScreen(id = "1", navController = {} as NavController)
}
