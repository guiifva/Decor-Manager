package com.nocta.eventmanager.catalog.application.use_cases.categories.categoryNameAlreadyExists

import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryNameAlreadyExistsUseCaseImpl(private val categoryRepository: CategoryRepository) :
    CategoryNameAlreadyExistsUseCase {
    override fun execute(name: String, id: UUID?): Boolean {
        return categoryRepository.findByNameAndIdIgnoreCase(name, id) != null
    }
}