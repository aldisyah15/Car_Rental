package com.example.carrental.Schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SchedulePage() {
    Column() {
        Text(
            text = "hello schedule"
        )
    }
}

@Composable
@Preview
fun SchedulePagePreview() {
    SchedulePage()
}