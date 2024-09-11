package com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme

import com.nocta.eventmanager.catalog.infrastructure.mappers.ThemeMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateThemeUseCaseImpl(private val repository: ThemeRepository) : CreateThemeUseCase {

    private val themeMapper = ThemeMapper.INSTANCE

    @Transactional
    override fun createTheme(createThemeDto: CreateThemeDto): CreatedThemeDto {
        val theme = themeMapper.toEntity(createThemeDto)

        repository.save(theme)

        return themeMapper.toCreatedThemeDto(theme)
    }
}