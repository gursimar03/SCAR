///*
// * Copyright (C) 2023 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.example.scar.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//import com.example.scar.R
//import com.example.scar.ui.theme.Leaderboard
//import com.example.scar.ui.theme.SCARTheme
//
//@Composable
//fun HomeScreen(
//    leaderboardUiState: LeaderboardUiState, modifier: Modifier = Modifier
//) {
//    when (leaderboardUiState) {
//        is LeaderboardUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is LeaderboardUiState.Success -> Leaderboard(
//            leaderboardUiState.user,
//        )
//
//        is LeaderboardUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
//    }
//}
//
///**
// * The home screen displaying the loading message.
// */
//@Composable
//fun LoadingScreen(modifier: Modifier = Modifier) {
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.background),
//        contentDescription = "loading"
//    )
//}
//
///**
// * The home screen displaying error message with re-attempt button.
// */
//@Composable
//fun ErrorScreen(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = ""
//        )
//        Text(text = "failed", modifier = Modifier.padding(16.dp))
//    }
//}
//
///**
// * ResultScreen displaying number of photos retrieved.
// */
//@Composable
//fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//    ) {
//        Text(text = photos)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoadingScreenPreview() {
//    SCARTheme {
//        LoadingScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ErrorScreenPreview() {
//    SCARTheme {
//        ErrorScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PhotosGridScreenPreview() {
//    SCARTheme {
//        ResultScreen(stringResource(R.string.app_name))
//    }
//}
