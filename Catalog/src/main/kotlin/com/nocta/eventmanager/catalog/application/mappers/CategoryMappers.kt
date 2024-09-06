package com.nocta.eventmanager.catalog.application.mappers

import com.nocta.eventmanager.catalog.application.dtos.GetCategoryDto
import com.nocta.eventmanager.catalog.application.dtos.ListCategoriesDto
import com.nocta.eventmanager.catalog.domain.Category
import org.springframework.stereotype.Component

@Component
class ListCategoriesDtoMappers : Mapper<Category, ListCategoriesDto> {
    override fun map(t: Category): ListCategoriesDto {
        return ListCategoriesDto(t.id, t.name, t.description)
    }
}

@Component
class GetCategoryDtoMapper : Mapper<Category, GetCategoryDto> {
    override fun map(t: Category): GetCategoryDto {
        return GetCategoryDto(t.id, t.name, t.description)
    }
}
