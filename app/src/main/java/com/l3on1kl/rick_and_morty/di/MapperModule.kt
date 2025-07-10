package com.l3on1kl.rick_and_morty.di

import com.l3on1kl.rick_and_morty.data.local.entity.EntityToDomainMapper
import com.l3on1kl.rick_and_morty.data.remote.mapper.DtoToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideDtoToEntity() = DtoToEntityMapper()

    @Provides
    @Singleton
    fun provideEntityToDomain() = EntityToDomainMapper()
}
