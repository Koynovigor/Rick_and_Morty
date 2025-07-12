package com.l3on1kl.rick_and_morty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.l3on1kl.rick_and_morty.data.local.CharacterDatabase
import com.l3on1kl.rick_and_morty.data.local.dao.CharacterDao
import com.l3on1kl.rick_and_morty.data.local.dao.RemoteKeysDao
import com.l3on1kl.rick_and_morty.data.local.entity.EntityToDomainMapper
import com.l3on1kl.rick_and_morty.data.remote.api.CharacterApi
import com.l3on1kl.rick_and_morty.data.remote.mapper.DtoToEntityMapper
import com.l3on1kl.rick_and_morty.data.remote.mediator.CharacterRemoteMediator
import com.l3on1kl.rick_and_morty.data.remote.paging.SearchPagingSource
import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter
import com.l3on1kl.rick_and_morty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi,
    private val database: CharacterDatabase,
    private val dao: CharacterDao,
    private val keysDao: RemoteKeysDao,
    private val dtoToEntity: DtoToEntityMapper,
    private val entityToDomain: EntityToDomainMapper
) : CharacterRepository {
    override fun getCharacters(
        filter: CharacterFilter,
        online: Boolean
    ): Flow<PagingData<Character>> {
        val mediator = if (filter.isEmpty) {
            CharacterRemoteMediator(
                api,
                database,
                dao,
                keysDao,
                dtoToEntity
            )
        } else {
            null
        }

        val pagingFactory = {
            if (filter.isEmpty) {
                dao.pagingSource()
            } else if (online) {
                SearchPagingSource(
                    api,
                    filter,
                    dtoToEntity,
                    dao
                )
            } else {
                dao.pagingSourceByFilter(
                    filter.name,
                    filter.status,
                    filter.species,
                    filter.gender
                )
            }
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = mediator,
            pagingSourceFactory = pagingFactory
        ).flow.map {
            it.map(entityToDomain)
        }
    }

    override fun hasLocalCharacters(
        filter: CharacterFilter
    ): Flow<Boolean> =
        if (filter.isEmpty) {
            dao.observeCount().map { it > 0 }
        } else {
            dao.observeCountByFilter(
                filter.name,
                filter.status,
                filter.species,
                filter.gender
            ).map { it > 0 }
        }
}
