package com.l3on1kl.rick_and_morty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.l3on1kl.rick_and_morty.data.local.dao.CharacterDao
import com.l3on1kl.rick_and_morty.data.local.dao.RemoteKeysDao
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.local.entity.CharacterRemoteKeys
import com.l3on1kl.rick_and_morty.data.local.util.EnumConverters

@Database(
    entities = [
        CharacterEntity::class,
        CharacterRemoteKeys::class
    ],
    version = 5,
    exportSchema = true
)
@TypeConverters(EnumConverters::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
