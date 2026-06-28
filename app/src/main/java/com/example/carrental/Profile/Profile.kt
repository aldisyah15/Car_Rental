package com.example.carrental.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfilePage() {
    Column() {
        Text(
            text = "Hello Profile"
        )
    }
}

@Composable
@Preview
fun ProfilePagePreview() {
    ProfilePage()
}