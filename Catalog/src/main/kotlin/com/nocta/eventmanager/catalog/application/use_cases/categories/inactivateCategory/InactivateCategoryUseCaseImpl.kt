package com.nocta.eventmanager.catalog.application.use_cases.themes.inactivateTheme

import com.nocta.eventmanager.catalog.domain.exceptions.ThemeNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InactivateCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : InactivateCategoryUseCase {
    override fun execute(id: UUID) {
        val theme = categoryRepository.findById(id)
            .orElseThrow { ThemeNotFoundException(id) }

        if (theme.active) {
            theme.inactivate()
            categoryRepository.save(theme)
        }
    }
}