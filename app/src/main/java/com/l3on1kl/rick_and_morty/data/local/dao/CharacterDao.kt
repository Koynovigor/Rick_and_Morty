package com.l3on1kl.rick_and_morty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query(
        """
        SELECT * FROM characters 
        WHERE name LIKE '%' || :nameQuery || '%' 
        ORDER BY id ASC 
    """
    )
    fun pagingSourceByName(nameQuery: String): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Transaction
    @Query("DELETE FROM characters")
    suspend fun clearAll()
}
