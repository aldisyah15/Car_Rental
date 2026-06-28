package com.example.carrental.Home.SelectDate

import android.annotation.SuppressLint
import androidx.collection.intSetOf
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.platform.LocalLocale
import com.example.carrental.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Selectdate() {

    Surface(
        modifier = Modifier
            .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            HeaderText(textLeft = "Select Date & Member", textRight = "Cancel")
            Spacer(Modifier.height(15.dp))
            HorizontalDivider()
            PickupDate()
            DropPickUp()
            Spacer(Modifier.height(15.dp))
            ProsesToCheckout()
        }
    }
}

@Composable
fun HeaderText(textLeft: String, textRight: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft,
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.lg,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = textRight,
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.sm,
            color = Color(0xFF3B82F6)
        )
    }
}

@SuppressLint("NonObservableLocale")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PickupDate() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Column(
        modifier = Modifier
            .fillMaxWidth()) {
        Spacer(Modifier.height(15.dp))
        Text(
            text = "Pickup date",
            fontSize = FontSize.sm,
            fontFamily = PlusJakartaSansFont,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledBorderColor = Color.Transparent,
            ),
            modifier = Modifier
                .height(48.dp)
                .background(
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    shape = RoundedCornerShape(50)
                )
            .clickable(
                onClick = { showDatePicker = true },
                indication = null,
                interactionSource = null
                )
                .border(1.dp, color = Color(0xFFDFDFDF), shape = RoundedCornerShape(50)),
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(com.example.carrental.R.drawable.calendar_add),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        )
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = { showDatePicker = false }
                    ) {
                        Text(
                            text = "Ok",
                            fontFamily = PlusJakartaSansFont,
                            fontSize = FontSize.lg
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDatePicker = false }
                    ) {
                        Text(
                            text = "Cancel",
                            fontFamily = PlusJakartaSansFont,
                            fontSize = FontSize.lg
                        )
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropPickUp() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Column(
        modifier = Modifier

    ) {
        Spacer(Modifier.height(15.dp))
        Text(
            text = "Dropup Date",
            fontSize = FontSize.sm,
            fontFamily = PlusJakartaSansFont,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            modifier = Modifier
                .height(48.dp)
                .clickable(
                    onClick = {showDatePicker = true},
                    interactionSource = null,
                    indication = null
                )
                .background(
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    shape = RoundedCornerShape(50)
                ),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.calendar_add),
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledBorderColor = Color.Transparent,
            )
        )
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {showDatePicker = false}) {
                        Text(
                            text = "Ok",
                            fontFamily = PlusJakartaSansFont,
                            fontSize = FontSize.lg
                        )
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier
                )
            }
        }

    }
}

@Composable
fun ProsesToCheckout() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF3A3B3F) else Color.Blue )
    ) {
        Text(
            text = "Proceed To Checkout",
            color = Color.White,
            fontFamily = PlusJakartaSansFont,
            fontSize = FontSize.sm
        )
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}