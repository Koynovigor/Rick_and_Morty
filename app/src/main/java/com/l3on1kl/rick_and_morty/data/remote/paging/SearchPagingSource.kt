package com.l3on1kl.rick_and_morty.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.l3on1kl.rick_and_morty.data.local.dao.CharacterDao
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.remote.api.CharacterApi
import com.l3on1kl.rick_and_morty.data.remote.mapper.DtoToEntityMapper
import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter

private const val INITIAL_PAGE = 1

class SearchPagingSource(
    private val api: CharacterApi,
    private val filter: CharacterFilter,
    private val dtoToEntity: DtoToEntityMapper,
    private val dao: CharacterDao,
) : PagingSource<Int, CharacterEntity>() {

    override fun getRefreshKey(
        state: PagingState<Int, CharacterEntity>
    ): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, CharacterEntity> {
        val page = params.key ?: INITIAL_PAGE

        return try {
            val response = api.getCharacters(
                page = page,
                name = filter.name,
                status = filter.status?.name?.lowercase(),
                species = filter.species,
                gender = filter.gender?.name?.lowercase()
            )
            val entities = response.results.map(dtoToEntity)
            dao.insertAll(entities)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.pageInfo.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
