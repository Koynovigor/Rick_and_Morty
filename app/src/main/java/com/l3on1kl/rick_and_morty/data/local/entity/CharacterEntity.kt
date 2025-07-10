package com.l3on1kl.rick_and_morty.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status

@Entity(
    tableName = "characters",
    indices = [
        Index(
            "name",
            unique = false
        )
    ]
)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    @ColumnInfo(name = "image_url") val imageUrl: String
)
