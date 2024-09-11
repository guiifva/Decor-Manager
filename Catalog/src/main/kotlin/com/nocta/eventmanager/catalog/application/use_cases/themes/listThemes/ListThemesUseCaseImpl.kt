package com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes

import com.nocta.eventmanager.catalog.infrastructure.mappers.ThemeMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service

@Service
class ListThemesUseCaseImpl(private val themesRepository: ThemeRepository) : ListThemesUseCase {
    private val themeMapper = ThemeMapper.INSTANCE

    override fun listAllThemes(): List<ListThemesDto> {
        return themesRepository.findAll().map { themeMapper.toListThemeDto(it) }
    }
}