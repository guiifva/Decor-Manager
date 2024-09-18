package com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory

import java.util.*

interface GetCategoryUseCase {
    fun execute(id: UUID): GetCategoryDto
}