package com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory

import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.infrastructure.mappers.CategoryMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    private val categoryMapper = CategoryMapper.INSTANCE

    @Transactional
    fun createCategory(createCategoryDto: CreateCategoryDto): GetCategoryDto {
        val category = categoryMapper.toEntity(createCategoryDto)

        categoryRepository.save(category)

        return categoryMapper.toCreatedThemeDto(category)
    }
}