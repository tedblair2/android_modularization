package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.home.viewmodel.HomeViewModel
import com.example.local.model.Blog

const val HOME_ROUTE="home"
fun NavGraphBuilder.homeScreen(onItemClick: (blogId: String) -> Unit={}){
    composable(route = HOME_ROUTE){
        HomeScreen(onItemClick = {onItemClick(it)})
    }
}

@Composable
internal fun HomeScreen(viewModel: HomeViewModel= hiltViewModel(),onItemClick: (blogId: String) -> Unit) {
    val blogs = viewModel.blogs.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(
            count =blogs.itemCount,
            key = blogs.itemKey { it.id }
        ){
            blogs[it]?.let {blog ->
                PostItem(blog = blog, onItemClick = {id->
                    onItemClick(id)
                })
            }
        }
    }
}

@Composable
fun PostItem(blog: Blog,onItemClick:(blogId:String)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(bottom = 6.dp)
        .clickable { onItemClick(blog.id) },
        verticalArrangement = Arrangement.Center){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp),
            verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
                contentAlignment = Alignment.Center){
                AsyncImage(model = blog.owner?.picture, contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape), contentScale = ContentScale.Crop)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text ="${blog.owner?.firstName} ${blog.owner?.lastName}", fontSize = 15.sp )
        }
        AsyncImage(model = blog.image,
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = blog.text ?: "" , fontSize = 17.sp)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp))
    }
}