package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreateThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreatedThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme.GetThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes.ListThemesDto
import com.nocta.eventmanager.catalog.domain.Theme
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ThemeMapper {
    companion object {
        val INSTANCE: ThemeMapper = Mappers.getMapper(ThemeMapper::class.java)
    }

    fun toCreatedThemeDto(theme: Theme): CreatedThemeDto
    fun toEntity(createThemeDto: CreateThemeDto): Theme
    fun toListThemeDto(theme: Theme): ListThemesDto
    fun toGetThemeDto(theme: Theme): GetThemeDto
}