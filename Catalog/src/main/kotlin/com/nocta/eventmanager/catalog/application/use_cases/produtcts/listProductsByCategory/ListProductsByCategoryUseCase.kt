package com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory

import java.util.*

interface ListProductsByCategoryUseCase {
    fun execute(categoryId: UUID): List<ListProductsByCategoryDto>
}