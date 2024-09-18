package com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme


import com.nocta.eventmanager.catalog.domain.exceptions.ThemeNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toGetThemeDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetThemeUseCaseImpl(private val repository: ThemeRepository) : GetThemeUseCase {
    override fun execute(id: UUID): GetThemeDto {
        val theme = repository.findById(id).orElseThrow { ThemeNotFoundException(id) }

        return theme.toGetThemeDto()
    }
}