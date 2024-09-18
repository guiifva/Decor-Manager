package com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories

interface ListCategoriesUseCase {
    fun execute(): List<ListCategoriesDto>
}