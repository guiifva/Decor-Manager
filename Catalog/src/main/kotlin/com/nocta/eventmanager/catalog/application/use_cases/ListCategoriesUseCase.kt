package com.nocta.eventmanager.catalog.application.use_cases

import com.nocta.eventmanager.catalog.application.dtos.ListCategoriesDto
import com.nocta.eventmanager.catalog.application.mappers.ListCategoriesDtoMappers
import com.nocta.eventmanager.catalog.repositories.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class ListCategoriesUseCase(private val categoriesRepository: CategoriesRepository,
                            private val listCategoriesDtoMappers: ListCategoriesDtoMappers
) {

    fun ListAllCategories() : List<ListCategoriesDto>{
        val categories = categoriesRepository.findAll()

        return categories.map { it -> listCategoriesDtoMappers.map(it) }
    }
}