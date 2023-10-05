package com.example.vktestapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException


class GifsPageSource(private val query:String?, private val gifsRepository: GifsRepository): PagingSource<Int, GifClass>() {



    override fun getRefreshKey(state: PagingState<Int, GifClass>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifClass> {
        try {
            val pageNumber = params.key ?: 1
            val pageSize = params.loadSize.coerceAtMost(25)
            val offset = pageSize * pageNumber
            Log.d("WTF", "$offset and $pageSize and $query")
            val response = gifsRepository.getSearchedGifs(query, pageSize, offset)
            Log.d("WTF", "WTF")
            return if (response.isSuccessful) {
                val gifs = response.body()!!.data

                Log.d("gifs", gifs.map { it-> it.id }.toString())
                val nextPageNumber = if (gifs.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(gifs, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            Log.d("WTF", "HttpException")
            return LoadResult.Error(e)
        } catch (e: Exception) {
            Log.d("WTF", "Another Exception")
            return LoadResult.Error(e)
        }
    }



}