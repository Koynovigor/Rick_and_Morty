package com.l3on1kl.rick_and_morty.di


import android.app.Application
import androidx.room.Room
import com.l3on1kl.rick_and_morty.data.local.CharacterDatabase
import com.l3on1kl.rick_and_morty.data.local.dao.CharacterDao
import com.l3on1kl.rick_and_morty.data.local.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): CharacterDatabase =
        Room.databaseBuilder(
            app,
            CharacterDatabase::class.java,
            "characters.db"
        )
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideCharacterDao(
        db: CharacterDatabase
    ): CharacterDao = db.characterDao()

    @Provides
    fun provideRemoteKeysDao(
        db: CharacterDatabase
    ): RemoteKeysDao = db.remoteKeysDao()
}
