package com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories

import com.nocta.eventmanager.catalog.infrastructure.mappers.CategoryMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class ListCategoriesUseCase(
    private val categoriesRepository: CategoryRepository,
) {
    private val categoryMapper = CategoryMapper.INSTANCE

    fun listAllCategories(): List<ListCategoriesDto> {
        return categoriesRepository.findAll().map { categoryMapper.toListCategoryDto(it) }
    }
}