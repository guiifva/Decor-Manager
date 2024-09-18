package com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories

import com.nocta.eventmanager.catalog.infrastructure.mappers.toListCategoriesDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class ListCategoriesUseCaseImpl(private val categoriesRepository: CategoryRepository) : ListCategoriesUseCase {
    override fun execute(): List<ListCategoriesDto> =
        categoriesRepository.findAll().map { it.toListCategoriesDto() }
}