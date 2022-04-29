package com.rm.pagingtutorial.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rm.pagingtutorial.data.dto.Data
import com.rm.pagingtutorial.data.remote.PassengerApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PassengerDataSource @Inject constructor(private val passengerApi: PassengerApi) :
    PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = passengerApi.getPassengers(nextPageNumber)
            LoadResult.Page(
                data = response.`data`,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}