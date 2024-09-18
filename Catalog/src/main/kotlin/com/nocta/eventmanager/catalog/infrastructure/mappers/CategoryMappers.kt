package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory.CreateCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories.ListCategoriesDto
import com.nocta.eventmanager.catalog.domain.Category
import java.util.*


fun Category.toGetCategoryDto(): GetCategoryDto {
    return GetCategoryDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun Category.toListCategoriesDto(): ListCategoriesDto {
    return ListCategoriesDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun CreateCategoryDto.toCategory(): Category {
    return Category(
        name = this.name,
        description = this.description,
        active = true, // Default value for new categories
        products = mutableListOf() // Initializing as empty
    )
}
