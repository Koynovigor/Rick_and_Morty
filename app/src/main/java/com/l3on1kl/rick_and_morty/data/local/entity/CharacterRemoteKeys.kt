package com.l3on1kl.rick_and_morty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class CharacterRemoteKeys(
    @PrimaryKey val characterId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
