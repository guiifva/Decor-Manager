package com.nocta.eventmanager.catalog.application.use_cases

import com.nocta.eventmanager.catalog.application.dtos.CreateCategoryDto
import com.nocta.eventmanager.catalog.application.dtos.GetCategoryDto
import com.nocta.eventmanager.catalog.application.mappers.GetCategoryDtoMapper
import com.nocta.eventmanager.catalog.domain.Category
import com.nocta.eventmanager.catalog.repositories.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class CreateCategoryUseCase(private val categoryRepository: CategoriesRepository,
                            private val mapper: GetCategoryDtoMapper
) {

    fun createCategory(createCategoryDto: CreateCategoryDto): GetCategoryDto {
        val category = Category(name = createCategoryDto.name, description = createCategoryDto.description)

        categoryRepository.save(category)

        return mapper.map(category)
    }
}