package com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme

import com.nocta.eventmanager.catalog.domain.exceptions.ThemeAlreadyExistsException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toCreatedThemeDto
import com.nocta.eventmanager.catalog.infrastructure.mappers.toTheme
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateThemeUseCaseImpl(private val repository: ThemeRepository) : CreateThemeUseCase {

    @Transactional
    override fun execute(createThemeDto: CreateThemeDto): CreatedThemeDto {
        if (repository.findByNameIgnoreCase(createThemeDto.name) != null) {
            throw ThemeAlreadyExistsException(createThemeDto.name)
        }

        val theme = createThemeDto.toTheme()

        repository.save(theme)

        return theme.toCreatedThemeDto()
    }
}