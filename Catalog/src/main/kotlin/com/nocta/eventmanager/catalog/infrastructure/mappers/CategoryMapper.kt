package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory.CreateCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories.ListCategoriesDto
import com.nocta.eventmanager.catalog.domain.Category
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface CategoryMapper {
    companion object {
        val INSTANCE: CategoryMapper = Mappers.getMapper(CategoryMapper::class.java)
    }

    fun toCreatedThemeDto(category: Category): GetCategoryDto
    fun toListCategoryDto(category: Category): ListCategoriesDto
    fun toEntity(createCategoryDto: CreateCategoryDto): Category
}