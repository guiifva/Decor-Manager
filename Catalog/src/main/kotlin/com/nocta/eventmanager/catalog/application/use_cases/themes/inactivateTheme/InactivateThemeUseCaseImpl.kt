package com.nocta.eventmanager.catalog.application.use_cases.themes.inactivateTheme

import com.nocta.eventmanager.catalog.domain.exceptions.ThemeNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InactivateThemeUseCaseImpl(private val themeRepository: ThemeRepository) : InactivateThemeUseCase {
    override fun execute(id: UUID) {
        val theme = themeRepository.findById(id)
            .orElseThrow { ThemeNotFoundException(id) }

        if (theme.active) {
            theme.inactivate()
            themeRepository.save(theme)
        }
    }
}