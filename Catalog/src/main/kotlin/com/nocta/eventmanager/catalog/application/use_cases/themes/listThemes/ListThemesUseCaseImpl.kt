package com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes

import com.nocta.eventmanager.catalog.infrastructure.mappers.toListThemesDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service

@Service
class ListThemesUseCaseImpl(private val themesRepository: ThemeRepository) : ListThemesUseCase {
    override fun execute(): List<ListThemesDto> {
        return themesRepository.findAll().map { it.toListThemesDto() }
    }
}