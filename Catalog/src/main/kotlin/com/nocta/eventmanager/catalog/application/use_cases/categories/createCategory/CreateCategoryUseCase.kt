package com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory

import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto

interface CreateCategoryUseCase {
    fun execute(createCategoryDto: CreateCategoryDto): GetCategoryDto
}