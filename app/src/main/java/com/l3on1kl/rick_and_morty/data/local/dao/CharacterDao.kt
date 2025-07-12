package com.l3on1kl.rick_and_morty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query(
        """
        SELECT COUNT(*) FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species LIKE '%' || :species || '%')
          AND (:gender IS NULL OR gender = :gender)
    """
    )
    fun observeCountByFilter(
        name: String?,
        status: Status?,
        species: String?,
        gender: Gender?
    ): Flow<Int>

    @Query(
        """
        SELECT * FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species LIKE '%' || :species || '%')
          AND (:gender IS NULL OR gender = :gender)
        ORDER BY id ASC
    """
    )
    fun pagingSourceByFilter(
        name: String?,
        status: Status?,
        species: String?,
        gender: Gender?
    ): PagingSource<Int, CharacterEntity>

    @Query("SELECT COUNT(*) FROM characters")
    fun observeCount(): Flow<Int>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Transaction
    @Query("DELETE FROM characters")
    suspend fun clearAll()
}
