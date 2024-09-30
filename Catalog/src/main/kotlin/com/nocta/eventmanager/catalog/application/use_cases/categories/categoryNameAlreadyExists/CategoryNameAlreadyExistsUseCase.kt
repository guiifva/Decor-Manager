package com.nocta.eventmanager.catalog.application.use_cases.categories.categoryNameAlreadyExists

import java.util.*

interface CategoryNameAlreadyExistsUseCase {
    fun execute(name: String, id: UUID? = null): Boolean
}