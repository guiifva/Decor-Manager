package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreateThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreatedThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme.GetThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes.ListThemesDto
import com.nocta.eventmanager.catalog.domain.Theme
import java.util.*

fun Theme.toCreatedThemeDto(): CreatedThemeDto {
    return CreatedThemeDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        size = this.size,
        description = this.description,
        active = this.active,
        price = this.price
    )
}

fun CreateThemeDto.toTheme(): Theme {
    return Theme(
        name = this.name,
        size = this.size,
        description = this.description,
        active = this.active,
        price = this.price
    )
}

fun Theme.toGetThemeDto(): GetThemeDto {
    return GetThemeDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        size = this.size,
        description = this.description,
        active = this.active,
        price = this.price
    )
}

fun Theme.toListThemesDto(): ListThemesDto {
    return ListThemesDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        size = this.size,
        description = this.description,
        active = this.active,
        price = this.price
    )
}



