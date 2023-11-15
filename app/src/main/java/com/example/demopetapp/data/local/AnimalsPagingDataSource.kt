package com.example.demopetapp.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demopetapp.data.mapper.toAnimal
import com.example.demopetapp.data.remote.service.PetFinderService
import com.example.demopetapp.domain.entity.Animal
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimalsPagingDataSource @Inject constructor(
    private val petFinderService: PetFinderService
) : PagingSource<Int, Animal>() {
    override fun getRefreshKey(state: PagingState<Int, Animal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Animal> {
        val position = params.key ?: 1
        val response = petFinderService.getAnimals(position)

        return try {
            LoadResult.Page(
                data = response.animals.map { it.toAnimal() },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.totalPages) null else position + 1,
                itemsAfter = LoadResult.Page.COUNT_UNDEFINED,
                itemsBefore = LoadResult.Page.COUNT_UNDEFINED
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
