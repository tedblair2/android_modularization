package com.example.paging.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.local.model.Blog
import com.example.local.model.BlogKey
import com.example.local.repository.DbService
import com.example.network.ApiResult
import com.example.network.repository.ApiService
import com.example.paging.mappers.toDomain

@OptIn(ExperimentalPagingApi::class)
class BlogsMediator(
    private val dbService: DbService,
    private val apiService: ApiService
):RemoteMediator<Int,Blog>(){

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Blog>): MediatorResult {
        return try {
            val currentPage=when(loadType){
                LoadType.REFRESH->{
                    val remoteKey=getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.next?.minus(1) ?: 1
                }
                LoadType.APPEND->{
                    val remoteKey=getRemoteKeyForLastItem(state)
                    val nextPage=remoteKey?.next ?: return MediatorResult.Success(endOfPaginationReached = remoteKey !=null)
                    nextPage
                }
                LoadType.PREPEND->{
                    val remoteKey=getRemoteKeyForFirstItem(state)
                    val prevPage=remoteKey?.prev ?: return MediatorResult.Success(endOfPaginationReached = remoteKey !=null)
                    prevPage
                }
            }
            when(val response=apiService.getBlogs(currentPage)){
                is ApiResult.Success->{
                    val endOfPaginationReached= response.data?.data?.isEmpty() ?: true

                    val prevPage=if (currentPage==1) null else currentPage.minus(1)
                    val nextPage=if (endOfPaginationReached) null else currentPage.plus(1)

                    val keys=response.data?.data?.let {
                        it.map {blog->
                            BlogKey(blog?.id!!,prevPage,nextPage)
                        }
                    } ?: emptyList()
                    val blogs= response.data?.data?.toDomain() ?: emptyList()
                    dbService.pagingTransaction(blogs,keys,loadType==LoadType.REFRESH)
                    MediatorResult.Success(endOfPaginationReached)
                }
                is ApiResult.Error->{
                    MediatorResult.Error(Exception())
                }
                else -> {MediatorResult.Success(false)}
            }
        }catch (e:Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Blog>): BlogKey?{
        return state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id?.let {id->
                dbService.getBlogKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Blog>):BlogKey?{
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
            it.id.let { id->
                dbService.getBlogKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Blog>):BlogKey?{
        return state.pages.lastOrNull{
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            it.id.let { id->
                dbService.getBlogKey(id)
            }
        }
    }
}