package com.example.modsapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.details.ui.detailScreen
import com.example.details.ui.navigateToDetailScreen
import com.example.home.ui.HOME_ROUTE
import com.example.home.ui.homeScreen

@Composable
fun MainApp() {
    val navController= rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController =navController,
        startDestination = HOME_ROUTE){

        homeScreen(onItemClick = {blogId->
            navController.navigateToDetailScreen(blogId)
        })
        detailScreen()
    }
}