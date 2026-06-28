package com.example.carrental.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoritePage() {
    Column() {
        Text(text = "Favorite")
    }
}

@Composable
@Preview
fun PreviewFavoritePage() {
    FavoritePage()
}