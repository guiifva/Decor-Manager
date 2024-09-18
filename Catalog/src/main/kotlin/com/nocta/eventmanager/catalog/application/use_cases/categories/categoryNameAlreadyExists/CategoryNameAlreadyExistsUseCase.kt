package com.nocta.eventmanager.catalog.application.use_cases.categories.categoryNameAlreadyExists

interface CategoryNameAlreadyExistsUseCase {
    fun execute(name: String): Boolean
}