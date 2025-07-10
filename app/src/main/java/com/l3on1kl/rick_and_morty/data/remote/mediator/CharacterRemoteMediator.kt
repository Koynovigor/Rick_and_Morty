package com.l3on1kl.rick_and_morty.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.l3on1kl.rick_and_morty.data.local.CharacterDatabase
import com.l3on1kl.rick_and_morty.data.local.dao.CharacterDao
import com.l3on1kl.rick_and_morty.data.local.dao.RemoteKeysDao
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterRemoteKeys
import com.l3on1kl.rick_and_morty.data.remote.api.CharacterApi
import com.l3on1kl.rick_and_morty.data.remote.mapper.DtoToEntityMapper
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: CharacterApi,
    private val database: CharacterDatabase,
    private val dao: CharacterDao,
    private val keysDao: RemoteKeysDao,
    private val dtoToEntity: DtoToEntityMapper
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult = try {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = state.anchorPosition?.let { pos ->
                    state.closestItemToPosition(pos)?.id?.let {
                        keysDao.remoteKeysById(it)
                    }
                }

                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE
            }

            LoadType.PREPEND -> {
                val first = state.firstItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)

                keysDao.remoteKeysById(first.id)?.prevKey
                    ?: return MediatorResult.Success(true)
            }

            LoadType.APPEND -> {
                val last = state.lastItemOrNull()
                    ?: return MediatorResult.Success(true)

                keysDao.remoteKeysById(last.id)?.nextKey
                    ?: return MediatorResult.Success(true)
            }
        }

        val response = api.getCharacters(page)
        val entities = response.results.map(dtoToEntity)

        database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
                keysDao.clearRemoteKeys()
            }

            val keys = buildList {
                val prev = if (page == INITIAL_PAGE) null else page - 1

                val next = if (response.pageInfo.next == null) null else page + 1

                entities.forEach {
                    add(
                        CharacterRemoteKeys(
                            it.id,
                            prev,
                            next
                        )
                    )
                }
            }

            keysDao.insertAll(keys)
            dao.insertAll(entities)
        }

        MediatorResult.Success(
            endOfPaginationReached = response.pageInfo.next == null
        )

    } catch (e: IOException) {
        MediatorResult.Error(e)
    } catch (e: HttpException) {
        MediatorResult.Error(e)
    }
}
