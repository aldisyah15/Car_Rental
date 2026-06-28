package com.example.carrental.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont

@Composable
fun AppInput(title: String, placeholder: String, onvaluechange: (String) -> Unit, value: String, isPassword: Boolean) {

   Column() {
       Text(
           text = title,
           fontFamily = PlusJakartaSansFont,
           fontSize = FontSize.sm,
           color = Color(0xFFA1A1A1)
       )
       Spacer(Modifier.height(10.dp))
       TextField(
           value = value,
           onValueChange = onvaluechange,
           placeholder = {
               Text(
                   text = placeholder,
               )
           },
           singleLine = true,
           modifier = Modifier.fillMaxWidth().border(1.dp, color = Color(0xFFDFDFDF), shape = RoundedCornerShape(20) ),
           colors = TextFieldDefaults.colors(
               focusedIndicatorColor = Color.Transparent,
               unfocusedIndicatorColor = Color.Transparent,
               focusedContainerColor = Color.Transparent,
               unfocusedContainerColor = Color.Transparent,
               unfocusedTextColor = Color(0xFFC8C8C8),
               unfocusedLabelColor = Color(0xFFC8C8C8),
           ),
           visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
       )
   }
}