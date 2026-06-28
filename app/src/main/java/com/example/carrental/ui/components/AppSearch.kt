package com.example.carrental.ui.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrental.ui.theme.FontSize
import com.example.carrental.ui.theme.PlusJakartaSansFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearch() {
    val searchValue = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = searchValue.value,
        onValueChange = { ketikanUser ->
            searchValue.value = ketikanUser
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
    ) { innerPadding ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = searchValue.value,
            innerTextField = innerPadding,
            singleLine = true,
            enabled = true,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
            placeholder = {
                Text(
                    text = "Search",
                        color = Color(0xFFADADAD),
                        fontSize = FontSize.md,
                        fontFamily = PlusJakartaSansFont
                )
            },
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            isError = false,
            leadingIcon = {
                Icon(
                    painter = painterResource(com.example.carrental.R.drawable.search_01),
                    contentDescription = ""
                )
            },
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = true,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xFFDFDFDF),
                        focusedBorderColor = Color(0xFFDFDFDF)
                    ),
                    shape = RoundedCornerShape(50)
                )
            }
        )
    }


//    Column(modifier = Modifier.fillMaxWidth()) {
//        BasicTextField(
//            value = searchValue.value,
//            onValueChange = { textBaru ->
//                searchValue.value = textBaru
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(40.dp)
//        ) { innerTextField ->
//            TextFieldDefaults.DecorationBox(
//                value = searchValue.value,
//                innerTextField = innerTextField,
//                enabled = true,
//                singleLine = true,
//                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
//                placeholder = {
//                    Text(
//                        text = "Search",
//                        color = Color(0xFFADADAD),
//                        fontSize = FontSize.md,
//                        fontFamily = PlusJakartaSansFont
//                    )
//                },
//                visualTransformation = VisualTransformation.None,
//                interactionSource = interactionSource,
//                container = {
//                    OutlinedTextFieldDefaults.ContainerBox(
//                        enabled = true,
//                        isError = false,
//                        interactionSource = interactionSource,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedContainerColor = Color.White,
//                            unfocusedContainerColor = Color.White,
//                            unfocusedBorderColor = Color(0xFFDFDFDF),
//                            focusedBorderColor = Color(0xFFDFDFDF)
//
//                        ),
//                        shape = RoundedCornerShape(50)
//                    )
//                },
//                leadingIcon = {
//                    Icon(
//                        painter = painterResource(com.example.carrental.R.drawable.search_01),
//                        contentDescription = ""
//                    )
//                }
//            )
//        }
//    }
}


@Composable
@Preview
fun AppSearchPreview() {
    AppSearch()
}