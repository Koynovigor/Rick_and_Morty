package com.l3on1kl.rick_and_morty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterRemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CharacterRemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE characterId = :id")
    suspend fun remoteKeysById(id: Int): CharacterRemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
