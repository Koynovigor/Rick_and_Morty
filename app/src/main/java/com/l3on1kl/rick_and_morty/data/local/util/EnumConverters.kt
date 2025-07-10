package com.l3on1kl.rick_and_morty.data.local.util

import androidx.room.TypeConverter
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status

object EnumConverters {

    @TypeConverter
    fun toStatus(
        value: String?
    ): Status = Status.safeValueOf(value)

    @TypeConverter
    fun fromStatus(
        status: Status
    ): String = status.name

    @TypeConverter
    fun toGender(
        value: String?
    ): Gender = Gender.safeValueOf(value)

    @TypeConverter
    fun fromGender(
        gender: Gender
    ): String = gender.name
}
