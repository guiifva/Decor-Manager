package com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory

import com.nocta.eventmanager.catalog.domain.exceptions.CategoryNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toGetCategoryDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : GetCategoryUseCase {
    override fun execute(id: UUID): GetCategoryDto {
        return categoryRepository.findById(id).orElseThrow { CategoryNotFoundException(id) }.toGetCategoryDto()
    }
}