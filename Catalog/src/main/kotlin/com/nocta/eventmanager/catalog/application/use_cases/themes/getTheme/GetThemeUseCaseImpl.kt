package com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme


import com.nocta.eventmanager.catalog.domain.exceptions.ThemeNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.mappers.ThemeMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetThemeUseCaseImpl(private val repository: ThemeRepository) : GetThemeUseCase {

    private val themeMapper = ThemeMapper.INSTANCE

    override fun getTheme(id: UUID): GetThemeDto {
        val theme = repository.findById(id).orElseThrow { ThemeNotFoundException() }

        return themeMapper.toGetThemeDto(theme)
    }
}