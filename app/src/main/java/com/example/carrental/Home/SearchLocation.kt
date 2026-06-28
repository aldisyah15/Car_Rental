package com.example.carrental.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrental.Home.data_dummy.DaftarLocasi
import com.example.carrental.R
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont


data class result(val kota: String, val Kecamatan: String)

@Composable
fun SearchLocation() {
    val state = rememberTextFieldState("")


    val filtered = remember(state.text) {
        if (state.text.isEmpty()) {
            emptyList()
        } else {
            DaftarLocasi.flatMap { lokasi ->
                lokasi.kecamatan
                    .filter { it.contains(state.text, ignoreCase = true) }
                    .map { kecamatanCocok -> result(lokasi.kota, kecamatanCocok) }

            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = "Cari alamat..",
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.md,
            modifier = Modifier
                .padding(bottom = 10.dp),
            color = Color.Gray
        )

        Box(
            modifier = Modifier

        ) {
            BasicTextField(
                state = state,
                lineLimits = TextFieldLineLimits.SingleLine,
                decorator = { inner ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(color = Color.White, RoundedCornerShape(20.dp))
                            .border(
                                width = 0.4.dp,
                                color = Color(0xFFDFDFDF),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.search_01,
                            ),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(Modifier.width(16.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            if (state.text.isEmpty()) {
                                Text(
                                    text = "Search....",
                                    fontSize = FontSize.sm,
                                    fontFamily = PlusJakartaSansFont,
                                    color = Color(0xFFDFDFDF)
                                )
                            }
                            inner()
                        }


                    }
                }
            )
        }

        LazyColumn() {
            items(filtered) { daerah ->
                Text(
                    text = "Kota: ${daerah.Kecamatan}",
                    color = Color.Blue
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchLocationPreview() {
    SearchLocation()
}
