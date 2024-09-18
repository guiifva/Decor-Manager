package com.nocta.eventmanager.catalog.application.use_cases.categories.categoryNameAlreadyExists

import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryNameAlreadyExistsUseCaseImpl(private val categoryRepository: CategoryRepository) :
    CategoryNameAlreadyExistsUseCase {
    override fun execute(name: String): Boolean {
        return categoryRepository.findByNameIgnoreCase(name) != null
    }
}