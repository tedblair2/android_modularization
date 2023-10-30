package com.example.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ARG_1="blog_id"
const val DETAIL_ROUTE="details/{$ARG_1}"

fun NavGraphBuilder.detailScreen(){
    composable(route = DETAIL_ROUTE,
        arguments = listOf(
            navArgument(ARG_1){
                type= NavType.StringType
            }
        ) ){
        val id=it.arguments?.getString(ARG_1).toString()
        DetailScreen(id = id)
    }
}

fun NavController.navigateToDetailScreen(id:String){
    navigate("details/$id")
}

@Composable
internal fun DetailScreen(id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text = id, fontSize = 30.sp)
    }
}