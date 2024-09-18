package com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory

import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.domain.exceptions.CategoryAlreadyExistsException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toCategory
import com.nocta.eventmanager.catalog.infrastructure.mappers.toGetCategoryDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : CreateCategoryUseCase{

    @Transactional
    override fun execute(createCategoryDto: CreateCategoryDto): GetCategoryDto {
        if (categoryRepository.findByNameIgnoreCase(createCategoryDto.name) != null) {
            throw CategoryAlreadyExistsException(createCategoryDto.name)
        }

        val category = createCategoryDto.toCategory()

        categoryRepository.save(category)

        return category.toGetCategoryDto()
    }
}