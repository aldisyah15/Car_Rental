package com.example.carrental.Home

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.carrental.Home.SelectDate.Selectdate
import com.example.carrental.R
import com.example.carrental.ui.components.AppButton
import com.example.carrental.ui.components.AppSearch
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

data class Brand(
    val name: String,
    val urlImage: String
)
val BrandList = listOf(
    Brand(
        name = "Royce",
        urlImage = "https://ffbbfiacmsntgsetdzqf.supabase.co/storage/v1/object/public/logo/rolls-royce1.png"
    ),
    Brand(
        name = "ferari",
        urlImage = "https://ffbbfiacmsntgsetdzqf.supabase.co/storage/v1/object/public/logo/ferari.png"
    ),
    Brand(
        name = "lamborgini",
        urlImage = "https://ffbbfiacmsntgsetdzqf.supabase.co/storage/v1/object/public/logo/lamborghini.png"
    ),
    Brand(
        name = "Mercedes",
        urlImage = "https://ffbbfiacmsntgsetdzqf.supabase.co/storage/v1/object/public/logo/mercedes.png"
    )
)

data class Car(
    val id: String,
    val name: String,
    val rentalPrice: String,
    val imageID: List<Int>,
    val machineType: String,
    val gearType: String,
    val seat: String,
    val description: String,
    val salesName: String,
    val foto_sales: Int,
)
val CarList = listOf(
    Car(
        id = "1",
        name = "Rolls Royce Ghost Series 2",
        rentalPrice = "$3800",
        imageID = listOf(
            R.drawable.ferrari_812,
            R.drawable.jpegroy,
            R.drawable.rolls_royce2
        ),
        machineType = "577 HP",
        gearType = "Automatic",
        seat = "5 Seat",
        salesName = "Budi Darmawan",
        description = """
            Rolls-Royce Ghost Series 2 adalah definisi sejati dari kemewahan tiada tara. Dilengkapi dengan mesin V12 twin-turbocharged 6.6 liter, mobil ini memberikan tenaga yang luar biasa namun tetap senyap di dalam kabin. Setiap detail interior dibuat dengan tangan menggunakan kulit kualitas tertinggi dan kayu asli. Fitur Planar Suspension System yang legendaris membuat Anda merasa seperti melayang di atas aspal. Kendaraan ini bukan sekadar alat transportasi, melainkan simbol status dan kenyamanan maksimal bagi mereka yang menginginkan yang terbaik di dunia otomotif.
        """.trimIndent(),
        foto_sales = R.drawable.google
    ),

    Car(
        id = "2",
        name = "Ferrari Convertible",
        rentalPrice = "$3200",
        imageID = listOf(
            R.drawable.jpegroy22,
            R.drawable.jpegroy,
            R.drawable.rolls_royce2
        ),
        machineType = "223 HP",
        gearType = "Automatic",
        seat = "2 Seat",
        salesName = "Siska Amelia",
        description = """
            Rasakan adrenalin yang memuncak dengan Ferrari Convertible, mahakarya mesin 
            bertenaga tinggi yang menggabungkan kecepatan murni dengan kebebasan berkendara 
            atap terbuka. Dengan desain aerodinamis yang ikonik dan raungan mesin yang 
            khas, mobil ini mampu melesat dari 0 hingga 100 km/jam dalam hitungan detik. 
            Interior kokpit yang fokus pada pengemudi memastikan setiap kendali ada di 
            ujung jari Anda. Sangat cocok bagi Anda yang ingin menikmati pemandangan 
            pesisir pantai atau jalanan kota dengan gaya yang elegan dan performa sport 
            yang tak tertandingi oleh kelas lain.
        """.trimIndent(),
        foto_sales = R.drawable.home_01
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MissingPermission")
@Composable
fun HomeNav(innerPadding: PaddingValues, navController: NavHostController) {
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Mencari lokasi...") }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isBooking by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        getUserLocationName(context) { result ->
            locationText = result
        }
    }

Column() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, bottom = innerPadding.calculateBottomPadding())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            GetLocation(locationText)
            NotificationAndProfile()

        }

        AppSearch()
        Spacer(Modifier.height(10.dp))

        CircleLogo()
        Spacer(Modifier.height(5.dp))

        ScrollCard(isBooking = { isBooking = !isBooking }, navController = navController)

    }
    if (isBooking) {
        ModalBottomSheet(
            onDismissRequest = { isBooking = false },
            sheetState = sheetState,
            modifier = Modifier,
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        ) {
            Selectdate()
        }
    }
}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetLocation(locationText: String) {
    var showDialog by remember { mutableStateOf(false) }

    val sizeAnimation by animateDpAsState(
        targetValue = if (showDialog) 55.dp else 45.dp,
    )

    Row(
        modifier = Modifier
            .clickable(
                onClick = { showDialog = true }
            )) {

        Image(
            painter = painterResource(R.drawable.location),
            contentDescription = null,
            modifier = Modifier
                .background(color = Color.Blue, shape = RoundedCornerShape(50.dp))
                .size(sizeAnimation)
                .padding(10.dp)
        )



        Spacer(Modifier.width(15.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Location",
                fontSize = FontSize.sm,
                fontFamily = PlusJakartaSansFont,
                color = if (isSystemInDarkTheme()) Color.White else Color.Gray
            )
            Text(
                text = locationText,
                fontSize = FontSize.md,
                fontFamily = PlusJakartaSansFont,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }

        if (showDialog) {

            BasicAlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                content = {
                    SearchLocation()
                }
            )
        }
    }
}

@Composable
fun NotificationAndProfile() {
    Row {
        Image(
            painter = painterResource(R.drawable.notification),
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(50))
                .padding(10.dp),
            colorFilter = ColorFilter.tint(color = Color.White)
        )

        Image(
            painter = painterResource(R.drawable.user_circle),
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(50))
                .padding(10.dp),
            colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
        )
    }
}

@Composable
fun CircleLogo() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(BrandList) { brand ->
            Column() {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = Color(0xFFF7F7F7), shape = RoundedCornerShape(50)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = brand.urlImage,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )

                }
                Box(
                    modifier = Modifier
                        .width(60.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = brand.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontFamily = PlusJakartaSansFont,
                        fontSize = FontSize.sm,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }

}

@Composable
fun ScrollCard(isBooking: () -> Unit, navController: NavHostController) {
    LazyColumn() {
        items(CarList) { car ->
            CardHome(
                carName = car.name,
                carPrice = car.rentalPrice,
                urlImage = car.imageID[0],
                engine = car.machineType,
                gear = car.gearType,
                seat = car.seat,
                isBooking = isBooking,
                onClick = {navController.navigate("detail/${car.id}")}
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun CardHome(
    carName: String,
    carPrice: String,
    urlImage: Any,
    engine: String,
    gear: String,
    seat: String,
    isBooking: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(318.dp)
            .background(
                color = if (isSystemInDarkTheme()) Color(0xFF242528) else Color(0xFFF7F7F7),
                RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable(
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = carName,
                fontFamily = PlusJakartaSansFont,
                fontSize = FontSize.xl,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Favorite()
        }

        Spacer(Modifier.height(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = FontSize.md,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    append(carPrice)
                }

                withStyle(
                    style = SpanStyle(
                        fontSize = FontSize.sm,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    append("/day")
                }
            }
        )
        Spacer(Modifier.height(20.dp))
        AsyncImage(
            model = urlImage,
            contentDescription = "",
            modifier = Modifier
                .height(84.44.dp)
                .width(295.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Fiture(image = R.drawable.engine1, text = engine)
            Fiture(R.drawable.gear, text = gear)
            Fiture(R.drawable.seat, seat)
        }

        Spacer(Modifier.height(9.dp))

        AppButton(
            text = "Book Now",
            onClick = isBooking,
            modifier = Modifier,
            bgColor = Color.Blue,
            txColor = Color.White
        )
    }
}


@Composable
fun Favorite() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(color = Color.White, shape = RoundedCornerShape(50)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.love),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
    }
}

@Composable
fun Fiture(image: Int, text: String) {
    Row(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(50))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = text,
            fontSize = FontSize.md,
            fontFamily = PlusJakartaSansFont,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomePrevieww() {
    HomeNav(
        innerPadding = PaddingValues(0.dp),
        navController = {} as NavHostController
    )
}
